package com.mumiantech.androidtest.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mumiantech.androidtest.R;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Muhaitian on 2017/9/21.
 */

public class CalendarView<T> extends LinearLayout implements View.OnClickListener, MonthDateView.DateClick, CalendarDialogView.DataSelected {


    TextView tvCalendarName;
    TextView tvCalendarValue;
    Button btMonthsSelected;
    //    RecyclerView rvCalender;
    MonthDateView mdvMonthdateview;
    private List<T> mDataList;
    private T mData;
    private Context context;
    private static final String TAG = CalendarView.class.getSimpleName();

    private CalendarDialogView calendarDialogView;

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View.inflate(context, R.layout.kindergarten_attendance_schedule, this);
        tvCalendarName = findViewById(R.id.tv_calendar_name);
        tvCalendarValue = findViewById(R.id.tv_calendar_value);
        btMonthsSelected = findViewById(R.id.bt_months_selected);
        btMonthsSelected.setOnClickListener(this);
        mdvMonthdateview = findViewById(R.id.mdv_monthdateview);
        Calendar calendar = Calendar.getInstance();
        mdvMonthdateview.initView(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        setSelectedMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        mdvMonthdateview.setDateClick(this);
        calendarDialogView = new CalendarDialogView(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
        calendarDialogView.setDataSelected(this);
    }

    public void setmDataList(List<T> mDataList) {
        this.mDataList = mDataList;
    }

    public void setmData(T mData) {
        this.mData = mData;
    }

    public void setCalendarName(String name) {
        tvCalendarName.setText(name);
    }

    public void setCalendarName(int name) {
        tvCalendarName.setText(name);
    }

    private void setSelectedMonth(int year,int month){
        tvCalendarValue.setText(year+"年"+(month+1)+"月");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_months_selected) {
            if (calendarDialogView != null && !calendarDialogView.isShowing()) {
                calendarDialogView.show();
            }
        }
    }

    @Override
    public void onDateClick(int year, int month, int day) {
        Log.d(TAG, "onDateClick: ");
    }

    @Override
    public void onDateSelect(int year, int month, int day) {
        if (mdvMonthdateview != null) {
            setSelectedMonth(year,month);
            mdvMonthdateview.changeDate(year, month, day);
        }
    }
}
