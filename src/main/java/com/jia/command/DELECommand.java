package com.jia.command;

import com.jia.myenum.FTPStateCode;
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
        File file = new File(thread.getNowDir() + File.separator + data);
        if(!file.exists()){
            response = FTPStateCode.FILE_NOT_FOUND.getMsg();
        }else{
            if(file.delete()){
                response = FTPStateCode.FILE_DELETE_SUCCESS.getMsg();
            }else {
                response = FTPStateCode.COMMAND_NOT_IMPLEMENTED.getMsg();
            }
        }
        out.println(response);
        out.flush();
    }
}
