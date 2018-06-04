package com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home.ColumnFragment;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home.LiveFragment;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home.RecommendFragment;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.home.TrackCartoonFragment;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

/**
 * Created by WangPeng on 2018/6/3.
 */
public class MainHomeFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTabTitles;
    private Context mContext;

    public MainHomeFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mTabTitles = ArmsUtils.getStringArray(mContext, R.array.tab_titles_main_home);
        mFragments.add(LiveFragment.newInstance());
        mFragments.add(RecommendFragment.newInstance());
        mFragments.add(TrackCartoonFragment.newInstance());
        mFragments.add(ColumnFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];

    }
}
