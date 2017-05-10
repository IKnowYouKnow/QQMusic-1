package com.youyan.qqmusic.util;


import android.content.Context;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class ShareSDKUtils {

    public static void oneKeyShare(Context context, String title, String content, String url) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);
        oks.setImageUrl("https://p.ssl.qhimg.com/d/_open360/logo72nb/127_10_7272.png");
        // 启动分享GUI
        oks.show(context);
    }

}
