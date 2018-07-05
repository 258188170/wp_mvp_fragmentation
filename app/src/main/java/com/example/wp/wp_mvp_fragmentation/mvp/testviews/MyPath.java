package com.example.wp.wp_mvp_fragmentation.mvp.testviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangpeng .
 */
public class MyPath extends View {
    private Paint mPaint;
    private int mWidth, mHeight;

    public MyPath(Context context) {
        super(context);
        init();
    }


    public MyPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
//        Path path = new Path();
//        path.lineTo(200,200);
//        path.lineTo(200,0);
//        canvas.drawPath(path,mPaint);
//        path.lineTo(200,200);
//        path.setLastPoint(200,100);
//        path.lineTo(200,0);
//        path.close();
//        canvas.drawPath(path,mPaint);
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
//        path.setLastPoint(-300,300);
//        canvas.drawPath(path, mPaint);
//        canvas.translate(mWidth / 2, mHeight / 2);
//        canvas.scale(1,-1);
//        Path path = new Path();
//        Path src = new Path();
//        path.addRect(-200,-200,200,200,Path.Direction.CW);
//        src.addCircle(0,0,100, Path.Direction.CW);
//        path.addPath(src,0,200);
//        canvas.drawPath(path,mPaint);
//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
//        Path path = new Path();
//        path.lineTo(100,100);
//        RectF rectF = new RectF(0,0,300,300);
//
////        path.addArc(rectF,0,350);
//        path.arcTo(rectF,0,350);
//        canvas.drawPath(path,mPaint);
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();                     // path中添加一个圆形(圆心在坐标原点)
        path.addCircle(0,0,100, Path.Direction.CW);
        Path src = new Path();
        src.addRect(-200,-200,200,200, Path.Direction.CW);
        path.offset(300,0,src);
        canvas.drawPath(path,mPaint);               // 绘制path

        mPaint.setColor(Color.BLUE);                // 更改画笔颜色

        canvas.drawPath(src,mPaint);                // 绘制src
    }
}
