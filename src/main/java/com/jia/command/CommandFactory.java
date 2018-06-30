package com.jia.command;

/**
 * @author jia
 * @date 2018/6/21 15:27
 * 创建命令的工厂类
 **/
public class CommandFactory {

    public static Command createCommand(String type){

        type = type.toUpperCase();
        switch (type){
            // 用户命令
            case "USER" : return new UserCommand();
            // 密码命令
            case "PASS" : return new PassCommand();
            // 目录命令
            case "DIR"  : return new ListCommand();
            // 列出文件列表命令
            case "LIST" : return new ListCommand();
            //
            case "PORT" : return new PortCommand();
            // 退出命令
            case "QUIT" : return new QuitCommand();
            // 下载命令
            case "RETR" : return new RetrCommand();
            // 打开文件夹
            case "CWD"  : return new CwdCommand();
            // 上传命令
            case "STOR" : return new StoreCommand();
            // 列出当前目录路径命令
            case "XPWD" : return new PWDCommand();
            // 建立文件夹命令
            case "XMKD" : return new MKDCommand();
            // 删除文件命令
            case "DELE" : return new DELECommand();
            default : return null;
        }
    }
}
