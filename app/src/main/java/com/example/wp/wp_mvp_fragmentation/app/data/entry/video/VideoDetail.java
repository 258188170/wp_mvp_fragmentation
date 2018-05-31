package com.example.wp.wp_mvp_fragmentation.app.data.entry.video;


public class VideoDetail {

    private Summary mSummary;
    private Reply mReply;

    public VideoDetail(Summary summary, Reply reply) {
        mSummary = summary;
        mReply = reply;
    }

    public Summary getSummary() {
        return mSummary;
    }

    public void setSummary(Summary summary) {
        mSummary = summary;
    }

    public Reply getReply() {
        return mReply;
    }

    public void setReply(Reply reply) {
        mReply = reply;
    }
}
