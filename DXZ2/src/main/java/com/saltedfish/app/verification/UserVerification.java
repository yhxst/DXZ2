package com.saltedfish.app.verification;

import com.saltedfish.app.mapper.UserMapper;
import com.saltedfish.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static com.saltedfish.app.constant.Constants.COOKIENAME;
import static com.saltedfish.app.constant.Constants.SIGNINGKEY;

public class UserVerification {
    @Autowired
    UserMapper userMapper;
    private static UserVerification verification;

    public static UserVerification getVerification() {
        if (verification == null)
            verification = new UserVerification();
        return verification;
    }

    public static synchronized boolean userVerificationByToken(HttpServletRequest httpServletRequest){

        if (JwtUtil.parseToken(httpServletRequest) != null) {
            return true;
        }
        return false;
    }

    public synchronized boolean userVerificationByCodeAndPassWord(String phone,String password){
        boolean flag = false;
//        try{
            String userCode = userMapper.getUserCode(phone,password);
            flag = true;
//        }
//        catch (Exception e){
//            e.getMessage();
//            e.getCause();
//        }

        return flag;
    }
}
