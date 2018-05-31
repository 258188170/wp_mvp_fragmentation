package com.example.wp.wp_mvp_fragmentation.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.wp.wp_mvp_fragmentation.di.module.SplashModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.activity.SplashActivity;

@ActivityScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}