package com.drchip.android.views.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RectangleLinearLayout extends LinearLayout{

    public RectangleLinearLayout(Context context) {
        super(context);
    }

    public RectangleLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectangleLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RectangleLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        int width = getMeasuredWidth();
        int height = width;
        setMeasuredDimension(width, height);
    }
}
