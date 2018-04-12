package com.saltedfish.app.bean;

import com.saltedfish.app.mapper.FileUrlMapper;
import com.saltedfish.app.mapper.UserMapper;

import java.io.Serializable;
import java.sql.Timestamp;

public class User extends BaseBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private String userCode;
    private String userPhone;
    private String userPassWord;
    private String userName;

    public void autoUserCode(UserMapper userMapper){
        userCode = BaseBean.getRandomString(20);
        boolean flag = true;
        while (flag){
            if (userMapper.getOne(userCode) == null){
                flag = false;
            }else {
                userCode = BaseBean.getRandomString(20);
            }
        }
    }

    public static User createNewUser(UserMapper userMapper,String name,String userPhone,String password){
        User user = new User();
        user.setUserName(name);
        user.setUserPassWord(password);
        user.setUserPhone(userPhone);
        user.autoUserCode(userMapper);
        user.autoCreateAt();
        user.autoUpdateAt();
        return user;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
