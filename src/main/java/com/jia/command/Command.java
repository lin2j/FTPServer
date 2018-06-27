package com.jia.command;

import com.jia.thread.ControllerRunnable;

import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/21 15:25
 *  命令接口
 **/
public interface Command {

    /**
     * 执行命令
     * @param data  命令后面的信息
     * @param out 输出流
     * @param thread 命令控制线程
     */
    void execute(String data, PrintWriter out, ControllerRunnable thread) ;
}
