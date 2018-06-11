package com.example.wp.wp_mvp_fragmentation.mvp.contract;

import android.widget.ImageView;

import com.example.wp.wp_mvp_fragmentation.app.data.entry.LiveMultiItem;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.live.GetAllListData;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.LiveMultiItemAdapter;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import io.reactivex.Observable;


public interface LiveContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void setHeaderView(LiveMultiItemAdapter adapter);

        void setBanner(BGABanner.Adapter<ImageView, String> adapter, List<String> banners);

        void setRecyclerAdapter(LiveMultiItemAdapter adapter);

        void setFooterView(LiveMultiItemAdapter adapter);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<GetAllListData> getLiveList(boolean update);

        List<LiveMultiItem> parseRecommendData(GetAllListData.DataBean getAllListData);

        List<LiveMultiItem> parsePartitions(GetAllListData.DataBean getAllListData);
    }
}
