package com.mumiantech.androidtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by wangkang001 on 2017/9/20.
 */

public class WeekDayView extends View {
    private static final String TAG = WeekDayView.class.getSimpleName();

    //字体颜色
    private int WeekDayColor = Color.parseColor("#BDBDBD");
    //上横线颜色
    private int mTopLineColor = Color.parseColor("#CCE4F2");
    //下横线颜色
    private int mBottomLineColor = Color.parseColor("#CCE4F2");
    private int mStrokeWidth = 4;
    private int mWeekSize = 14;
    private Paint mPaint;
    private DisplayMetrics mDisplayMetrics;
    private String[] WeekString = {"日", "一", "二", "三", "四", "五", "六"};
    private Context mContext;

    public WeekDayView(Context context) {
        super(context);
    }

    public WeekDayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mDisplayMetrics = getResources().getDisplayMetrics();
        mPaint = new Paint();
        initView(attrs);
    }

    private void initView(AttributeSet attributeSet) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = mDisplayMetrics.densityDpi * 30;
        }
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = mDisplayMetrics.densityDpi * 300;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        mPaint.setColor(mTopLineColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawLine(0, 0, width, 0, mPaint);

        mPaint.setColor(mBottomLineColor);
        canvas.drawLine(0, height, width, height, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(WeekDayColor);
        mPaint.setTextSize(mWeekSize * mDisplayMetrics.scaledDensity);
        int columWidth = width / 7;
        for (int i = 0; i < WeekString.length; i++) {
            String text = WeekString[i];
            int fontWidth = (int) mPaint.measureText(text);
            int startX = columWidth * i + (columWidth - fontWidth) / 2;
            int startY = (int) (height / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
            canvas.drawText(text, startX, startY, mPaint);
        }
    }
}
