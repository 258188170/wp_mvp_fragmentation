package com.example.wp.wp_mvp_fragmentation.mvp.presenter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wp.wp_mvp_fragmentation.app.data.api.Router;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.SplashContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    private int count = 5;

    @Inject
    public SplashPresenter(SplashContract.Model model, SplashContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void toStart() {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .doOnSubscribe(disposable -> mRootView.showTimer(count))
                .map(aLong -> count - aLong)
                .take(count + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(aLong -> {
                    if (count==0){
                        ARouter.getInstance().build(Router.APP_MAIN).navigation();
                    }
                });

    }
}
