package com.mumiantech.androidtest;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CalendarDialogView.DataSelected{
    private static final String TAG = "MainActivity";
    TextView textView;
    CalendarDialogView calendarDialogView;
    DatePickerDialog datePickerDialog;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DatePickerDialog mDatePickerDialog = new DatePickerDialog(getApplicationContext());
//        mDatePickerDialog.show();
        Button button = (Button) findViewById(R.id.buttonPanel);
       calendarDialogView = new CalendarDialogView(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
        datePickerDialog = new DatePickerDialog(this);
        calendarDialogView.setDataSelected(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                datePickerDialog.show();
                calendarDialogView.show();
            }
        });

//        ButterKnife.bind(this);
    }

    @Override
    public void onDateSelect(int year, int month, int day) {
        Log.d(TAG, "onDateSelect: "+year+"-"+month+"-"+day);
    }
}
