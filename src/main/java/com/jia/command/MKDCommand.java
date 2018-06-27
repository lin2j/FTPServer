package com.jia.command;

import com.jia.myenum.FTPStateCode;
import com.jia.thread.ControllerThread;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/25 1:42
 * 创建文件夹
 **/
public class MKDCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread){
        String response = null;
        String filePath = thread.getNowDir() + File.separator + data;
        File file = new File(filePath);
        try {
            if(file.mkdirs()){
                response = "250 " + filePath;
            }else{
                response = FTPStateCode.ACTION_NOT_TOKEN.getMsg();
            }
        } catch (Exception e) {
            response = FTPStateCode.ACTION_NOT_TOKEN.getMsg();
        }
        out.println(response);
        out.flush();
    }
}
