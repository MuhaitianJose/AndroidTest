package com.mumiantech.androidtest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

/**
 * Created by Muhaitian on 2017/9/21.
 */

public class CalendarDialogView extends Dialog implements View.OnClickListener {

    private static final String TAG = CalendarDialogView.class.getSimpleName();
    private Button confirm;
    private Button cancel;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private DataSelected dataSelected;

    public CalendarDialogView(@NonNull Context context) {
        super(context);
    }

    public CalendarDialogView(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_dailog_view);
        initDialogView();
    }

    private void initDialogView() {
        confirm = findViewById(R.id.bt_confirm);
        confirm.setOnClickListener(this);
        cancel = findViewById(R.id.bt_cancel);
        cancel.setOnClickListener(this);
        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ");
        if (view.getId() == R.id.bt_cancel) {
            dismiss();
        } else if (view.getId() == R.id.bt_confirm) {
           if(dataSelected!=null){
               dataSelected.onDateSelect(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
           }
           dismiss();
        }
    }

    public void setDataSelected(DataSelected dataSelected) {
        this.dataSelected = dataSelected;
    }

    public interface DataSelected{
        void onDateSelect(int year,int month,int day);
    }
}
