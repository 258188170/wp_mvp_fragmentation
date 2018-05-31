package com.example.wp.wp_mvp_fragmentation.app.data.api.cache;

import com.example.wp.wp_mvp_fragmentation.app.data.entry.recomment.IndexData;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Created by wangpeng .
 */
public interface RecommendCache {
    /**
     * @param data
     * @param dynamicKey    请求同一个接口,需要参照一个变量的不同返回不同的数据,比如分页,构造时传入页数就可以了
     * @param evictProvider EvictProvider & EvictDynamicKey & EvictDynamicKeyGroup
     *                      这三个对象内部都保存有一个boolean类型的字段,其意思为是否驱逐(使用或删除)缓存,
     *                      RxCache在取到未过期的缓存时,会根据这个boolean字段,考虑是否使用这个缓存,
     *                      EvictProvider如果为true,就会重新通过Retrofit获取新的数据,如果为false就会使用这个缓存
     * @return
     */
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<IndexData>> getRecommendIndexData(Observable<IndexData> data, DynamicKey dynamicKey, EvictProvider evictProvider);
}
