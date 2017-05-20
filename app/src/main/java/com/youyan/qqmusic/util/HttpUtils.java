package com.youyan.qqmusic.util;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class HttpUtils {

    public static String get(String url, Map<String, String> params) {
        StringBuffer sb = new StringBuffer(url);
        if (params != null) {
            sb.append("?");
            Set<String> keySet = params.keySet();
            int i = 0;
            for (String key : keySet) {
                String p = params.get(key);
                sb.append(key + "=" + p);
                if (i < params.size() - 1) {
                    sb.append("&");
                }
                i++;
            }
        }
        System.out.println("url:" + sb.toString());
        try {
            URL url1 = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            int code = conn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == code) {
                InputStream is = conn.getInputStream();
                String result = StreamUtils.streamToString(is);
                conn.disconnect();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
