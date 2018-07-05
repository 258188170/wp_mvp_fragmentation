package com.example.wp.wp_mvp_fragmentation.mvp.testviews.checkview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.wp.wp_mvp_fragmentation.R;

/**
 * Created by wangpeng .
 * 确定 取消动画
 */
public class CheckView extends View {
    private static final String TAG = "CheckView";
    private static final int ANIM_NULL = 0;//动画状态
    private static final int ANIM_CHECK = 1;//开启
    private static final int ANIM_UNCHECK = 2;//结束
    private  int mRadius = 50;//结束

    private Context mContext;
    private int mWidth, mHeight;
    private Handler mHandler;

    private Paint mPaint;
    private Bitmap mBitmap;

    private int animCurrentPage = -1;
    private int animMaxPage = 13;
    private int animDuration = 500;//动画时长
    private int animState = ANIM_NULL;//动画状态

    private boolean isCilck = false;

    public CheckView(Context context) {
        super(context);
        init(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.checkmark);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate();
                    if (animState == ANIM_NULL) return;
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--;
                    }
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                    Log.e(TAG, "handleMessage: count" + animCurrentPage);
                } else {
                    if (isCilck) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    /**
     * 绘制内容
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, mRadius, mPaint);
        //图像边长
        int sideLength = mBitmap.getHeight();
        Log.i(TAG, "onDraw: " + sideLength);
        //得到图像选区  和实际位置
        Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        Rect dst = new Rect(-40, -40, 40, 40);
        canvas.drawBitmap(mBitmap, src, dst, null);

    }

    /**
     * 选择
     */
    public void check() {
        if (animState != ANIM_NULL || isCilck) return;
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCilck = true;
    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (animState != ANIM_NULL || (!isCilck)) return;
        animState = ANIM_UNCHECK;
        animCurrentPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCilck = false;
    }

    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0) return;
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形颜色
     *
     * @param color
     */
    public void setBackgroundColor(@NonNull int color) {
        mPaint.setColor(color);
    }

    public void setmRadius(int mRadius) {
        this.mRadius = mRadius;
    }
}
