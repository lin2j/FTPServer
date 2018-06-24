package com.jia.thread;

import com.jia.command.Command;
import com.jia.command.CommandFactory;
import com.jia.command.PassCommand;
import com.jia.command.UserCommand;
import com.jia.server.Share;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author jia
 * @date 2018/6/21 15:27
 * 控制连接线程类
 **/
public class ControllerThread extends Thread{

    public static Integer count = 0;

    private Socket socket = null;

    /**
     * 当前用户所对应的用户
     */
    public static final ThreadLocal<String> USER = new ThreadLocal<>();

    /**
     * 标记用户是否已经登录
     */
    private boolean isLogined = false;

    /**
     * 要建立数据链接的主机ip
     */
    public String targetIP = null;

    /**
     * 要建立数据链接的主机端口
     */
    public Integer targetPort = null;

    /**
     * 当前目录
     */
    private String nowDir = Share.nowDir.toString();

    public String getTargetIP() {
        return targetIP;
    }

    public void setTargetIP(String targetIP) {
        this.targetIP = targetIP;
    }

    public Integer getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(Integer targetPort) {
        this.targetPort = targetPort;
    }

    public String getNowDir() {
        return nowDir;
    }

    public void setNowDir(String nowDir) {
        this.nowDir = nowDir;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

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
                    out.println("220 Server readiness");
                    out.flush();
                    count++;
                }
                commandStr = clientInfo.readLine();
                System.out.print("Instructions from client : ");
                System.out.println(commandStr);
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
                        out.println("532 Not logged in");
                    }
                }else{
                    out.println("502 Command not found");
                    out.flush();
                }
            }
        }catch (SocketException e){
            System.out.println("Socket closed");
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
