package com.jia.command;

import com.jia.myenum.FTPStateCode;
import com.jia.thread.ControllerThread;

import java.io.PrintWriter;

/**
 * @author jia
 * @date 2018/6/22 22:31
 **/
public class PortCommand implements Command {
    @Override
    public void execute(String data, PrintWriter out, ControllerThread thread) {
        String[] ipAndPort = data.split(",");
        // ip 地址
        StringBuilder ip = new StringBuilder();
        ip.append(ipAndPort[0]).append(".").append(ipAndPort[1]).append(".");
        ip.append(ipAndPort[2]).append(".").append(ipAndPort[3]);
        // 端口
        Integer port = Integer.valueOf(ipAndPort[4])*256 + Integer.valueOf(ipAndPort[5]);
        // 记录 ip 和端口
        thread.setTargetIP(ip.toString());
        thread.setTargetPort(port);
        out.println(FTPStateCode.SUCCESSFULLY.getMsg());
        out.flush();
    }
}
