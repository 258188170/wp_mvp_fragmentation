package com.example.wp.wp_mvp_fragmentation.mvp.presenter;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wp.wp_mvp_fragmentation.app.data.api.Router;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.RecommendContract;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.RecommendMultiItemAdapter;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.entity.RecommendMultiItem;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.widget.CustomLoadMoreView;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@FragmentScope
public class RecommendPresenter extends BasePresenter<RecommendContract.Model, RecommendContract.View> implements BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    private RecommendMultiItemAdapter mAdapter;
    private List<RecommendMultiItem> data = null;

    @Inject
    public RecommendPresenter(RecommendContract.Model model, RecommendContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void loadData(int idx, boolean refresh, boolean clearCache) {
        mModel.getRecommendIndexData(idx, refresh, clearCache)
                .retryWhen(new RetryWithDelay(3, 2))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (refresh || clearCache) mRootView.showLoading();
                })
                .doFinally(() -> mRootView.hideLoading())
                .observeOn(Schedulers.io())
                .map(indexData -> mModel.parseIndexData(indexData))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<RecommendMultiItem>>(mErrorHandler) {
                    @Override
                    public void onNext(List<RecommendMultiItem> recommendMultiItems) {
                        if (recommendMultiItems != null) {
                            setAdapter(recommendMultiItems, refresh);
                        }
                    }

                });

    }

    public int getIdx(boolean refresh) {
        data = mAdapter.getData();
        if (data == null || data.size() == 0) {
            return 0;
        }
        int index = refresh ? 0 : data.size() - 1;
        return data.get(index).getIndexDataBean() == null ? 0 : data.get(index).getIndexDataBean().getIdx();
    }

    public void setAdapter(List<RecommendMultiItem> recommendMultiItems, boolean refresh) {
        if (mAdapter == null) {
            mAdapter = new RecommendMultiItemAdapter(recommendMultiItems);
            mRootView.setRecycleAdapter(mAdapter);
            mAdapter.setEnableLoadMore(true);
            mAdapter.setPreLoadNumber(2);
            mAdapter.setLoadMoreView(new CustomLoadMoreView());
            mAdapter.setOnLoadMoreListener(this);
            mAdapter.setOnItemClickListener(this);
        } else {
            if (refresh) {
                removePreRefreshItem();
                addPreRefreshItem();
                mAdapter.addData(0, recommendMultiItems);
                mRootView.recycleScrollToPosition(0);
            } else {
                mAdapter.addData(recommendMultiItems);
                mAdapter.loadMoreComplete();
            }
        }
    }


    private void removePreRefreshItem() {
        final List<RecommendMultiItem> data = mAdapter.getData();
        if (data == null && data.size() == 0) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getItemType() == RecommendMultiItem.PRE_HERE_CLICK_TO_REFRESH) {
                data.remove(i);
                break;
            }
        }
    }

    private void addPreRefreshItem() {
        RecommendMultiItem multiItem = new RecommendMultiItem();
        multiItem.setItemType(RecommendMultiItem.PRE_HERE_CLICK_TO_REFRESH);
        mAdapter.addData(0, multiItem);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<RecommendMultiItem> data = adapter.getData();
        if (data != null) {
            RecommendMultiItem item = data.get(position);
            if (RecommendMultiItem.isVideoItem(item.getItemType())) {
                ARouter.getInstance().build(Router.VIDEODETAIL_ACTIVITY).withString("aid", item.getIndexDataBean().getParam()).navigation();
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        loadData(getIdx(false), false, false);
    }
}
