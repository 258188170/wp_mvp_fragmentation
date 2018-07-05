package com.example.wp.wp_mvp_fragmentation.mvp.testviews.radarview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangpeng .
 */
public class RadarView extends View {
    private int count = 6;//蜘蛛网格中正方形的个数
    private float angle = (float) (Math.PI*2/count);//Math.PI记录的圆周率
    private float radius;//网格最大半径

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
