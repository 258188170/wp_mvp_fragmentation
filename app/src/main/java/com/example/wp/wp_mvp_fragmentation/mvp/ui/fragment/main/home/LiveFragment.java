package com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.app.base.MySupportFragment;
import com.jess.arms.di.component.AppComponent;

/**
 * Created by WangPeng on 2018/6/3.
 * 直播
 */
public class LiveFragment extends MySupportFragment {
    private View mRootView;
    public static LiveFragment newInstance() {

        Bundle args = new Bundle();

        LiveFragment fragment = new LiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null)
            mRootView = inflater.inflate(R.layout.fragment_live_main_home, container, false);
        return mRootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
