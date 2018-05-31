package com.example.wp.wp_mvp_fragmentation.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.wp.wp_mvp_fragmentation.di.module.MainModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}