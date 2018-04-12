package com.saltedfish.app.bean.user;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.User_InfoMapper;

public class User_Info extends BaseBean {
    public User_Info(){
        autoUpdateAt();
    }

    public User_Info(User_InfoMapper user_InfoMapper){
        super(user_InfoMapper);
    }

    private String userId;
    private boolean sex;
    private String headPhoto;//http://
    private String describes;
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void autoInfo(){
        sex = true;
        headPhoto = "http://www.baodu.com";
        describes = "";
        email = "";
    }
}
