package com.jia.myenum;

/**
 * @author jia
 * @date 2018/6/25 9:04
 * FTP 响应码
 **/
public enum FTPStateCode {
    /**
     * 用户存在，需要密码
     */
    USER_EXIST(0, "331 User exist, need a password"),
    /**
     * 参数错误
     */
    ARGUMENT_ERROR(1, "501 Syntax error in parameters or arguments."),
    /**
     * 密码错误
     */
    BAD_PASSWORD(2, "530 Bad password"),
    /**
     * 成功，无异常
     */
    SUCCESSFULLY(3, "200 Successfully"),
    /**
     * 无权限执行此操作
     */
    PERMISSION_DENIED(4, "500 Permission denied"),
    /**
     * 文件或目录不存在
     */
    NO_SUCH_FILE_OR_DIRECTORY(5, "550 No such file or directory"),
    /**
     * 命令无误
     */
    COMMAND_OKAY(6, "250 Command okay"),
    /**
     * 文件不存在
     */
    FILE_NOT_FOUND(7, "550 File not found"),
    /**
     * 文件删除成功
     */
    FILE_DELETE_SUCCESS(8, "250 Delete action completed"),
    /**
     * 命令未能执行
     */
    COMMAND_NOT_IMPLEMENTED(9, "502 Command not implemented"),
    /**
     * 连接状态正常，可以传送数据
     */
    STATUS_OKAY(10, "150 File status okay, about to open data connection"),
    /**
     * 操作未能执行
     */
    ACTION_NOT_TOKEN(11, "550 Requested action not taken"),
    /**
     * 连接断开
     */
    GOOD_BYE(12, "221 Good bye"),
    /**
     * 服务器就绪
     */
    SERVER_READY(13, "220 Server ready for new user"),
    /**
     *  文件操作完成
     */
    FILE_ACTION_COMPLETED(14, "250 Transfer complete "),
    /**
     * 本地（服务器）错误
     */
    LOCAL_ERROR(15, "451 Local error"),
    /**
     * 未登录
     */
    NOT_LOGGRD_IN(16, "530 Not logged in"),
    /**
     *  无此命令
     */
    COMMAND_NOT_FOUND(17, "500 Command not found");


    /**
     * 代号
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;

    FTPStateCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
