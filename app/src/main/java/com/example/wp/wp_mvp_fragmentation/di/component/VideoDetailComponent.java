package com.example.wp.wp_mvp_fragmentation.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.wp.wp_mvp_fragmentation.di.module.VideoDetailModule;

import com.jess.arms.di.scope.ActivityScope;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.activity.VideoDetailActivity;

@ActivityScope
@Component(modules = VideoDetailModule.class, dependencies = AppComponent.class)
public interface VideoDetailComponent {
    void inject(VideoDetailActivity activity);
}