package com.mumiantech.androidtest.adapter;

import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.ViewGroup;

import com.mumiantech.androidtest.bean.CalenderDate;
import com.mumiantech.androidtest.bean.RecycleViewHolder;
import com.mumiantech.androidtest.widget.RecycleViewListener;

import java.util.List;

/**
 * Created by Muhaitian on 20/09/2017.
 */

public class CalenderAdapter extends CommonAdapter<CalenderDate> {
    private static String TAG = "CalenderAdapter";
    public CalenderAdapter(List datas, @LayoutRes int id, RecycleViewListener listener) {
        super(datas, id, listener);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        super.onBindViewHolder(holder, position);
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        return super.onCreateViewHolder(parent, viewType);
    }
}
