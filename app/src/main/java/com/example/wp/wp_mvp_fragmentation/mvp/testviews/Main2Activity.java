package com.example.wp.wp_mvp_fragmentation.mvp.testviews;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.mvp.testviews.animator.CircularAnim;
import com.example.wp.wp_mvp_fragmentation.mvp.testviews.checkview.CheckView;

public class Main2Activity extends AppCompatActivity {
    CheckView checkview;
    private ProgressBar mProgressBar;
    private Button mChangeBtn;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

}
