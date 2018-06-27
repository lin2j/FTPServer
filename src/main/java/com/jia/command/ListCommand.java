package com.jia.command;

import com.jia.myenum.FTPStateCode;
import com.jia.thread.ControllerRunnable;
import com.jia.utils.FileUtils;
import com.jia.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author jia
 * @date 2018/6/22 22:30
 * ls 和 dir 命令
 **/
public class ListCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerRunnable thread) {
        StringBuilder fileInfo  = new StringBuilder();
        // 文件类型，文件夹或普通文件
        Integer maxLength = 1;
        String fileType = null;
        String tab = "\t";
        // 算出文件的全包名
        String userPath = thread.getNowDir();
        userPath = userPath + data ;
        File file = new File(userPath);
        // 该目录下的所有文件（包括文件夹）
        File[] files = file.listFiles();
        String[] fileNames = file.list();
        if(fileNames != null) {
            if(fileNames.length > 0) {
                maxLength = FileUtils.maxFileName(fileNames);
            }
        }
        fileInfo.append(StringUtils.format(maxLength, "Name")).append(tab).append("Type").append(tab).append("Size\n");
        if(files != null){
            if(files.length > 0) {
                for (File f : files) {
                    if(f.isDirectory()){
                        fileType = "dir";
                    }else{
                        fileType = "file";
                    }
                    fileInfo.append(StringUtils.format(maxLength,f.getName())).append(tab);
                    fileInfo.append(fileType).append(tab);
                    fileInfo.append(FileUtils.size(f)).append("\n");
                }
            }
        }

        // 向客户端发送数据
        try {
            out.println(FTPStateCode.STATUS_OKAY.getMsg());
            out.flush();
            Socket socket = new Socket(thread.getTargetIP(), thread.getTargetPort());
            socket.setSoTimeout(30000);
            PrintWriter dataOut = new PrintWriter(socket.getOutputStream());
            dataOut.println(fileInfo.toString());
            dataOut.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            out.println("");
        }
        out.println();
        out.flush();
    }
}
