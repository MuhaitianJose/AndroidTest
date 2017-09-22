package com.mumiantech.androidtest.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mumiantech.androidtest.bean.RecycleViewHolder;
import com.mumiantech.androidtest.widget.RecycleViewListener;

import java.util.List;

/**
 * @author Yep
 * @date 9/19/2017.
 */

public class CommonAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {
    private static final String TAG = "CommonAdapter";
    protected List<T> mDatas;

    private int itemLayoutRes;

    protected RecycleViewListener<T> mListener;

    public CommonAdapter(List<T> datas, @LayoutRes int id, RecycleViewListener<T> listener) {
        Log.d(TAG, "CommonAdapter: ");
        this.mDatas = datas;
        this.itemLayoutRes = id;
        this.mListener = listener;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayoutRes, parent, false);
        RecycleViewHolder holder = new RecycleViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        final T data = mDatas.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClickItem(data, view);
                }
            }
        });

        if (mListener != null) {
            mListener.onConvertView(data, holder.holder, mDatas, position);
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }
}
