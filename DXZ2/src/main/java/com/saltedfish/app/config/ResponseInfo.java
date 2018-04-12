package com.saltedfish.app.config;


import javax.servlet.http.HttpServletResponse;

public class ResponseInfo {
    private int code;
    private String message;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ResponseInfo setCode(int code){
        switch (code){
            //成功
            case Info.SUCCESS :
                this.code = code;
                this.message = "操作成功!";
                break;
            case Info.SUCCESS_DOWN :
                this.code = code;
                this.message = "下载成功!";
                break;
            case Info.SUCCESS_UPLOAD :
                this.code = code;
                this.message = "上传成功!";
                break;
            case Info.SUCCESS_PUSH :
                this.code = code;
                this.message = "发博成功!";
                break;
            case Info.SUCCESS_ADD :
                this.code = code;
                this.message = "添加成功!";
                break;
            case Info.SUCCESS_DELETE :
                this.code = code;
                this.message = "删除成功!";
                break;
            case Info.SUCCESS_UPDATE :
                this.code = code;
                this.message = "修改成功!";
                break;
            case Info.SUCCESS_SEARCH :
                this.code = code;
                this.message = "查找成功!";
                break;
            case Info.SUCCESS_LOGIN :
                this.code = code;
                this.message = "success login!";
                break;

             //500 服务器错误
            case Info.SERVER_ERR :
                this.code = code;
                this.message = "服务器错误!";
                break;

            //empty
            case Info.EMPTY_FILE :
                this.code = code;
                this.message = "文件为空!";
                break;

            case Info.EMPTY_SOMEONE_FILE :
                this.code = code;
                this.message = "某个文件为空!";
                break;
            case Info.EMPTY_USER :
                this.code = code;
                this.message = "login --- userCode or userPassWord is null!";
                break;

            //login
            case Info.NOT_LOGIN :
                this.code = code;
                this.message = "not login,please login !";
                break;

            default:
                this.code = 1000;
                this.message = "Unknown error! Please contact GM QQ:465051576/WeiXin:Fly_kkkk";
        }
        return this;
    }

    public static void setCode(HttpServletResponse response,int code){
        int code1;
        String message1 = "";
        switch (code){

            //成功
            case Info.SUCCESS :
                code1 = code;
                message1 = "操作成功!";
                break;
            case Info.SUCCESS_DOWN :
                code1 = code;
                message1 = "下载成功!";
                break;
            case Info.SUCCESS_UPLOAD :
                code1 = code;
                message1 = "上传成功!";
                break;
            case Info.SUCCESS_PUSH :
                code1 = code;
                message1 = "发博成功!";
                break;
            case Info.SUCCESS_ADD :
                code1 = code;
                message1 = "添加成功!";
                break;
            case Info.SUCCESS_DELETE :
                code1 = code;;
                message1 = "删除成功!";
                break;
            case Info.SUCCESS_UPDATE :
                code1 = code;
                message1 = "修改成功!";
                break;
            case Info.SUCCESS_SEARCH :
                code1 = code;
                message1 = "查找成功!";
                break;
            case Info.SUCCESS_LOGIN :
                code1 = code;
                message1 = "success login!";
                break;

            // 300 参数错误
            case Info.PARAM_ERR :
                code1 = code;
                message1 = "param err!";
                break;

            //500 服务器错误
            case Info.SERVER_ERR :
                code1 = code;
                message1 = "服务器错误!";
                break;

            //empty
            case Info.EMPTY_FILE :
                code1 = code;
                message1 = "文件为空!";
                break;

            case Info.EMPTY_SOMEONE_FILE :
                code1 = code;
                message1 = "某个文件为空!";
                break;
            case Info.EMPTY_USER :
                code1 = code;
                message1 = "login --- userCode or userPassWord is null!";
                break;

            //login
            case Info.NOT_LOGIN :
                code1 = code;
                message1 = "not login,please login !";
                break;
            case Info.ERR_LOGIN :
                code1 = code;
                message1 = "phone or password err!";
                break;

            case Info.REGISTER_INFO_EMPTY :
                code1 = code;
                message1 = "register info is empty!";
                break;
            case Info.REGISTER_NAME_ERR :
                code1 = code;
                message1 = "register name is err!";
                break;
            case Info.REGISTER_PASSWORD_ERR :
                code1 = code;
                message1 = "register password is err!";
                break;
            case Info.REGISTER_PHONE_ERR :
                code1 = code;
                message1 = "register phone is err!";
                break;

            /**
             * 用户操作错误 1000
             */
            case Info.FOLLOW_ALREADY :
                code1 = code;
                message1 = "you already follow he!";
                break;
            case Info.FOLLOW_NOTFOLLOW :
                code1 = code;
                message1 = "you not follow he!";
                break;


            /**
             * err  1100
             */
            case Info.POST_NOT_EXIST :
                code1 = code;
                message1 = "you select post not exist or post already be deleted!";
                break;

            default:
                code1 = code;
                message1 = "Unknown error! Please contact GM QQ:465051576/WeiXin:Fly_kkkk";
        }
        response.setStatus(code1);
        response.addHeader("message",message1);
        return;
    }
}
