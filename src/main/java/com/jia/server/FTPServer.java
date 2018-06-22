package com.jia.server;

import com.jia.thread.ControllerThread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jia
 * @date 2018/6/21 15:24
 *
 * FTP 服务器主程序
 **/
public class FTPServer {

    private ServerSocket serverSocket;

    public FTPServer(Integer port)throws Exception{
        serverSocket = new ServerSocket(port);
        // 初始化线程共享数据
       // Share.init();
        Share.temp();
        System.out.println(Share.users);
    }

    public void listen() throws Exception{
        Socket socket = null;
        // 监听连接
        while(true){
            // tcp 三次握手建立连接
            socket = serverSocket.accept();
            ControllerThread ct = new ControllerThread(socket);
            ct.start();
        }
    }

    public static void main(String[] args) {
        try{
            System.out.println("*** FTPServer started ***");
            FTPServer ftpServer = new FTPServer(21);
            ftpServer.listen();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
