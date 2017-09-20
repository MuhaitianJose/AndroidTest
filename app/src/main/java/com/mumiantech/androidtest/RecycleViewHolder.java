package com.mumiantech.androidtest;

import android.support.v7.widget.RecyclerView;
import android.view.View;

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
