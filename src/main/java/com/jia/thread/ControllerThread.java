package com.jia.thread;

import com.jia.server.Share;

import java.net.Socket;

/**
 * @author jia
 * @date 2018/6/21 15:27
 * 控制连接线程类
 **/
public class ControllerThread extends Thread{

    private Socket socket = null;

    /**
     * 当前用户所对应的用户
     */
    public static final ThreadLocal<String> USER = new ThreadLocal<String>();

    /**
     * 标记用户是否已经登录
     */
    private boolean isLogined = false;

    /**
     * 当前目录
     */
    private String nowDir = Share.nowDir;

    public ControllerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){

    }
}
