package com.example.wp.wp_mvp_fragmentation.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.wp.wp_mvp_fragmentation.app.base.MySupportActivity;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.example.wp.wp_mvp_fragmentation.di.component.DaggerSplashComponent;
import com.example.wp.wp_mvp_fragmentation.di.module.SplashModule;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.SplashContract;
import com.example.wp.wp_mvp_fragmentation.mvp.presenter.SplashPresenter;

import com.example.wp.wp_mvp_fragmentation.R;


import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class SplashActivity extends MySupportActivity<SplashPresenter> implements SplashContract.View {


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.tv_launcher_timer)
    void toMain() {
        mPresenter.toStart();
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
    public void clickable() {

    }
}
