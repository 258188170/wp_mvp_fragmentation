package com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.Summary;
import com.jess.arms.utils.ArmsUtils;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by wangpeng .
 */
public class MyTagAdapter extends TagAdapter<Summary.DataBean.TagBean> {

    public MyTagAdapter(List<Summary.DataBean.TagBean> datas) {
        super(datas);
    }

    @Override
    public View getView(FlowLayout parent, int position, Summary.DataBean.TagBean tagBean) {
        TextView tv = new TextView(parent.getContext());
        int paddingTopAndBottom = 10;
        int paddingLeftAndRight = 25;
        tv.setPadding(paddingLeftAndRight, paddingTopAndBottom, paddingLeftAndRight, paddingTopAndBottom);
        tv.setText(tagBean.getTag_name());
        tv.setTextSize(12);
        tv.setTextColor(ArmsUtils.getColor(parent.getContext(), R.color.text_title));
        tv.setBackgroundResource(R.drawable.shape_tv_tag_video_detail);
        AutoUtils.auto(tv);
        return tv;
    }
}