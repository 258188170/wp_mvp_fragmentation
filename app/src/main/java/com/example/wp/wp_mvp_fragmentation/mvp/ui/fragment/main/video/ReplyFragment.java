package com.example.wp.wp_mvp_fragmentation.mvp.ui.fragment.main.video;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.app.base.MySupportFragment;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.Reply;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.ReplyMultiItem;
import com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter.ReplyMultiItemAdapter;
import com.jess.arms.di.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wangpeng .
 * 评论
 */

@SuppressLint("ValidFragment")
public class ReplyFragment extends MySupportFragment {

    private List<ReplyMultiItem> mData = new ArrayList<>();
    private Reply mReply;
    private String mReplyCountStr;
    private View mRootView = null;
    @BindView(R.id.rv_reply)
    RecyclerView mRvReply;
    @BindView(R.id.ll_bottom)
    LinearLayout mLlBottom;

    public ReplyFragment(Reply reply, String replyCountStr) {
        this.mReply = reply;
        this.mReplyCountStr = replyCountStr;
    }

    public static ReplyFragment newInstance(Reply reply, String replyCountStr) {
        return new ReplyFragment(reply, replyCountStr);
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_reply_video_detail, container, false);
        }
        return mRootView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (mReply == null || mReply.getData() == null) {
            return;
        }
        Reply.DataBean dataBean = mReply.getData();
        initVideoReply(dataBean);
        initRecyclerView();
    }

    private void initVideoReply(Reply.DataBean bean) {
        final List<Reply.DataBean.RepliesBean> hots = bean.getHots();
        int size = hots.size();
        if (hots != null && size > 0) {
            for (int i = 0; i < size; i++) {
                mData.add(new ReplyMultiItem(ReplyMultiItem.ITEM, hots.get(i)));
            }
            mData.add(new ReplyMultiItem(ReplyMultiItem.TITLE_HOTS));
        }
        List<Reply.DataBean.RepliesBean> replies = bean.getReplies();
        if (replies != null && replies.size() > 0) {
            for (int i = 0; i < replies.size(); i++) {
                mData.add(new ReplyMultiItem(ReplyMultiItem.ITEM, replies.get(i)));
            }
        }
    }

    /**
     * 加载recycleView
     */
    private void initRecyclerView() {
        View headerView = View.inflate(_mActivity, R.layout.item_title_reply_video_detail, null);
        ((TextView) headerView.findViewById(R.id.tv_reply_count)).setText(mReplyCountStr);

        ReplyMultiItemAdapter adapter = new ReplyMultiItemAdapter(mData);
        adapter.addHeaderView(headerView);
        mRvReply.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRvReply.setAdapter(adapter);
    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
