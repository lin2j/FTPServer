package com.jia.command;

import com.jia.thread.ControllerThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @author jia
 * @date 2018/6/22 22:32
 * 退出 命令
 **/
public class QuitCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        try{
            out.println("221 Bye");
            out.flush();
            out.close();
            thread.getSocket().close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
