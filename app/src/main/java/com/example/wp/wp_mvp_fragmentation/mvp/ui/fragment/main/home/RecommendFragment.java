package com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.app.base.MySupportFragment;
import com.example.wp.wp_mvp_fragmentation.di.component.DaggerRecommendComponent;
import com.example.wp.wp_mvp_fragmentation.di.module.RecommendModule;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.RecommendContract;
import com.example.wp.wp_mvp_fragmentation.mvp.presenter.RecommendPresenter;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.RecommendMultiItemAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class RecommendFragment extends MySupportFragment<RecommendPresenter> implements RecommendContract.View {

    private View mRootView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerRecommendComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_recommend_main_home, container, false);
        }
        return mRootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeColors(ArmsUtils.getColor(_mActivity, R.color.colorPrimary));
        mRefreshLayout.setOnRefreshListener(() ->
                mPresenter.loadData(mPresenter.getIdx(true), true, false));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadData(0, false, true);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void setRecycleAdapter(RecommendMultiItemAdapter adapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity,2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(adapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 上拉加载更多视图
                if (position == adapter.getData().size()) {
                    return 2;
                }
                return adapter.getData().get(position).getSpanSize();
            }
        });
    }

    @Override
    public void recycleScrollToPosition(int position) {

        mRecyclerView.scrollToPosition(position);
    }
}
