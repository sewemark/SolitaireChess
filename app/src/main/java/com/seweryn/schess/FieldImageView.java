package com.seweryn.schess;

import android.widget.ImageView;
import android.content.Context;
import android.util.AttributeSet;
import java.lang.*;
/**
 * Created by sew on 2015-10-25.
 */

public class FieldImageView extends ImageView {
    public FieldImageView(Context context) {
        super(context);
    }

    public FieldImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public FieldImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
