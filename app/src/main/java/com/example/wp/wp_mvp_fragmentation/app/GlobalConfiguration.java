package com.example.wp.wp_mvp_fragmentation.app;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.wp.wp_mvp_fragmentation.BuildConfig;
import com.example.wp.wp_mvp_fragmentation.app.config.MyGlobalHttpHandler;
import com.example.wp.wp_mvp_fragmentation.app.config.MyGsonConfiguration;
import com.example.wp.wp_mvp_fragmentation.app.config.MyResponseErrorListener;
import com.example.wp.wp_mvp_fragmentation.app.config.MyRetrofitConfiguration;
import com.example.wp.wp_mvp_fragmentation.app.config.MyRxCacheConfiguration;
import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.intercept.UserAgentInterceptor;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.http.RequestInterceptor;
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
        if (!BuildConfig.LOG_DEBUG) { //Release 时,让框架不再打印 Http 请求和响应的信息
            builder.printHttpLogLevel(RequestInterceptor.Level.NONE);
        }
        builder
//                .baseurl(Api.BASE_URL)
                .retrofitConfiguration(new MyRetrofitConfiguration())
//                     使用统一UserAgent
                .addInterceptor(new UserAgentInterceptor())
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

    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {

    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }
}
