package com.example.wp.wp_mvp_fragmentation.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.wp.wp_mvp_fragmentation.app.base.MySupportActivity;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.NavHistoryFragment;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.NavHomeFragment;
import com.flyco.systembar.SystemBarHelper;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.example.wp.wp_mvp_fragmentation.di.component.DaggerMainComponent;
import com.example.wp.wp_mvp_fragmentation.di.module.MainModule;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.MainContract;
import com.example.wp.wp_mvp_fragmentation.mvp.presenter.MainPresenter;

import com.example.wp.wp_mvp_fragmentation.R;


import java.util.TimerTask;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 主界面和侧边栏
 */
@Route(path = "/app/main")
public class MainActivity extends MySupportActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.main_root)
    LinearLayoutCompat mLlRoot;
    @BindView(R.id.main_drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.nav_view)
    NavigationView mNav;

    private long mPreTime = 0;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initStatusBar();
        initFragmentation();
        initNavigationView();
    }

    private void initStatusBar() {
        SystemBarHelper.tintStatusBarForDrawer(this, mDrawer, ArmsUtils.getColor(this, R.color.colorPrimary));
        SystemBarHelper.setPadding(this, mNav.getHeaderView(0));
        SystemBarHelper.setPadding(this, mLlRoot);
    }

    private void initFragmentation() {

        //加载首页fragment
        NavHomeFragment homeFragment = findFragment(NavHomeFragment.class);
        if (homeFragment == null) {
            loadRootFragment(R.id.main_content, NavHomeFragment.newInstance());
        }

    }

    private void initNavigationView() {
        mNav.setBackgroundColor(ArmsUtils.getColor(this, R.color.white));
        removeNavigationViewScrollbar(mNav);
        mNav.setCheckedItem(R.id.nav_home);
        mNav.setNavigationItemSelectedListener(this);
    }

    private void removeNavigationViewScrollbar(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalFadingEdgeEnabled(false);
            }
        }
    }


    public void closeDrawer() {
        mDrawer.closeDrawer(GravityCompat.START);
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
        finish();
    }


    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        closeDrawer();
        switch (item.getItemId()) {
            case R.id.nav_home:
                NavHomeFragment navHomeFragment = findFragment(NavHomeFragment.class);
                if (navHomeFragment == null)
                    start(navHomeFragment, SupportFragment.SINGLETASK);
                else
                    popTo(NavHomeFragment.class, false);
                break;
            case R.id.nav_history:
                NavHistoryFragment navHistoryFragment = findFragment(NavHistoryFragment.class);
                if (navHistoryFragment == null)
                    popTo(NavHistoryFragment.class, false, new TimerTask() {
                        @Override
                        public void run() {
                            start(navHistoryFragment.newInstance());
                        }
                    });
                else
                    popTo(NavHomeFragment.class, false);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressedSupport() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            ISupportFragment topFragment = getTopFragment();
            if (!(topFragment instanceof NavHomeFragment)) {
                mNav.setCheckedItem(R.id.nav_home);
            }
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                pop();
            } else {
                //放置后台
                moveTaskToBack(false);

                //2秒点击退出应用
                long nowTime = System.currentTimeMillis();
                if (mPreTime - nowTime > 2000) {
                    ArmsUtils.makeText(this, ArmsUtils.getString(this,
                            R.string.double_click_to_exit));
                    mPreTime = nowTime;
                }else {
                    ArmsUtils.exitApp();
                }
            }
        }

    }
}
