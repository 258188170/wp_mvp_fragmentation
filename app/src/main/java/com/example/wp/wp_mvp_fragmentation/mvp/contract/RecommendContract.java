package com.example.wp.wp_mvp_fragmentation.mvp.contract;

import com.example.wp.wp_mvp_fragmentation.app.data.entry.recomment.IndexData;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.RecommendMultiItemAdapter;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.entity.RecommendMultiItem;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;


public interface RecommendContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void setRecycleAdapter(RecommendMultiItemAdapter adapter);
        void recycleScrollToPosition(int position);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        /**
         * @param idx        下拉刷新时取第一个数据的idx，上拉加载更多时取最后一个数据的idx
         * @param refresh    下拉刷新时取"true"，上拉加载更多时取"false"
         * @param clearCache 第一次加载数据时为true，其他时候为false(对应的true时，login_event的值为1[获取banner]，为false时，login_event的值为0)
         * @return
         */
        Observable<IndexData> getRecommendIndexData(int idx, boolean refresh, boolean clearCache);

        List<RecommendMultiItem> parseIndexData(IndexData indexData);
    }
}
