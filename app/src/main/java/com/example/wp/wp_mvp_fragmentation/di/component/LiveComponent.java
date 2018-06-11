package com.example.wp.wp_mvp_fragmentation.di.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.example.wp.wp_mvp_fragmentation.di.module.LiveModule;

import com.jess.arms.di.scope.FragmentScope;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home.LiveFragment;

@FragmentScope
@Component(modules = LiveModule.class, dependencies = AppComponent.class)
public interface LiveComponent {
    void inject(LiveFragment fragment);
}