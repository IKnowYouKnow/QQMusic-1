package com.youyan.qqmusic.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MySlidingPaneLayout extends SlidingPaneLayout {

    private ViewPager mConflictViewPager;
    private int mLastX;

    public MySlidingPaneLayout(Context context) {
        super(context);
    }

    public MySlidingPaneLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setConflictViewPager(ViewPager viewPager) {
        mConflictViewPager = viewPager;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mConflictViewPager != null) {
                    int currentX = (int) ev.getX();
                    int deltaX = currentX - mLastX;
                    if (deltaX > 0 && mConflictViewPager.getCurrentItem() ==  0) {
                        intercept = true;
                    }
                } else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        mLastX = (int) ev.getX();

        return intercept;
    }
}
