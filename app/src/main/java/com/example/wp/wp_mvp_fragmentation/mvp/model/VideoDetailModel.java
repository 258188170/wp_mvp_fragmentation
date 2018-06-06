package com.example.wp.wp_mvp_fragmentation.mvp.model;

import android.app.Application;

import com.example.wp.wp_mvp_fragmentation.app.data.api.service.VideoDetailService;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.PlayUrl;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.Reply;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.Summary;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.VideoDetailContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class VideoDetailModel extends BaseModel implements VideoDetailContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public VideoDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<Summary> getSummaryData(String aid) {
        return mRepositoryManager.obtainRetrofitService(VideoDetailService.class).getSummaryData(aid);
    }

    @Override
    public Observable<Reply> getReplyData(String aid, int pn, int ps) {
        return mRepositoryManager.obtainRetrofitService(VideoDetailService.class).getReplyData(aid, pn, ps);
    }

    @Override
    public Observable<PlayUrl> getPlayurl(String aid) {
        return mRepositoryManager.obtainRetrofitService(VideoDetailService.class).getPlayurl(aid);
    }
}