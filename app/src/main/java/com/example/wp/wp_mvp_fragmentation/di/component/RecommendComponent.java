package com.example.wp.wp_mvp_fragmentation.di.component;

import dagger.Component;

import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home.RecommendFragment;
import com.jess.arms.di.component.AppComponent;

import com.example.wp.wp_mvp_fragmentation.di.module.RecommendModule;

import com.jess.arms.di.scope.FragmentScope;

@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}