package com.mumiantech.androidtest;

import android.view.View;

import java.util.List;

/**
 * @author Yep
 * @date 9/19/2017.
 */

public interface RecycleViewListener<T> {
    void onClickItem(T data, View item);

    void onConvertView(T data, ViewHolder holder, List<T> datas, int pisition);
}
