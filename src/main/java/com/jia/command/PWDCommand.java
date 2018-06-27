package com.jia.command;

import com.jia.thread.ControllerRunnable;

import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/24 22:54
 **/
public class PWDCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerRunnable thread) {
        String response = "257 " + thread.getNowDir();
        out.println(response);
        out.flush();
    }
}
