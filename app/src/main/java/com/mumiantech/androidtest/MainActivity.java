package com.mumiantech.androidtest;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private static final String TAG = "MainActivity";
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kindergarten_attendance_schedule);
        MonthDateView  monthDateView = (MonthDateView) findViewById(R.id.muha8tan);
        monthDateView.initView();
//        Calendar calendar = Calendar.getInstance();
//        int CurrYear = calendar.get(Calendar.YEAR);
//        int CurrMonth = calendar.get(Calendar.MONTH);
//        Log.d(TAG, "onCreate: CurrYear="+CurrYear+" | CurrMonth="+CurrMonth);
//        int mMonthdays = DateUtils.getMonthDays(CurrYear, CurrMonth);
//        int weekNumber = DateUtils.getFirstDayWeek(CurrYear, CurrMonth);
//        List<CalenderDate> calenderDateList = new ArrayList<>();
//        for (int i = 0; i < 6 * 7; i++) {
//            CalenderDate date = new CalenderDate();
//            calenderDateList.add(date);
//        }
//
//        for(int day=0;day<mMonthdays;day++){
//            calenderDateList.get(day+weekNumber-1).setDay(day+1);
//            Log.d(TAG, "onCreate: calenderDateList["+(day+weekNumber-1)+"]="+calenderDateList.get(day+weekNumber-1).getDay());
//        }
    }
}
