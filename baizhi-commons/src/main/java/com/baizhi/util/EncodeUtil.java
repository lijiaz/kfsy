package com.baizhi.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by MaXn on 2017/6/12.
 */
public class EncodeUtil {

    public static String encode(String value){
        String encode = null;
        try {
            encode = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encode;
    }

}
