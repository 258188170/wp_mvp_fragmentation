package com.example.wp.wp_mvp_fragmentation.app.config.lifecyclesOptioins;

import android.app.Activity;
import android.os.Bundle;

import org.simple.eventbus.EventBus;

import timber.log.Timber;

/**
 * Created by wangpeng on 2018/5/31.
 */
public class MyActivityLifecycle implements android.app.Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.w(activity + " - onActivityCreated");
        EventBus.getDefault().register(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        EventBus.getDefault().unregister(activity);
    }
}
