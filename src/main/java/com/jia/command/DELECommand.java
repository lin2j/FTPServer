package com.jia.command;

import com.jia.thread.ControllerThread;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/25 1:44
 * 删除文件
 **/
public class DELECommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        String response = null;
        String fail = "220 File not exists";
        File file = new File(thread.getNowDir() + File.separator + data);
        if(!file.exists()){
            response = fail;
        }else{
            if(file.delete()){
                response = "250 Delete success";
            }else {
                response = fail;
            }
        }
        out.println(response);
        out.flush();
    }
}
