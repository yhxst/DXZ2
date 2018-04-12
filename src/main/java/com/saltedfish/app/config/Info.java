package com.saltedfish.app.config;

public class Info {
    public static final int SUCCESS = 200;
    public static final int SUCCESS_DOWN = 201;
    public static final int SUCCESS_UPLOAD = 202;
    /**
     * 成功发出图片
     */
    public static final int SUCCESS_PUSH = 203;
    public static final int SUCCESS_ADD= 204;
    public static final int SUCCESS_DELETE = 205;
    public static final int SUCCESS_UPDATE = 206;
    public static final int SUCCESS_SEARCH = 207;

    public static final int SUCCESS_LOGIN = 208;


    /**
     * param
     */
    public static final int PARAM_ERR = 300;



    public static final int SERVER_ERR = 500;
    /**
     * empty
     */
    public static final int EMPTY_FILE = 800;
    public static final int EMPTY_SOMEONE_FILE = 801;
    public static final int EMPTY_USER = 802;

    /**
     * login
     */
    public static final int NOT_LOGIN = 900;
    public static final int ERR_LOGIN = 901;
    public static final int REGISTER_INFO_EMPTY = 902;
    public static final int REGISTER_NAME_ERR = 903;
    public static final int REGISTER_PHONE_ERR = 904;
    public static final int REGISTER_PASSWORD_ERR = 905;

    /**
     * 用户操作错误
     */
    public static final int FOLLOW_ALREADY = 1000;
    public static final int FOLLOW_NOTFOLLOW = 1001;


    /**
     * err
     */
    public static final int POST_NOT_EXIST = 1100;//博不存在或者被删了
}
