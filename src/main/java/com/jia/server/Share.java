package com.jia.server;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @author jia
 * @date 2018/6/21 15:25
 *
 * 线程共享数据类
 **/
public class Share {

    /**
     * 登录后默认的目录
     */
    public static String nowDir = null;

    /**
     * 用户列表
     */
    public static HashMap<String, String> users = new HashMap<String, String>();
    /**
     * 已经登录的用户列表
     */
    public static HashMap<String, String> loginedUsers = new HashMap<String, String>();


    public static void temp(){
        users.put("jia", "666");
    }

    /**
     * 初始化共享数据
     */
    public static void init(){
        String path = "/server.iml";

        try{
            // 读取server.iml配置文件
            File serverIml = new File(path);
            SAXBuilder builder = new SAXBuilder();
            Document parse = builder.build(serverIml);
            Element root = parse.getRootElement();

            // 初始化登陆后用户所在的目录
            nowDir = root.getChildText("path");

            // 初始化用户列表
            Element userE = root.getChild("users");
            List<Element> userEC = userE.getChildren();
            String name = null;
            String password = null;
            System.out.println("初始化用户列表···");
            for (Element user : userEC){
                name = user.getChildText("Name");
                password = user.getChildText("Password");
                users.put(name, password);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
