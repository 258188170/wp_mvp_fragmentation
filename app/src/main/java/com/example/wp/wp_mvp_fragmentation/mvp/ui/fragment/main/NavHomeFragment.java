package com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.app.base.MySupportFragment;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.tab.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by WangPeng on 2018/6/3.
 */
public class NavHomeFragment extends MySupportFragment {
    private final ISupportFragment[] mFragments = new ISupportFragment[4];
    @BindView(R.id.bottom_bar)
    CommonTabLayout mBottomBar;

    public static NavHomeFragment newInstance() {
        return new NavHomeFragment();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_nav, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        initFragmentation();
        initBottomBar();
    }

    private void initFragmentation() {
        final ISupportFragment fragment = findChildFragment(MainHomeFragment.class);
        if (fragment == null) {
            mFragments[0] = MainHomeFragment.newInstance();
            mFragments[1] = MainCategoryFragment.newInstance();
            mFragments[2] = MainDynamicFragment.newInstance();
            mFragments[3] = MainCommunicateFragment.newInstance();
            //加载多个同级根Fragment,类似Wechat, QQ主页的场景
            loadMultipleRootFragment(R.id.fl_content, 0, mFragments);
        } else {
            mFragments[0] = findChildFragment(MainHomeFragment.class);
            mFragments[1] = findChildFragment(MainCategoryFragment.class);
            mFragments[2] = findChildFragment(MainDynamicFragment.class);
            mFragments[3] = findChildFragment(MainCommunicateFragment.class);
        }
    }

    private void initBottomBar() {
        final ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.main_home), R.mipmap.ic_home_selected, R.mipmap.ic_home_unselected));
        mTabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.main_category), R.mipmap.ic_category_selected, R.mipmap.ic_category_unselected));
        mTabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.main_dynamic), R.mipmap.ic_dynamic_selected, R.mipmap.ic_dynamic_unselected));
        mTabEntities.add(new TabEntity(ArmsUtils.getString(_mActivity, R.string.main_communicate), R.mipmap.ic_communicate_selected, R.mipmap.ic_communicate_unselected));
        mBottomBar.setTabData(mTabEntities);
    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
