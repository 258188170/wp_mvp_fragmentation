package com.example.wp.wp_mvp_fragmentation.mvp.testviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.mvp.testviews.checkview.CheckView;

public class Main2Activity extends AppCompatActivity {
    CheckView checkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }
}
