package com.example.wp.wp_mvp_fragmentation.app.data.api.service;

import com.example.wp.wp_mvp_fragmentation.app.data.entry.recomment.IndexData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by wangpeng .
 */
public interface RecommendService {
    /**
     * 推荐
     * @param idx
     * @param pull
     * @param login_event
     * @return
     */
    @Headers({"Domain-Name: recommend"})
    @GET("/x/feed/index?appkey=1d8b6e7d45233436&build=515000&mobi_app=android&network=wifi&open_event=cold&platform=android&style=2&ts=1508556998&sign=8215c7d01711e2f11e75830d856d32f5")
    Observable<IndexData> getRecommendIndexData(@Query("idx") int idx, @Query("pull") String pull, @Query("login_event") int login_event);//login_event为1时加载banner

}
