package com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.app.base.MySupportFragment;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.tab.TabEntity;
import com.example.wp.wp_mvp_fragmentation.di.component.DaggerLiveComponent;
import com.example.wp.wp_mvp_fragmentation.di.module.LiveModule;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.LiveContract;
import com.example.wp.wp_mvp_fragmentation.mvp.presenter.LivePresenter;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.LiveMultiItemAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class LiveFragment extends MySupportFragment<LivePresenter> implements LiveContract.View {

    private View mHeaderView;
    private EditText mEtSearch;
    private BGABanner mBanner;
    private CommonTabLayout mCtlCategory;
    private View mFooterView;
    private Button mBtnAllLive;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private View mRootView;

    public static LiveFragment newInstance() {
        LiveFragment fragment = new LiveFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLiveComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .liveModule(new LiveModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_live_main_home, container, false);
        }
        return mRootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRefreshLayout();
        initHeaderView();
        initCtlCategory();
        initFooterView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadData(false);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeColors(ArmsUtils.getColor(_mActivity, R.color.colorPrimary));
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(() -> mPresenter.loadData(true));
    }

    //头部布局    待实现
    private void initHeaderView() {
        mHeaderView = getLayoutInflater().inflate(R.layout.header_live_main_home, null, false);
        mEtSearch = mHeaderView.findViewById(R.id.et_search);
        mBanner = mHeaderView.findViewById(R.id.banner);
        mCtlCategory = mHeaderView.findViewById(R.id.ctl_category);
    }

    private void initCtlCategory() {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.live_home_follow_anchor), R.mipmap.live_home_follow_anchor, R.mipmap.live_home_follow_anchor));
        tabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.live_home_entertainment), R.mipmap.ic_live_home_entertainment, R.mipmap.ic_live_home_entertainment));
        tabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.live_home_game), R.mipmap.ic_live_home_game, R.mipmap.ic_live_home_game));
        tabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.live_home_mobile_game), R.mipmap.ic_live_home_mobile_game, R.mipmap.ic_live_home_mobile_game));
        tabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.live_home_painting), R.mipmap.ic_live_home_painting, R.mipmap.ic_live_home_painting));
        mCtlCategory.setTabData(tabEntities);
        mCtlCategory.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFooterView() {
        mFooterView = getLayoutInflater().inflate(R.layout.footer_live_main_home, null, false);
        mBtnAllLive = mFooterView.findViewById(R.id.btn_all_live);
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
    public void onDestroy() {
        super.onDestroy();
        mBanner.setAutoPlayAble(false);
    }

    @Override
    public void setHeaderView(LiveMultiItemAdapter adapter) {

    }

    @Override
    public void setBanner(BGABanner.Adapter<ImageView, String> adapter, List<String> banners) {

    }

    @Override
    public void setRecyclerAdapter(LiveMultiItemAdapter adapter) {

    }

    @Override
    public void setFooterView(LiveMultiItemAdapter adapter) {

    }
}
