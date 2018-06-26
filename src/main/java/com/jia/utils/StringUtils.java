package com.jia.utils;

import java.util.Formatter;

/**
 * @author jia
 * @date 2018/6/26 19:36
 **/
public class StringUtils {

    public static String format(Integer length, String s){
        StringBuilder format = new StringBuilder();
        format.append("%").append(-length).append("s");
        return String.format(format.toString(), s);
    }
}
