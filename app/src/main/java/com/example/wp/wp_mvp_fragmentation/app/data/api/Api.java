package com.example.wp.wp_mvp_fragmentation.app.data.api;

/**
 * Created by wangpeng on 2018/5/31.
 */
public interface Api {
    String BASE_URL ="";
    String LIVE_BASE_URL = "http://api.live.bilibili.com/";
    String RECOMMEND_BASE_URL = "https://app.bilibili.com/";
    String VIDEO_DETAIL_SUMMARY_BASE_URL = "https://app.bilibili.com/";
    String VIDEO_DETAIL_REPLY_BASE_URL = "https://api.bilibili.com/";

    // 没有登录的情况下，使用这个User-Agent
    String COMMON_UA_STR = "Mozilla/5.0 BiliDroid/5.15.0 (bbcallen@gmail.com)";
}
