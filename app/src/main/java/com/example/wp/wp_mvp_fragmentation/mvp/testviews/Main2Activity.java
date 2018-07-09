package com.example.wp.wp_mvp_fragmentation.mvp.testviews;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.mvp.testviews.checkview.CheckView;
import com.example.wp.wp_mvp_fragmentation.mvp.testviews.radarview.RadarView;

public class Main2Activity extends AppCompatActivity {
    CheckView checkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RadarView mRadarView = (RadarView) findViewById(R.id.radar);
        mRadarView.setMainPaintColor(Color.BLUE);
        mRadarView.setTextPaintColor(Color.GRAY);
        mRadarView.setValuePaintColor(Color.RED);
    }
}
