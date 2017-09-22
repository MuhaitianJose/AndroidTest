package com.mumiantech.androidtest.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mumiantech.androidtest.bean.ViewHolder;

/**
 * @author Yep
 * @date 9/19/2017.
 */

public class RecycleViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder holder;

    public RecycleViewHolder(View itemView) {
        super(itemView);
        holder = ViewHolder.getViewHolder(itemView);
    }
}
