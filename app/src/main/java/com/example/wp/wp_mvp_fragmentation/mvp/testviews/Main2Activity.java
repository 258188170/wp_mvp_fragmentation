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

        CircularAnim.init(700, 618, R.color.colorPrimary);
        // optional. set the default duration OnAnimatorDeployListener.
        CircularAnim.initDefaultDeployAnimators(
                new CircularAnim.OnAnimatorDeployListener() {
                    @Override
                    public void deployAnimator(Animator animator) {
                        animator.setInterpolator(new AccelerateInterpolator());
                    }
                },
                new CircularAnim.OnAnimatorDeployListener() {
                    @Override
                    public void deployAnimator(Animator animator) {
                        animator.setInterpolator(new DecelerateInterpolator());
                    }
                },
                null,
                null);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mChangeBtn = (Button) findViewById(R.id.change_btn);
        frameLayout = findViewById(R.id.fl);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                // 收缩按钮
                CircularAnim.hide(frameLayout).go();
            }
        });

        mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.GONE);
                // 伸展按钮
                CircularAnim.show(frameLayout).go();
            }
        });
    }


}
