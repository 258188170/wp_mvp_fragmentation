package com.example.wp.wp_mvp_fragmentation.mvp.model;

import android.app.Application;

import com.example.wp.wp_mvp_fragmentation.app.data.api.Api;
import com.example.wp.wp_mvp_fragmentation.app.data.api.cache.LiveCache;
import com.example.wp.wp_mvp_fragmentation.app.data.api.service.LiveService;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.LiveMultiItem;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.live.GetAllListData;
import com.example.wp.wp_mvp_fragmentation.mvp.contract.LiveContract;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;


@FragmentScope
public class LiveModel extends BaseModel implements LiveContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LiveModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
        RetrofitUrlManager.getInstance().putDomain("live", Api.LIVE_BASE_URL);
    }


    @Override
    public Observable<GetAllListData> getLiveList(boolean update) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(LiveService.class)
                .getLiveIndexList())
                .flatMap(new Function<Observable<GetAllListData>, ObservableSource<GetAllListData>>() {
                    @Override
                    public ObservableSource<GetAllListData> apply(Observable<GetAllListData> resultDataObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(LiveCache.class)
                                .getLiveIndexList(resultDataObservable, new EvictProvider(update))
                                .map(new Function<Reply<GetAllListData>, GetAllListData>() {
                                    @Override
                                    public GetAllListData apply(Reply<GetAllListData> resultReply) throws Exception {
                                        return resultReply.getData();
                                    }
                                });
                    }
                });
    }

    @Override
    public List<LiveMultiItem> parseRecommendData(GetAllListData.DataBean getAllListData) {
        List<LiveMultiItem> data = new ArrayList<>();
        GetAllListData.DataBean.RecommendDataBean recommend_data = getAllListData.getRecommend_data();
        //推荐
        if (recommend_data != null) {
            //TITTLE
            final GetAllListData.DataBean.RecommendDataBean.PartitionBean partition = recommend_data.getPartition();
            if (partition != null) {
                LiveMultiItem item = new LiveMultiItem();
                item.setItemType(LiveMultiItem.TITLE);
                item.setTitleIconSrc(partition.getSub_icon().getSrc());
                item.setTitleName(partition.getName());
                item.setTitleCount(partition.getCount());
                data.add(item);
            }
            //ITEM
            final List<GetAllListData.DataBean.RecommendDataBean.LivesBean> lives = recommend_data.getLives();
            if (lives != null) {
                int size = lives.size();
                GetAllListData.DataBean.RecommendDataBean.LivesBean live = null;
                for (int i = 0; i < size; i++) {
                    live = lives.get(i);
                    LiveMultiItem item = new LiveMultiItem();
                    item.setItemType(LiveMultiItem.ITEM);
                    item.setOdd((i % 2 == 0));
                    item.setItemCoverSrc(live.getCover().getSrc());
                    item.setItemOwnerName(live.getOwner().getName());
                    item.setItemTitle(live.getTitle());
                    item.setItemSubTitle(live.getArea_v2_name());
                    item.setItemOnline(live.getOnline());
                    data.add(item);
                }
            }
            //BANNER
            final List<GetAllListData.DataBean.RecommendDataBean.BannerDataBean> banner_data = recommend_data.getBanner_data();
            if (banner_data != null) {
                GetAllListData.DataBean.RecommendDataBean.BannerDataBean bannerDataBean = null;
                int size = banner_data.size();
                for (int i = 0; i < size; i++) {
                    bannerDataBean = banner_data.get(i);
                    LiveMultiItem item = new LiveMultiItem();
                    item.setItemType(LiveMultiItem.BANNER);
                    item.setBannerTitle(bannerDataBean.getTitle());
                    item.setBannerCoverSrc(bannerDataBean.getCover().getSrc());
                    data.add(item);
                }
            }
            // ITEM
            if (lives != null) {
                GetAllListData.DataBean.RecommendDataBean.LivesBean live;
                for (int i = 6; i < 12; i++) {
                    live = lives.get(i);
                    LiveMultiItem item = new LiveMultiItem();
                    item.setItemType(LiveMultiItem.ITEM);
                    item.setOdd((i % 2 == 0));
                    item.setItemCoverSrc(live.getCover().getSrc());
                    item.setItemOwnerName(live.getOwner().getName());
                    item.setItemTitle(live.getTitle());
                    item.setItemSubTitle(live.getArea_v2_name());
                    item.setItemOnline(live.getOnline());
                    data.add(item);
                }
            }
            // BOTTOM
            LiveMultiItem item = new LiveMultiItem();
            item.setItemType(LiveMultiItem.BOTTOM);
            data.add(item);
        }
        return data;
    }

    @Override
    public List<LiveMultiItem> parsePartitions(GetAllListData.DataBean getAllListData) {
        List<LiveMultiItem> data = new ArrayList<>();
        final List<GetAllListData.DataBean.PartitionsBean> partitions = getAllListData.getPartitions();
        if (partitions != null) {
            //娱乐,游戏,手游,绘画
            for (GetAllListData.DataBean.PartitionsBean partitionsBean : partitions) {
                // TITLE
                {
                    LiveMultiItem item = new LiveMultiItem();
                    item.setItemType(LiveMultiItem.TITLE);
                    item.setTitleIconSrc(partitionsBean.getPartition().getSub_icon().getSrc());
                    item.setTitleName(partitionsBean.getPartition().getName());
                    item.setTitleCount(partitionsBean.getPartition().getCount());
                    data.add(item);
                }
                // ITEM
                List<GetAllListData.DataBean.PartitionsBean.LivesBeanX> lives = partitionsBean.getLives();
                if (lives != null) {
                    GetAllListData.DataBean.PartitionsBean.LivesBeanX live;
                    for (int i = 0; i < 6; i++) {
                        live = lives.get(i);
                        LiveMultiItem item = new LiveMultiItem();
                        item.setItemType(LiveMultiItem.ITEM);
                        item.setOdd((i % 2 == 0));
                        item.setItemCoverSrc(live.getFace());
                        item.setItemOwnerName(live.getUname());
                        item.setItemTitle(live.getTitle());
                        item.setItemSubTitle(live.getArea_name());
                        item.setItemOnline(live.getOnline());
                        data.add(item);
                    }
                }
                // BOTTOM
                LiveMultiItem item = new LiveMultiItem();
                item.setItemType(LiveMultiItem.BOTTOM);
                data.add(item);
            }
        }
        return data;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

}