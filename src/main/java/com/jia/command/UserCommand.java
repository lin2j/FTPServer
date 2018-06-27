package com.jia.command;

import com.jia.myenum.FTPStateCode;
import com.jia.server.Share;
import com.jia.thread.ControllerRunnable;

import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/21 23:09
 * 输入用户名
 **/
public class UserCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerRunnable thread) {
        String response = null;
        if(Share.users.containsKey(data)){
            ControllerRunnable.USER.set(data);
            thread.setNowDir(thread.getNowDir() + data);
            response = FTPStateCode.USER_EXIST.getMsg();
        }else{
            response = FTPStateCode.ARGUMENT_ERROR.getMsg();
        }
        out.println(response);
        out.flush();
    }
}
