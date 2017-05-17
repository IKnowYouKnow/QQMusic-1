package com.youyan.qqmusic.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.youyan.qqmusic.R;

public class MenuItem extends LinearLayout {

    public static final int TYPE_NONE = 0;
    public static final int TYPE_HOT_POINT = 1;
    public static final int TYPE_ON_OFF = 2;

    private int mMenuType;
    private SwitchButton mSwitch;
    private View mRedPoint;
    private TextView mMenuTitle;

    public MenuItem(Context context) {
        this(context, null);
    }

    public MenuItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.menu_item, this, true);

        mSwitch = (SwitchButton) findViewById(R.id.item_switch);
        mRedPoint = findViewById(R.id.red_point);
        mMenuTitle = (TextView) findViewById(R.id.menu_title);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MenuItem, 0, 0);
        int menuType = ta.getInt(R.styleable.MenuItem_menuType, 0);
        String menuTitle = ta.getString(R.styleable.MenuItem_menuTitle);
        ta.recycle();

        initMenu(menuType, menuTitle);
    }

    private void initMenu(int type, String title) {
//        mSwitch.setButtonDrawable(R.drawable.switch_btn);
//        mSwitch.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mSwitch.setClickable(true);

        mMenuTitle.setText(title);
        setMenuType(type);
    }

    public void setMenuType(int type) {
        mMenuType = type;
        switch (mMenuType) {
            case TYPE_NONE:
                mSwitch.setVisibility(GONE);
                mRedPoint.setVisibility(GONE);
                break;
            case TYPE_HOT_POINT:
                mSwitch.setVisibility(GONE);
                mRedPoint.setVisibility(VISIBLE);
                break;
            case TYPE_ON_OFF:
                mSwitch.setVisibility(VISIBLE);
                mRedPoint.setVisibility(GONE);
                break;
            default:
                mSwitch.setVisibility(GONE);
                mRedPoint.setVisibility(GONE);
                break;
        }
    }

    public void setMenuTitle(String title) {
        mMenuTitle.setText(title);
    }

    public void showRedPoint(boolean show) {
        mRedPoint.setVisibility(show ? VISIBLE : GONE);
    }

    public void setOnCheckChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        mSwitch.setOnCheckedChangeListener(listener);
    }
}
