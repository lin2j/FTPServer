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
            case "USER" : return new UserCommand();
            case "PASS" : return new PassCommand();
            case "DIR"  : return new ListCommand();
            case "LIST" : return new ListCommand();
            case "PORT" : return new PortCommand();
            case "QUIT" : return new QuitCommand();
            case "RETR" : return new RetrCommand();
            case "CWD"  : return new CwdCommand();
            case "STOR" : return new StoreCommand();
            case "XPWD" : return new PWDCommand();
            case "XMKD"  : return new MKDCommand();
            case "DELE" : return new DELECommand();
            default : return null;
        }
    }
}
