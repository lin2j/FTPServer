package com.jia.myenum;

/**
 * @author jia
 * @date 2018/6/25 9:04
 * FTP 响应码
 **/
public enum FTPStateCode {
    /**
     * FTP 响应码代号以及信息
     */
    USER_EXIST(0, "331 User exist, need a password"),
    ARGUMENT_ERROR(1, "501 Syntax error in parameters or arguments."),
    BAD_PASSWORD(2, "530 Bad password"),
    SUCCESSFULLY(3, "200 Successfully"),
    PERMISSION_DENIED(4, "500 Permission denied"),
    NO_SUCH_FILE_OR_DIRECTORY(5, "550 No such file or directory"),
    COMMAND_OKAY(6, "250 Command okay"),
    FILE_NOT_FOUND(7, "550 File not found"),
    FILE_DELETE_SUCCESS(8, "250 Delete action completed"),
    COMMAND_NOT_IMPLEMENTED(9, "502 Command not implemented"),
    STATUS_OKAY(10, "150 File status okay, about to open data connection"),
    ACTION_NOT_TOKEN(11, "550 Requested action not taken"),
    GOOD_BYE(12, "221 Good bye"),
    SERVER_READY(13, "220 Server ready for new user"),
    FILE_ACTION_COMPLETED(14, "250 Transfer complete "),
    LOCAL_ERROR(15, "451 Local error"),
    NOT_LOGGRD_IN(16, "530 Not logged in"),
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
