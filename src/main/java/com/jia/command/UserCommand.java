package com.jia.command;

import com.jia.server.Share;
import com.jia.thread.ControllerThread;

import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/21 23:09
 * 输入用户名
 **/
public class UserCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        String response = null;
        if(Share.users.containsKey(data)){
            ControllerThread.USER.set(data);
            thread.setNowDir(thread.getNowDir() + data);
            response = "331 User exists, need a password";
        }else{
            response = "501 Argument error";
        }
        out.println(response);
        out.flush();
    }
}
