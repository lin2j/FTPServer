package com.jia.server;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public static StringBuilder nowDir = new StringBuilder();

    /**
     * 用户列表
     */
    public static HashMap<String, String> users = new HashMap<String, String>();
    /**
     * 已经登录的用户列表
     */
    public static HashMap<String, String> loginedUsers = new HashMap<String, String>();

    /**
     * 初始化共享数据
     */
    public static void init(){
        String projRootPath = System.getProperty("user.dir");
        String xmlPath = projRootPath + "/src/main/resources/server.xml";
        Path path = Paths.get(projRootPath);
        try{
            // 读取server.xml文件, server.xml 文件放在项目同级目录下
            String pathPar = path.getParent().toString();
            File serverXml = new File(pathPar + "/server.xml");
            SAXBuilder builder = new SAXBuilder();
            Document parse = builder.build(serverXml);
            Element root = parse.getRootElement();

            // 初始化登陆后用户所在的目录
            nowDir.append(root.getChildText("path"));
            nowDir.append("/FtpDir");
            nowDir.append("/");
            File ftpDir = new File(nowDir.toString());
            if(!ftpDir.exists()){
                ftpDir.mkdir();
            }

            // 初始化用户列表
            Element userE = root.getChild("users");
            List<Element> userEC = userE.getChildren("user");
            String name = null;
            String password = null;
            System.out.println("Initial user list...");
            for (Element user : userEC){
                name = user.getChildText("name");
                password = user.getChildText("pass");
                users.put(name, password);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
