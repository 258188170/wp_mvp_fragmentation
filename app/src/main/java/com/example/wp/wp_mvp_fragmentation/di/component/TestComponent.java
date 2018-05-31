package com.example.wp.wp_mvp_fragmentation.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.wp.wp_mvp_fragmentation.di.module.TestModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.activity.TestActivity;

@ActivityScope
@Component(modules = TestModule.class, dependencies = AppComponent.class)
public interface TestComponent {
    void inject(TestActivity activity);
}