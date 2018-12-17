package com.dohr.complaint.cell.UtilsClasses;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

public class NonScrollGridView extends GridView {

    public NonScrollGridView(Context context) {
        super(context);
    }
    public NonScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NonScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 3, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}
