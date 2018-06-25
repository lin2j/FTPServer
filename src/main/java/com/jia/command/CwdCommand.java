package com.jia.command;

import com.jia.myenum.FTPStateCode;
import com.jia.server.Share;
import com.jia.thread.ControllerThread;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jia
 * @date 2018/6/22 22:33
 * 改变工作目录
 **/
public class CwdCommand implements Command {

    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        // 上一级目录
        String towPoint = "..";
        String response = null;
        // 判断是不是返回上一级
        if(towPoint.equals(data)){
            Path path = Paths.get(thread.getNowDir());
            // 当前目录的父级目录
            String p = path.getParent().toString();
            // b 是一个用来判断 p 是否与根目录路径相同的标志
            boolean b = false;
            try {
                // 比较标准路径
                b = new File(p).getCanonicalPath().equals(new File(Share.nowDir.toString()).getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(b){
                response = FTPStateCode.PERMISSION_DENIED.getMsg();
            }else{
                response = FTPStateCode.COMMAND_OKAY.getMsg();
                thread.setNowDir(p);
            }
        }else {
            // 用户当前目录
            String nowDir = thread.getNowDir();
            // 目的目录
            String targetDir = nowDir + data;
            File file = new File(targetDir);
            if (!file.exists()) {
                response = FTPStateCode.NO_SUCH_FILE_OR_DIRECTORY.getMsg();
            } else {
                response = FTPStateCode.COMMAND_OKAY.getMsg();
                thread.setNowDir(thread.getNowDir() + data);
            }
        }
        out.println(response);
        out.flush();
    }
}
