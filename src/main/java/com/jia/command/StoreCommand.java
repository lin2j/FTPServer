package com.jia.command;

import com.jia.myenum.FTPStateCode;
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
        out.println(FTPStateCode.STATUS_OKAY.getMsg());
        out.flush();
        File file = new File(thread.getNowDir() + File.separator + data);
        try {
            Socket socket = new Socket(thread.getTargetIP(), thread.getTargetPort());
            // socket 的数据输入流
            InputStreamReader dataIn = new InputStreamReader(socket.getInputStream(), "UTF-8");
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            // 要写入的文件
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            // 判断输入流是否读完
            int a;
            while((a = bis.read()) != -1){
                bos.write(a);
            }
            dataIn.close();
            bos.flush();
            bis.close();
            bos.close();
            socket.close();
            out.println(FTPStateCode.FILE_ACTION_COMPLETED.getMsg());
            out.flush();
        } catch (IOException e) {
            out.println(FTPStateCode.LOCAL_ERROR.getMsg());
        }
    }
}
