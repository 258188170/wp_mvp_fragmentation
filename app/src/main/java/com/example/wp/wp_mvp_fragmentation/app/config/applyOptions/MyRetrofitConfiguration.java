package com.example.wp.wp_mvp_fragmentation.app.config.applyOptions;

import android.content.Context;

import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.intercept.LoggingInterceptor;
import com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.intercept.UserAgentInterceptor;
import com.jess.arms.BuildConfig;
import com.jess.arms.di.module.ClientModule;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by wangpeng on 2018/5/31.
 *  配置多BaseUrl支持
 */
public class MyRetrofitConfiguration implements ClientModule.RetrofitConfiguration {
    @Override
    public void configRetrofit(Context context, Retrofit.Builder builder) {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(new LoggingInterceptor());//使用自定义的Log拦截器
        }
        clientBuilder.addInterceptor(new UserAgentInterceptor());//使用自定义User-Agent
        builder.client(RetrofitUrlManager.getInstance().with(clientBuilder)
                .build());
    }
}
