package com.jia.command;

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
        String fail = "550 Create failed";
        File file = new File(filePath);
        try {
            if(file.createNewFile()){
                response = "250 " + filePath;
            }else{
                response = fail;
            }
        } catch (IOException e) {
            response = fail;
        }
        out.println(response);
        out.flush();
    }
}
