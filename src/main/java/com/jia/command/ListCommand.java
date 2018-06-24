package com.jia.command;

import com.jia.server.Share;
import com.jia.thread.ControllerThread;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author jia
 * @date 2018/6/22 22:30
 * ls 和 dir 命令
 **/
public class ListCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {

        StringBuilder fileNames = new StringBuilder();
        String userPath = thread.getNowDir();
        userPath = userPath +data ;
        File file = new File(userPath);
        String[] fileList = file.list();
        if(fileList != null) {
            for (String fileName : fileList) {
                fileNames.append(fileName);
                fileNames.append("\n");
            }
        }

        // 向客户端发送数据
        try {
            out.println("150 open ascii mode");
            out.flush();
            Socket socket = new Socket(thread.getTargetIP(), thread.getTargetPort());
            socket.setSoTimeout(30000);
            PrintWriter dataOut = new PrintWriter(socket.getOutputStream());
            dataOut.println(fileNames.toString());
            dataOut.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println();
        out.flush();
    }
}
