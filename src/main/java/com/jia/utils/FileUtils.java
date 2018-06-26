package com.jia.utils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * @author jia
 * @date 2018/6/26 17:14
 **/
public class FileUtils {

    /**
     * 1KB
     */
    private static final Integer KB = 1000;
    /**
     * 1MB
     */
    private static final Integer MB = 1000 * 1000;
    /**
     * 1GB
     */
    private static final Integer GB = 1000 * 1000 * 1000;

    /**
     * 根据 File.length() 获取文件的字节数（Byte）根据情况换算成其他单位
     * @param file 需要测量大小的文件
     * @return 换算结果以字符串形式返回
     */
    public static String size(File file){
        if(file.isDirectory()){
            return " ";
        }
        StringBuilder fileSize = new StringBuilder();
        // 保留两位小数
        DecimalFormat floatFormat = new DecimalFormat("##0.00");
        Long fileLen = file.length();
        if(fileLen >= KB){
            if(fileLen >= MB){
                if(fileLen >= GB){
                    fileSize.append(floatFormat.format(1.0 * fileLen / GB)).append(" GB");
                }else {
                    fileSize.append(floatFormat.format(1.0 * fileLen / MB)).append(" MB");
                }
            }else{
                fileSize.append(floatFormat.format(1.0 * fileLen / KB)).append(" KB");
            }
        }else{
            fileSize.append(floatFormat.format(1.0 * fileLen)).append(" B");
        }
        return fileSize.toString();
    }

    /**
     * 计算文件名列表中的最长的文件名的长度
     * @param fileNames 文件名列表
     * @return 最大文件名最大长度
     */
    public static Integer maxFileName(String[] fileNames){
        Integer maxLength = fileNames[0].length();
        for (String file : fileNames){
            if(maxLength < file.length()){
                maxLength = file.length();
            }
        }
        return maxLength;
    }
}
