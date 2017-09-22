package com.mumiantech.androidtest.widget;

import android.content.Context;

import android.support.annotation.Nullable;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.mumiantech.androidtest.bean.CalenderDate;
import com.mumiantech.androidtest.adapter.CommonAdapter;
import com.mumiantech.androidtest.utils.DateUtils;
import com.mumiantech.androidtest.R;
import com.mumiantech.androidtest.bean.ViewHolder;

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

    //数据
    private int CurrYear, CurrMonth, CurrDay;
    private final int NUM_COLUMNS = 7;
    private int NUM_ROWS = 6;

    //recycleView
    RecyclerView recyclerView;
    CommonAdapter calenderAdapter;
    List<CalenderDate> calenderDateList;
    private DateClick dateClick;

    public MonthDateView(Context context) {
        super(context);
    }

    public MonthDateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Calendar calendar = java.util.Calendar.getInstance();
        CurrYear = calendar.get(java.util.Calendar.YEAR);
        CurrMonth = calendar.get(java.util.Calendar.MONTH);
        CurrDay = calendar.get(java.util.Calendar.DATE);
    }


    public void initView() {
        recyclerView = this.findViewById(R.id.rv_calender);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        calenderAdapter = new CommonAdapter(calenderDateList, R.layout.calender_day_item, this);
        recyclerView.setAdapter(calenderAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        calenderAdapter.notifyDataSetChanged();

    }

    public void initData(int year, int month) {
        if (calenderDateList == null) {
            calenderDateList = new ArrayList<>();
            for (int i = 0; i < NUM_COLUMNS * NUM_ROWS; i++) {
                CalenderDate date = new CalenderDate(year, month, 0);
                calenderDateList.add(date);
            }
        }
        for (int i = 0; i < NUM_COLUMNS * NUM_ROWS; i++) {
            calenderDateList.get(i).setInVisible(true);
        }

        int mMonthdays = DateUtils.getMonthDays(year, month);
        int weekNumber = DateUtils.getFirstDayWeek(year, month);
        for (int day = 0; day < mMonthdays; day++) {
            calenderDateList.get(day + weekNumber - 1).setDay(day + 1);
            calenderDateList.get(day + weekNumber - 1).setInVisible(false);
        }
    }

    public void setDateClick(DateClick dateClick) {
        this.dateClick = dateClick;
    }

    @Override
    public void onClickItem(Object data, View item) {
        CalenderDate date = (CalenderDate) data;
        resetItemBackground();
        ((ViewHolder) item.getTag()).getView(R.id.layout_day).setSelected(true);
        if (dateClick != null) {
            dateClick.onDateClick(date.getYear(), date.getMonth(), date.getDay());
        }
        Log.d(TAG, "resetItemBackground: " + date.getYear() + "-" + date.getMonth() + "-" + date.getDay());

    }

    private void resetItemBackground() {
        if (recyclerView != null) {
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View root = recyclerView.getChildAt(i);
                LinearLayout layout_day = root.findViewById(R.id.layout_day);
                layout_day.setSelected(false);
            }
        }
    }

    @Override
    public void onConvertView(Object data, ViewHolder holder, List datas, int pisition) {
        CalenderDate date = (CalenderDate) data;
        if (date.isInVisible()) {
            holder.setVisible(R.id.tv_lunar_day, View.INVISIBLE);
            holder.setVisible(R.id.tv_gregorian_day, View.INVISIBLE);
            holder.setVisible(R.id.tv_attendance_flag, View.INVISIBLE);
            holder.getView(R.id.rl_day_item).setClickable(false);
        } else {
            holder.setText(R.id.tv_gregorian_day, date.getDay() + "");
            holder.setText(R.id.tv_lunar_day, date.getLunarCalendar() + "");
            holder.getView(R.id.rl_day_item).setClickable(true);
        }
        Log.d(TAG, "onConvertView: ----------------");
    }

    public interface DateClick {
        void onDateClick(int year, int month, int day);
    }
}
