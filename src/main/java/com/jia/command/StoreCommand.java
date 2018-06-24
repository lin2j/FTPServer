package com.jia.command;

import com.jia.thread.ControllerThread;

import java.io.*;
import java.net.Socket;

/**
 * @author jia
 * @date 2018/6/22 22:33
 * 从客户端上传文件
 **/
public class StoreCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        out.println("150 Binary data connection\r\n");
        out.flush();
        File file = new File(thread.getNowDir() + File.separator + data);
        try {
            Socket socket = new Socket(thread.getTargetIP(), thread.getTargetPort());
            // socket 的数据输入流
            InputStreamReader dataIn = new InputStreamReader(socket.getInputStream(), "UTF-8");
            BufferedReader br = new BufferedReader(dataIn);
            // 要写入的文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            // 判断输入流是否读完
            String s = null;
            while((s = br.readLine()) != null){
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
            out.println("226 Transfer complete");
            out.flush();
            dataIn.close();
            br.close();
            bw.close();
            socket.close();
        } catch (IOException e) {
            out.println("226 Transmission interruption");
        }
    }
}
