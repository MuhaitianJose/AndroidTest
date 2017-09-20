package com.mumiantech.androidtest;

import java.util.Calendar;

/**
 * Created by Muhaitian on 20/09/2017.
 */

public class CalenderDate {
    private int Year;
    private int Month;
    private int Day;
    private int lunarCalendar;

    public CalenderDate() {
        this(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DATE));
    }

    public CalenderDate(int year, int month, int day) {
        Year = year;
        Month = month;
        Day = day;

    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public int getLunarCalendar() {
        return lunarCalendar;
    }

    public void setLunarCalendar(int lunarCalendar) {
        this.lunarCalendar = lunarCalendar;
    }
}
