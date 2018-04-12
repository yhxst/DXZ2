package com.saltedfish.app.controller;

import com.saltedfish.app.bean.User;
import com.saltedfish.app.bean.user.User_Info;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.intercept.Auth;
import com.saltedfish.app.mapper.UserMapper;
import com.saltedfish.app.mapper.User_InfoMapper;
import com.saltedfish.app.util.CharUtil;
import com.saltedfish.app.verification.CookieUtil;
import jdk.nashorn.internal.parser.JSONParser;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    User_InfoMapper user_infoMapper;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public User login(HttpServletRequest request,HttpServletResponse response,@RequestBody User user){
        String userPhone = user.getUserPhone();
        String userPassWord = user.getUserPassWord();


        if (userPhone == null || userPassWord == null){
            //账号或者密码为空
            ResponseInfo.setCode(response,Info.EMPTY_USER);
            return null;
        }

        String code = userMapper.getUserCode(userPhone,userPassWord);
        if (code != null){
            //账号密码正确验证成功
            //创建Token 传给client

            Cookie cookie = CookieUtil.create(code);
            response.addCookie(cookie);
            ResponseInfo.setCode(response,Info.SUCCESS_LOGIN);

        }else {
            ResponseInfo.setCode(response,Info.ERR_LOGIN);
        }
        User user1 = userMapper.getUserByCode(code);

        return userMapper.getUserByCode(code);

    }


    //以后添加phone验证码
    @RequestMapping("/register")
    public void register(HttpServletResponse response,User user){
        String phone = user.getUserPhone();
        String passWord = user.getUserPassWord();
        String name = user.getUserName();

        if (phone == null || passWord == null || name == null){
            ResponseInfo.setCode(response,Info.REGISTER_INFO_EMPTY);
            return;
        }
        //判断电话是否重复/符合规范
        if (userMapper.getUserByPhone(phone) !=null){
            ResponseInfo.setCode(response,Info.REGISTER_PHONE_ERR);
            return;
        }
        //判断名字是否重复/符合规范
        if (userMapper.getUserByName(name) !=null){
            ResponseInfo.setCode(response,Info.REGISTER_NAME_ERR);
            return;
        }
        //判断密码是否符合规范
        if (!CharUtil.isEnglishAnNum(passWord)){
            ResponseInfo.setCode(response,Info.REGISTER_PASSWORD_ERR);
            return;
        }
        //创建账号
        userMapper.insert(User.createNewUser(userMapper,name,phone,passWord));
        //创建用户信息
        User_Info user_info = new User_Info(user_infoMapper);
        user_info.autoInfo();
        user_infoMapper.insert(user_info);

    }
}
