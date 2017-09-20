package com.mumiantech.androidtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Muhaitian on 2017/9/20.
 */

public class MonthDateView extends LinearLayout implements RecycleViewListener {
    private static final String TAG = MonthDateView.class.getSimpleName();

    private Context context;
    private DisplayMetrics displayMetrics;
    private Paint paint;
    //视图属性
    private int CircleColor;
    private int DateHeight;
    private int SelectedDayColor;
    private int NormalDayColor;
    private int DaySize;
    //数据
    private int CurrYear, CurrMonth, CurrDay;
    private int SelYear, SelMonth, SelDay;
    private int WeekRow;
    private float ColumnSize, RowSize;
    private int mTouchSlop;
    private DateClick dateClick;
    private int CircleRadius = 30;
    private int[][] daysString;

    private final int NUM_COLUMNS = 7;
    private int NUM_ROWS = 6;

    //recycleView
    RecyclerView recyclerView;
    CalenderAdapter calenderAdapter;
    List<CalenderDate> calenderDateList;

    public MonthDateView(Context context) {
        super(context);
    }

    public MonthDateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        displayMetrics = getResources().getDisplayMetrics();
//        initView(attrs);
    }

    public void initView() {
        recyclerView = findViewById(R.id.rv_calender);
        initData();
        calenderAdapter = new CalenderAdapter(calenderDateList, R.layout.calender_day_item, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(calenderAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        calenderAdapter.notifyDataSetChanged();
//        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MonthDateView);
//        try {
//            CircleColor = typedArray.getColor(R.styleable.MonthDateView_CircleColor, Color.TRANSPARENT);
//            DateHeight = (int) typedArray.getDimension(R.styleable.MonthDateView_DateHeight, 66);
//            DaySize = (int) typedArray.getDimension(R.styleable.MonthDateView_DaySize, 16);
//            SelectedDayColor = typedArray.getColor(R.styleable.MonthDateView_SelectedDayColor, Color.WHITE);
//            NormalDayColor = typedArray.getColor(R.styleable.MonthDateView_MormalDayColor, Color.BLACK);
//            Calendar calendar = Calendar.getInstance();
//            CurrYear = calendar.get(Calendar.YEAR);
//            CurrMonth = calendar.get(Calendar.MONTH);
//            CurrDay = calendar.get(Calendar.DATE);
//            SelYear = CurrYear;
//            SelMonth = CurrMonth;
//            SelDay = CurrDay;
//            RowSize = DateHeight;
//            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//            mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
//        } finally {
//            typedArray.recycle();
//        }
    }

    private void initData() {
        calenderDateList = new ArrayList<>();
        for (int i = 0; i < NUM_COLUMNS * NUM_ROWS; i++) {
            CalenderDate date = new CalenderDate();
            calenderDateList.add(date);
        }
        Calendar calendar = Calendar.getInstance();
        CurrYear = calendar.get(Calendar.YEAR);
        CurrMonth = calendar.get(Calendar.MONTH);
        CurrDay = calendar.get(Calendar.DATE);
        int mMonthdays = DateUtils.getMonthDays(CurrYear, CurrMonth);
        int weekNumber = DateUtils.getFirstDayWeek(CurrYear, CurrMonth);
        for (int day = 0; day < mMonthdays; day++) {
            calenderDateList.get(day + weekNumber - 1).setDay(day + 1);
            Log.d(TAG, "onCreate: calenderDateList["+(day+weekNumber-1)+"]="+calenderDateList.get(day+weekNumber-1).getDay());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = (int) (displayMetrics.density * 300);
        }
        NUM_ROWS = getMonthRowNumber();
        heightSize = NUM_ROWS * DateHeight;
        setMeasuredDimension(widthSize, heightSize);
    }


//    @Override
//    protected void onDraw(Canvas canvas) {
//        Log.d(TAG, "onDraw: ");
//        daysString = new int[6][7];
//        initSize();
//        Log.d(TAG, "onDraw: SelYear=" + SelYear);
//        Log.d(TAG, "onDraw: SelDay=" + SelMonth);
//        int mMonthdays = DateUtils.getMonthDays(SelYear, SelMonth);
//        int weekNumber = DateUtils.getFirstDayWeek(SelYear, SelMonth);
//        drawLines(canvas);
//        int column = 0, row = 0;
//        String dayString;
//        Log.d(TAG, "onDraw: mMonthdays=" + mMonthdays);
//        for (int day = 0; day < mMonthdays; day++) {
//            dayString = (day + 1) + "";
//            column = (day + weekNumber - 1) % 7;
//            row = (day + weekNumber - 1) / 7;
//            daysString[row][column] = day + 1;
//            paint.setTextSize(DaySize);
//
//            float startX = ColumnSize * column + (ColumnSize - paint.measureText(dayString)) / 2;
//            float startY = RowSize * row + RowSize / 2 - (paint.ascent() + paint.descent()) / 2;
//            Log.d(TAG, "onDraw: " + SelDay);
//            if (dayString.equals(SelDay + "")) {
//                Log.d(TAG, "onDraw: dayString equals" + dayString);
//                drawCircle(row, column, canvas, false);
//                paint.setColor(SelectedDayColor);
//            } else {
//                paint.setColor(NormalDayColor);
//                Log.d(TAG, "onDraw: unequal dayString" + dayString);
//            }
//            paint.setStyle(Paint.Style.FILL);
//            canvas.drawText(dayString, startX, startY, paint);
//        }
//    }

    /**
     * 绘制线条
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        int rightX = getWidth();
        int BottomY = getHeight();
        int rowCount = getMonthRowNumber();
        int columnCount = 7;
        Path path;
        float startX = 0;
        float endX = rightX;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(CircleColor);
        for (int row = 1; row <= rowCount; row++) {
            float startY = row * RowSize;
            path = new Path();
            path.moveTo(startX, startY);
            path.lineTo(endX, startY);
            canvas.drawPath(path, paint);
        }

        float startY = 0;
        float endY = BottomY;
        for (int column = 1; column < columnCount; column++) {
            startX = column * ColumnSize;
            path = new Path();
            path.moveTo(startX, startY);
            path.lineTo(startX, endY);
            canvas.drawPath(path, paint);
        }

    }

    /**
     * 绘制事务圆形
     *
     * @param row
     * @param column
     * @param canvas
     */
    private void drawCircle(int row, int column, Canvas canvas, boolean isSelected) {

        paint.setColor(CircleColor);
        paint.setStyle(Paint.Style.FILL);
        if (isSelected) {
            float circleX = (float) (ColumnSize * column + ColumnSize * 0.5);
            float circley = (float) (RowSize * row + RowSize * 0.5);
            canvas.drawCircle(circleX, circley, CircleRadius, paint);
        }
        float circleX = (float) (ColumnSize * column + ColumnSize * 0.5);
        float circley = (float) (RowSize * row + RowSize * 0.2);
        canvas.drawCircle(circleX, circley, CircleRadius, paint);
    }

    /**
     * 初始化列宽行高
     */
    private void initSize() {
        ColumnSize = getWidth() * 1.0F / NUM_COLUMNS;
        RowSize = DateHeight;
    }

    /**
     * 获取总共行数
     *
     * @return
     */
    private int getMonthRowNumber() {
        int mMonthDays = DateUtils.getMonthDays(SelYear, SelMonth);
        int weekNumber = DateUtils.getFirstDayWeek(SelYear, SelMonth);
        return (mMonthDays + weekNumber - 1) % 7 == 0 ? (mMonthDays + weekNumber - 1) / 7 : (mMonthDays + weekNumber - 1) / 7 + 1;
    }

    public void setDateClick(DateClick dateClick) {
        this.dateClick = dateClick;
    }

    @Override
    public void onClickItem(Object data, View item) {

    }

    @Override
    public void onConvertView(Object data, ViewHolder holder, List datas, int pisition) {
        Log.d(TAG, "onConvertView: ");
    }

    public interface DateClick {
        void onDateClick(int year, int month, int day);
    }
}
