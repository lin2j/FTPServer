package com.jia.server;

import com.jia.thread.ControllerRunnable;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        Share.init();
    }

    public void listen() throws Exception{
        // 创建一个固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Socket socket = null;
        // 监听连接
        while(true){
            // tcp 三次握手建立连接
            socket = serverSocket.accept();
            ControllerRunnable ct = new ControllerRunnable(socket);
            pool.submit(ct);
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
