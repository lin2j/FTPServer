package com.jia.command;

import com.jia.myenum.FTPStateCode;
import com.jia.server.Share;
import com.jia.thread.ControllerThread;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/22 22:30
 * 密码验证命令
 **/
public class PassCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        StringBuilder response = new StringBuilder();
        // 获取该用户的密码
        String user = ControllerThread.USER.get();
        String pass = Share.users.get(user);
        if(pass.equals(data)){
            Share.loginedUsers.put(user, pass);
            thread.setLogined(true);
            // 创建用户文件夹
            File userDir = new File(Share.nowDir.toString() + user);
            if(!userDir.exists()){
                userDir.mkdir();
            }
            response.append("203 User ").append(user).append(" login!");
        }else{
            response.append(FTPStateCode.BAD_PASSWORD.getMsg());
        }
        out.println(response.toString());
        out.flush();
    }
}
