package com.example.wp.wp_mvp_fragmentation.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.app.base.MySupportActivity;
import com.example.wp.wp_mvp_fragmentation.app.tag.MainTag;
import com.example.wp.wp_mvp_fragmentation.di.component.DaggerMainComponent;
import com.example.wp.wp_mvp_fragmentation.di.module.MainModule;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.MainContract;
import com.example.wp.wp_mvp_fragmentation.mvp.presenter.MainPresenter;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.nav.NavHistoryFragment;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.nav.NavHomeFragment;
import com.flyco.systembar.SystemBarHelper;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.simple.eventbus.Subscriber;

import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 主界面和侧边栏
 */
@Route(path = "/app/main")
public class MainActivity extends MySupportActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.ll_root)
    LinearLayout mLlRoot;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.nav)
    NavigationView mNav;

    private long mPreTime = 0;

    @OnClick(R.id.rl_setting)
    void toSetting() {
        ARouter.getInstance().build("/app/setting").navigation();
    }

    @OnClick(R.id.rl_theme)
    void toTheme() {
        ARouter.getInstance().build("/app/theme").navigation();
    }

    @OnClick(R.id.rl_change_skin)
    void changeSkin() {
        // TODO: 2017/10/18 切换夜间/白天皮肤
    }

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
        SystemBarHelper.setPadding(getApplicationContext(), mNav.getHeaderView(0));
        SystemBarHelper.setPadding(getApplicationContext(), mLlRoot);
    }

    private void initFragmentation() {

        //加载首页fragment
        NavHomeFragment homeFragment = findFragment(NavHomeFragment.class);
        if (homeFragment == null) {
            loadRootFragment(R.id.fl_content, NavHomeFragment.newInstance());
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
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }


    public void closeDrawer() {
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
                NavHomeFragment homeFragment = findFragment(NavHomeFragment.class);
                if (homeFragment == null) {
                    start(NavHomeFragment.newInstance(), SupportFragment.SINGLETASK);
                } else {
                    popTo(NavHomeFragment.class, false);
                }
                break;
            case R.id.nav_history:
                NavHistoryFragment historyFragment = findFragment(NavHistoryFragment.class);
                if (historyFragment == null) {
                    popTo(NavHomeFragment.class, false, new TimerTask() {
                        @Override
                        public void run() {
                            start(NavHistoryFragment.newInstance());
                        }
                    });
                } else {
                    popTo(NavHistoryFragment.class, false);
                }
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
                // 放置后台
                // moveTaskToBack(false);

                // 2秒内两次点击返回键退出应用
                long nowTime = System.currentTimeMillis();
                if (nowTime - mPreTime > 2000) {
                    ArmsUtils.makeText(getApplicationContext(), ArmsUtils.getString(this, R.string.double_click_to_exit));
                    mPreTime = nowTime;
                } else {
                    ArmsUtils.exitApp();
                }
            }
        }
    }

    @Subscriber(tag = "openDrawer")
    public void openDrawer(MainTag mainTag) {
        if (!mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.openDrawer(GravityCompat.START);
        }
    }

}
