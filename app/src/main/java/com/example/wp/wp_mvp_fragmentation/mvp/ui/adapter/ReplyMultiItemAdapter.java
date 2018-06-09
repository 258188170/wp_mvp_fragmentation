package com.example.wp.wp_mvp_fragmentation.mvp.ui.adapter;

import android.widget.LinearLayout;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wp.wp_mvp_fragmentation.R;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.Reply;
import com.example.wp.wp_mvp_fragmentation.app.data.entry.video.ReplyMultiItem;
import com.jess.arms.http.imageloader.ImageConfig;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

/**
 * Created by WangPeng on 2018/6/9.
 */
public class ReplyMultiItemAdapter extends BaseMultiItemQuickAdapter<ReplyMultiItem, BaseViewHolder> {


    private int[] mLevelIcons = new int[]{
            R.mipmap.ic_lv0_large
            , R.mipmap.ic_lv1_large
            , R.mipmap.ic_lv2_large
            , R.mipmap.ic_lv3_large
            , R.mipmap.ic_lv4_large
            , R.mipmap.ic_lv5_large
            , R.mipmap.ic_lv6_large
            , R.mipmap.ic_lv7_large
            , R.mipmap.ic_lv8_large
            , R.mipmap.ic_lv9_large
    };

    public ReplyMultiItemAdapter(List<ReplyMultiItem> data) {
        super(data);
        addItemType(ReplyMultiItem.ITEM, R.layout.item_item_video_detail);
        addItemType(ReplyMultiItem.TITLE_HOTS, R.layout.item_title_hots_video_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReplyMultiItem item) {
        switch (item.getItemType()) {
            case ReplyMultiItem.ITEM:
                Reply.DataBean.RepliesBean repliesBean = item.getReplies();
                if (repliesBean == null) {
                    return;
                }
                Reply.DataBean.RepliesBean.MemberBeanXX members = repliesBean.getMember();
                ArmsUtils.obtainAppComponentFromContext(mContext).imageLoader()
                        .loadImage(mContext, ImageConfigImpl.builder().url(members.getAvatar())
                                .imageView(helper.getView(R.id.iv_avatar))
                                .transformation(new CircleCrop()).build());
                helper.setText(R.id.tv_uname,members.getUname())
                        .setText(R.id.iv_level,mLevelIcons[members.getLevel_info().getCurrent_level()])
                        .setText(R.id.tv_content, repliesBean.getContent().getMessage())
                        .setText(R.id.tv_count, repliesBean.getCount() + "")
                        .setVisible(R.id.line, getData().indexOf(item) + 1 < (getData().size()) ? getData().get(getData().indexOf(item) + 1).getItemType() == ReplyMultiItem.TITLE_HOTS ? false : true : true);

                LinearLayout llReplie = helper.getView(R.id.ll_replies);
                llReplie.removeAllViews();



                break;
            case ReplyMultiItem.TITLE_HOTS:
                break;
            default:
                break;
        }
    }
}
