package com.jia.utils;

/**
 * @author jia
 * @date 2018/6/26 19:36
 **/
public class StringUtils {

    /**
     * 将字符串左对齐并占用length的长度
     * @param length 长度
     * @param s 字符串
     * @return 格式化后的字符串
     */
    public static String format(Integer length, String s){
        StringBuilder format = new StringBuilder();
        format.append("%").append(-length).append("s");
        return String.format(format.toString(), s);
    }
}
