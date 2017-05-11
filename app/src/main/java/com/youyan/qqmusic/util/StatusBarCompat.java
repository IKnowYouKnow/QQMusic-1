package com.youyan.qqmusic.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.youyan.qqmusic.R;

public class StatusBarCompat {

    private static int getStatusBarHeight(Context context) {
        int height = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            height = context.getResources().getDimensionPixelSize(resId);
        }
        return height;
    }

    public static void setStatusBarColor(Activity activity) {
        int color = ContextCompat.getColor(activity, R.color.colorPrimaryDark);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
            View contentView = parent.getChildAt(0);
            if (contentView != null) {
                int statusBarHeight = getStatusBarHeight(activity);
                contentView.setPadding(0, statusBarHeight, 0, 0);
                View statusBarHolder = new View(activity);
                ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
                statusBarHolder.setBackgroundColor(color);
                parent.addView(statusBarHolder, params);
            }
        }
    }

    public static View setStatusHolder(View parent, int resId) {
        Context context = parent.getContext();
        int statusBarHeight = getStatusBarHeight(context);

        FrameLayout root = new FrameLayout(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        root.setLayoutParams(params);

        View statusBarHolder = new View(context);
        ViewGroup.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusBarHolder.setBackgroundResource(resId);
        root.addView(statusBarHolder, params2);
        ViewGroup viewGroup = (ViewGroup) parent.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(parent);
        }
        parent.setPadding(0, statusBarHeight, 0, 0);
        root.addView(parent);
        return root;
    }
}
