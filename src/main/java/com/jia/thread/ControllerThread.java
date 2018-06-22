package com.jia.thread;

import com.jia.command.Command;
import com.jia.command.CommandFactory;
import com.jia.command.PassCommand;
import com.jia.command.UserCommand;
import com.jia.server.Share;

import java.io.*;
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
        int count = 0;
        try{
            // 获得输入流
            BufferedReader clientInfo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 从标准输入设备读取输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            // 获得输出流
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            // 服务器的响应信息
            String response = null;
            // 客户端指令
            String commandStr = null;
            // 客户端指令按空格分割后的字符串数组
            String[] commandStrs = null;
            while(true) {
                if (count == 0) {
                    out.println("220 服务器就绪");
                    out.flush();
                    count++;
                }
                commandStr = clientInfo.readLine();
                commandStrs = commandStr.split(" ");
                // 来自客户端的命令，以空格为标准分割成几部分，第一部分应当是命令
                commandStr = commandStrs[0];
                Command command = CommandFactory.createCommand(commandStr);
                if(command != null){
                    // 在未登录前只能使用 USER 和 PASS 命令
                    if(loginedValiate(command)){
                        String data = "";
                        if(commandStrs.length >= 2){
                            data = commandStrs[1];
                        }
                        command.execute(data, out, this);
                    }else{
                        out.println("532 执行该命令需要登录");
                    }
                }else{
                    out.println("502 输入指令有误");
                    out.flush();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 判断是否已经登录
     * @param command 接受判断的命令
     * @return 不是USER 和 PASS 命令即为登录，返回 true， 否则返回 false
     */
    private boolean loginedValiate(Command command){
        if(command instanceof UserCommand || command instanceof PassCommand){
            return true;
        }else{
            return isLogined;
        }
    }

    /**
     * 设置用户的登录状态
     * @param b 登录为true， 未登录为false
     */
    public void setLogined(boolean b){
        this.isLogined = b;
    }
}
