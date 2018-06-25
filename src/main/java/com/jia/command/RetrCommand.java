package com.jia.command;

import com.jia.myenum.FTPStateCode;
import com.jia.thread.ControllerThread;

import java.io.*;
import java.net.Socket;

/**
 * @author jia
 * @date 2018/6/22 22:32
 * 从服务器下载文件
 **/
public class RetrCommand implements Command {

    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        // 要发送的文件
       String filePath = thread.getNowDir() + File.separator + data;
       File file = new File(filePath);
       if(!file.exists()){
           out.println(FTPStateCode.FILE_NOT_FOUND);
           out.flush();
       }else{
           try{
               out.println(FTPStateCode.STATUS_OKAY.getMsg());
               out.flush();
               Socket socket = new Socket(thread.getTargetIP(), thread.getTargetPort());
               // 读取服务器文件的输出流
               FileInputStream fis = new FileInputStream(file);
               BufferedReader br = new BufferedReader(new InputStreamReader(fis));
               // 发送数据的 socket 的输出流
               PrintWriter dataOut = new PrintWriter(socket.getOutputStream());
               // 判断输入流是否读完的标志
               String s = null;
               while((s = br.readLine()) != null){
                    dataOut.println(s);
               }
               dataOut.flush();
               fis.close();
               dataOut.close();
               socket.close();
               out.println(FTPStateCode.FILE_ACTION_COMPLETED.getMsg());
           } catch (Exception e){
               out.println(FTPStateCode.LOCAL_ERROR.getMsg());
           }
           out.flush();
       }
    }
}
