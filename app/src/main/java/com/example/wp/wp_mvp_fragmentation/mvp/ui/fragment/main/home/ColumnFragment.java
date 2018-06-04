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
 * 专栏
 */
public class ColumnFragment extends MySupportFragment {
    public static ColumnFragment newInstance() {

        Bundle args = new Bundle();

        ColumnFragment fragment = new ColumnFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_column_main_home, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
