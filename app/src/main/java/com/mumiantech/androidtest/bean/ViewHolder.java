package com.mumiantech.androidtest.bean;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Yep
 * @date 9/19/2017.
 */

public class ViewHolder {

    public View mConvertView;

    private SparseArray<View> mViewArray;

    public static ViewHolder getViewHolder(View mConvertView) {
        if(mConvertView == null) {
            throw new NullPointerException("ViewHolder.getViewHolder convertView null");
        }
        ViewHolder holder;
        if(mConvertView.getTag() == null) {
            holder = new ViewHolder(mConvertView);
        } else {
            holder = (ViewHolder) mConvertView.getTag();
        }
        return holder;
    }

    public ViewHolder(View convertView) {
        this.mConvertView = convertView;
        mViewArray = new SparseArray<>();
        mConvertView.setTag(this);
    }

    public <T extends View> T getView(int id) {
        Log.d("ViewHolder", "getView: mConvertView="+mConvertView);
        View view = mViewArray.get(id);
        if(view == null) {
            view = mConvertView.findViewById(id);
            mViewArray.put(id, view);
        }
        return (T) view;
    }

    public void setText(int id, String text) {
        TextView mTvText = getView(id);
        mTvText.setText(text);
    }

    public void setText(int id, @StringRes int text) {
        TextView mTvText = getView(id);
        mTvText.setText(text);
    }

    public void setImage(int id, @DrawableRes int drawable) {
        ImageView mIvImage = getView(id);
        mIvImage.setImageResource(drawable);
    }

    public void setImage(int id, String url) {
        ImageView mIvImage = getView(id);

        // TODO: 9/19/2017 load image
    }

    public void setVisible(int id, int visible) {
        View view = getView(id);
        view.setVisibility(visible);
    }
}
