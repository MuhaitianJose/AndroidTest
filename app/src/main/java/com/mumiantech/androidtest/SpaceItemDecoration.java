package com.mumiantech.androidtest;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Muhaitian on 2017/9/21.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    int space;
    public SpaceItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
    }
}
