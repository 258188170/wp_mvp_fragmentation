package com.example.wp.wp_mvp_fragmentation.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.example.wp.wp_mvp_fragmentation.mvp.contract.VideoDetailContract;
import com.example.wp.wp_mvp_fragmentation.mvp.model.VideoDetailModel;


@Module
public class VideoDetailModule {
    private VideoDetailContract.View view;

    /**
     * 构建VideoDetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public VideoDetailModule(VideoDetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    VideoDetailContract.View provideVideoDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    VideoDetailContract.Model provideVideoDetailModel(VideoDetailModel model) {
        return model;
    }
}