package com.example.wp.wp_mvp_fragmentation.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.example.wp.wp_mvp_fragmentation.mvp.contract.LiveContract;
import com.example.wp.wp_mvp_fragmentation.mvp.model.LiveModel;


@Module
public class LiveModule {
    private LiveContract.View view;

    /**
     * 构建LiveModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LiveModule(LiveContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    LiveContract.View provideLiveView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    LiveContract.Model provideLiveModel(LiveModel model) {
        return model;
    }
}