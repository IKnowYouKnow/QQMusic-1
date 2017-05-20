package com.youyan.qqmusic.util;

import com.youyan.qqmusic.App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CacheUtils {

    private static final int CACHE_LIFE_TIME = 30 * 60 * 1000;

    public static void put(String key, String value) {
        File cacheDir = App.getApp().getExternalCacheDir();
        File file = new File(cacheDir, key);
        file.deleteOnExit();
        try {
            if (file.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                long diedTime = System.currentTimeMillis() + CACHE_LIFE_TIME;
                writer.write(diedTime + "");
                writer.newLine();
                writer.write(value);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        File cacheDir = App.getApp().getExternalCacheDir();
        File file = new File(cacheDir, key);
        String result = null;
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                long diedTime = Long.parseLong(reader.readLine());
                long currentTime = System.currentTimeMillis();
                if (currentTime < diedTime) {
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while( (line = reader.readLine()) != null ) {
                        sb.append(line);
                    }
                    result = sb.toString();
                    reader.close();
                } else {
                    reader.close();
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
