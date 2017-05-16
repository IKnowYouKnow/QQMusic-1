package com.youyan.qqmusic.widget;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youyan.qqmusic.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MiniBar implements View.OnClickListener {

    public interface OnMediaControlListener {
        void onClickBtnPlay();
        void onClickBtnPlayList();
    }

    private View mMinibarView;
    private Context mContext;
    private OnMediaControlListener mControlListener;
    private RotateAnimation mRotateAnim;

    @Bind(R.id.minibar_default_text) TextView mDefaultText;
    @Bind(R.id.minibar_album) CircleImageView mAlbumImage;
    @Bind(R.id.minibar_songname) TextView mSongName;
    @Bind(R.id.minibar_artistname) TextView mArtistName;
    @Bind(R.id.minibar_btn_play) ImageView mBtnPlay;
    @Bind(R.id.minibar_btn_playlist) ImageView mBtnPlayList;
    @Bind(R.id.minibar_circle) ImageView mLoadCircle;
    @Bind(R.id.minibar_album_info) RelativeLayout mAlbumInfo;

    public MiniBar(View minibar) {
        mMinibarView = minibar;
        mContext = mMinibarView.getContext();
        ButterKnife.bind(this, mMinibarView);
        init();
    }

    private void init() {
        mRotateAnim = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.rotate);
    }

    @OnClick({R.id.minibar_btn_play, R.id.minibar_btn_playlist})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minibar_btn_play:
                if (mControlListener != null) {
                    mControlListener.onClickBtnPlay();
                }
                break;
            case R.id.minibar_btn_playlist:
                if (mControlListener != null) {
                    mControlListener.onClickBtnPlayList();
                }
                break;
        }
    }

    public void startLoading() {
        mLoadCircle.clearAnimation();
        mLoadCircle.setImageResource(R.drawable.minibar_loading);
        mLoadCircle.startAnimation(mRotateAnim);
    }

    public void stopLoading() {
        mLoadCircle.clearAnimation();
        mLoadCircle.setImageResource(R.drawable.minibar_circle);
    }


    public void setMediaControlListener(OnMediaControlListener l) {
        mControlListener = l;
    }

    public void setAlbumImage(String url) {
        Glide.with(mContext).load(url)
                .placeholder(R.drawable.minibar_album_default)
                .error(R.drawable.minibar_album_default)
                .crossFade()
                .into(mAlbumImage);
    }

    public void setSongName(String songName) {
        mSongName.setText(songName);
    }

    public void setArtistName(String artistName) {
        mArtistName.setText(artistName);
    }

    public void showDefaultText(boolean visible) {
        if (visible) {
            mDefaultText.setVisibility(View.VISIBLE);
            mAlbumInfo.setVisibility(View.GONE);
        } else {
            mDefaultText.setVisibility(View.GONE);
            mAlbumInfo.setVisibility(View.VISIBLE);
        }
    }

    public void updatePlayBtn(boolean isPlay) {
        if (isPlay) {
            mBtnPlay.setImageResource(R.drawable.minibar_btn_pause);
        } else {
            mBtnPlay.setImageResource(R.drawable.minibar_btn_play);
        }
    }
}
