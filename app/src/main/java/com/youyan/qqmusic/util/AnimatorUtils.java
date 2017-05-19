package com.youyan.qqmusic.util;

import android.animation.ValueAnimator;
import android.view.View;

public class AnimatorUtils {

    public static ValueAnimator valueHeight(final View view, int... values) {
        ValueAnimator animator = ValueAnimator.ofInt(values);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                view.getLayoutParams().height = height;
                view.requestLayout();
            }
        });
        return animator;
    }

    public static ValueAnimator valueWidth(final View view, int... values) {
        ValueAnimator animator = ValueAnimator.ofInt(values);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int width = (int) animation.getAnimatedValue();
                view.getLayoutParams().width = width;
                view.requestLayout();
            }
        });
        return animator;
    }
}
