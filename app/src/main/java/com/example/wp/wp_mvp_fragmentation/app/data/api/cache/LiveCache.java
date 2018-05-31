package com.example.wp.wp_mvp_fragmentation.app.data.api.cache;

import com.example.wp.wp_mvp_fragmentation.app.data.entry.live.GetAllListData;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Created by wangpeng on 2018/5/31.
 */
public interface LiveCache {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<GetAllListData>> getLiveIndexList(Observable<GetAllListData> data, EvictProvider evictProvider);
}
