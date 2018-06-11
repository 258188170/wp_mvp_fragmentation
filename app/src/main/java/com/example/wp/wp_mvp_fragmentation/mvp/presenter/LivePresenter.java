package com.example.wp.wp_mvp_fragmentation.mvp.presenter;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.example.wp.wp_mvp_fragmentation.app.data.entry.LiveMultiItem;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.live.GetAllListData;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.LiveContract;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.LiveMultiItemAdapter;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@FragmentScope
public class LivePresenter extends BasePresenter<LiveContract.Model, LiveContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    private List<GetAllListData.DataBean.BannerBean> mBanner;
    private LiveMultiItemAdapter mAdapter;
    private BGABanner.Adapter<ImageView, String> mBannerAdapter;

    @Inject
    public LivePresenter(LiveContract.Model model, LiveContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
        this.mBannerAdapter = null;
    }

    public void loadData(boolean refresh) {

        mModel.getLiveList(refresh)
                .retryWhen(new RetryWithDelay(3, 2))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mRootView.showLoading())
                .doFinally(() -> mRootView.hideLoading())
                .observeOn(Schedulers.io())
                .map(getAllListDataResult -> {
                    GetAllListData.DataBean getAllListBean = getAllListDataResult.getData();
                    List<LiveMultiItem> data = new ArrayList<>();
                    if (getAllListBean != null) {
                        //轮播
                        mBanner = getAllListBean.getBanner();
                        //推荐
                        data.addAll(mModel.parseRecommendData(getAllListBean));
                        //分类数据
                        data.addAll(mModel.parsePartitions(getAllListBean));
                    }
                    return data;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<LiveMultiItem>>(mErrorHandler) {
                    @Override
                    public void onNext(List<LiveMultiItem> liveMultiItems) {
                        setBanner();
                        if (liveMultiItems != null) {
                            setAdapter(liveMultiItems);
                        }
                    }
                });


    }

    private void setAdapter(List<LiveMultiItem> data) {
        if (mAdapter == null) {
            mAdapter = new LiveMultiItemAdapter(data);
            mAdapter.setSpanSizeLookup((gridLayoutManager, position) -> data.get(position).getSpanSize());
            mRootView.setHeaderView(mAdapter);
            mRootView.setRecyclerAdapter(mAdapter);
            mRootView.setFooterView(mAdapter);
        } else {
            mAdapter.setNewData(data);
        }

    }

    private void setBanner() {
        mBannerAdapter = (banner, itemView, model, position) -> mImageLoader.loadImage(getContext(),
                ImageConfigImpl.builder()
                        .url(model)
                        .imageView(itemView)
                        .build());
        List<String> banners = new ArrayList<>();
        if (mBanner != null) {
            for (int i = 0; i < mBanner.size(); i++) {
                banners.add(mBanner.get(i).getImg());
            }

        }
        mRootView.setBanner(mBannerAdapter, banners);
    }

    private Context getContext() {
        return mAppManager.getTopActivity() == null ? mApplication : mAppManager.getTopActivity();
    }
}
