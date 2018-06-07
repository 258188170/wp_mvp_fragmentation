package com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wp.wp_mvp_fragmentation.app.base.MySupportFragment;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.Reply;
import com.jess.arms.di.component.AppComponent;

/**
 * Created by wangpeng .
 */
public class ReplyFragment extends MySupportFragment {
    public static ReplyFragment newInstance(Reply reply,String replyCountStr) {

        Bundle args = new Bundle();

        ReplyFragment fragment = new ReplyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
