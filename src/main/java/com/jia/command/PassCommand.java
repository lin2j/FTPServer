package com.jia.command;

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
        StringBuilder respone = new StringBuilder();
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
            respone.append("203 User ").append(user).append(" login!");
        }else{
            respone.append("530 bad password");
        }
        out.println(respone.toString());
        out.flush();
    }
}
