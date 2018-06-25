package com.jia.command;

import com.jia.myenum.FTPStateCode;
import com.jia.thread.ControllerThread;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/22 22:32
 * 退出 命令
 **/
public class QuitCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        try{
            out.println(FTPStateCode.GOOD_BYE.getMsg());
            out.flush();
            out.close();
            thread.getSocket().close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
