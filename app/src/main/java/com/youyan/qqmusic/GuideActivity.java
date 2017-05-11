package com.youyan.qqmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.youyan.qqmusic.base.BaseActivity;
import com.youyan.qqmusic.util.AppConfig;
import com.youyan.qqmusic.util.DimenUtils;
import com.youyan.qqmusic.util.ShareSDKUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.guide_view_pager) ViewPager mGuideViewPager;
    @Bind(R.id.guide_circle_group) LinearLayout mGuideCircleGroup;

    private static final int[] mGuideImages = {
      R.drawable.guide_fornew_page_1,
      R.drawable.guide_fornew_page_2,
      R.drawable.guide_fornew_page_3,
      R.drawable.guide_fornew_page_4,
      R.drawable.guide_fornew_page_5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
//        ButterKnife.bind(this);
        createCircles();
        mGuideViewPager.setAdapter(new GuidePagerAdapter());
        mGuideViewPager.setOnPageChangeListener(this);
    }

    private void createCircles() {
        for (int i = 0; i < mGuideImages.length; i++) {
            ImageView imageView = new ImageView(mActivity);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DimenUtils.dp2px(5),
                    DimenUtils.dp2px(5));
            params.rightMargin = DimenUtils.dp2px(8);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(R.drawable.guide_circle_selector);
            mGuideCircleGroup.addView(imageView);
        }
        mGuideCircleGroup.getChildAt(0).setSelected(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mGuideImages.length; i++) {
            mGuideCircleGroup.getChildAt(i).setSelected(false);
        }
        mGuideCircleGroup.getChildAt(position).setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onBackPressed() {
        gotoMainActivity();
        AppConfig.setNeedGuide(false);
    }

    private void gotoMainActivity() {
        startActivity(new Intent(mActivity, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }


    class GuidePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mGuideImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getLayoutInflater().inflate(R.layout.layout_guide_page, container, false);
            GuidePageHolder pageHolder = new GuidePageHolder();
            ButterKnife.bind(pageHolder, view);
            pageHolder.guide_page.setBackgroundResource(mGuideImages[position]);
            pageHolder.gotoapp_btn.setVisibility(position == mGuideImages.length - 1 ? View.VISIBLE : View.GONE);
            pageHolder.btn_skip.setVisibility(position == mGuideImages.length - 1 ? View.GONE : View.VISIBLE);
            container.addView(view);
            return view;
        }
    }

    class GuidePageHolder implements View.OnClickListener {
//        @Bind(R.id.guide_share_btn) ImageView btn_share;
        @Bind(R.id.guide_skip_btn) ImageView btn_skip;
        @Bind(R.id.guide_page) RelativeLayout guide_page;
        @Bind(R.id.guide_gotoapp_btn) ImageView gotoapp_btn;

        @OnClick({R.id.guide_skip_btn, R.id.guide_share_btn, R.id.guide_gotoapp_btn})
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.guide_skip_btn:
                case R.id.guide_gotoapp_btn:
                    AppConfig.setNeedGuide(false);
                    gotoMainActivity();
                    break;
                case R.id.guide_share_btn:
                    ShareSDKUtils.oneKeyShare(mActivity, "分享QQ音乐", "QQ音乐是腾讯公司推出的一款免费音乐服务...",
                            "https://y.qq.com/");
                    break;
                default:
                    break;
            }
        }
    }
}
