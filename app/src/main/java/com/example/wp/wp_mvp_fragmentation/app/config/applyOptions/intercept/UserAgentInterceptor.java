package com.example.wp.wp_mvp_fragmentation.app.config.applyOptions.intercept;

import com.example.wp.wp_mvp_fragmentation.app.data.api.Api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangpeng on 2018/5/31.
 * 添加UA拦截器，B站请求API需要加上UA才能正常使用
 */
public class UserAgentInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .removeHeader("User-Agent")
                .addHeader("User-Agent", Api.COMMON_UA_STR)
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
