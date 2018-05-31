package com.example.wp.wp_mvp_fragmentation.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.MyGlobalHttpHandler;
import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.MyGsonConfiguration;
import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.MyOkhttpConfiguration;
import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.MyResponseErrorListener;
import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.MyRetrofitConfiguration;
import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.MyRxCacheConfiguration;
import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.intercept.UserAgentInterceptor;
import com.example.wp.wp_mvp_fragmentation.app.config.lifecyclesOptioins.MyActivityLifecycle;
import com.example.wp.wp_mvp_fragmentation.app.config.lifecyclesOptioins.MyAppLifecycles;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.integration.ConfigModule;
import com.jess.arms.utils.DataHelper;

import java.io.File;
import java.util.List;

/**
 * Created by wangpeng on 2018/5/31.
 */
public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        //使用builder可以为框架配置一些配置信息
        builder
//                .baseurl(Api.BASE_URL)
                .retrofitConfiguration(new MyRetrofitConfiguration())
//                     使用统一UserAgent
//                .addInterceptor(new UserAgentInterceptor())
                .rxCacheConfiguration(new MyRxCacheConfiguration())
                .globalHttpHandler(new MyGlobalHttpHandler())
                .responseErrorListener(new MyResponseErrorListener())
                //缓存
                .cacheFile(new File(DataHelper.getCacheFile(context), "rxCache"))
                .gsonConfiguration(new MyGsonConfiguration())
                .okhttpConfiguration(new MyOkhttpConfiguration());

    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {

        lifecycles.add(new MyAppLifecycles());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {

        lifecycles.add(new MyActivityLifecycle());
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }
}
