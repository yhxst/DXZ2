package com.saltedfish.app.controller.user;


import com.saltedfish.app.bean.user.SimpleUserInfo;
import com.saltedfish.app.bean.user.User_Info;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.intercept.Auth;
import com.saltedfish.app.mapper.UserMapper;
import com.saltedfish.app.mapper.User_InfoMapper;
import com.saltedfish.app.util.FileUtil;
import com.saltedfish.app.util.JwtUtil;
import com.saltedfish.app.verification.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;

@Auth
@RequestMapping("/userInfo")
@RestController
public class User_InfoController {

    /**
     * 修改个人信息 √
     * 修改绑定的手机号 √
     * 修改头像 √
     */

    @Autowired
    User_InfoMapper user_infoMapper;

    @Autowired
    UserMapper userMapper;


    /**
     * 修改账号信息
     * 测速成功
     */
    @RequestMapping(value = "/changeInfo",method = RequestMethod.POST)
    public void changeInfo(HttpServletRequest request,
                           HttpServletResponse response,
                           SimpleUserInfo simpleUserInfo){
        String userId = JwtUtil.parseToken(request);
        if (userId == null){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return;
        }
        String code = user_infoMapper.getUserInfoId(userId);
        if (code == null)
        {
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return;
        }
        String name = simpleUserInfo.getName();
        boolean sex = simpleUserInfo.isSex();
        String describes = simpleUserInfo.getDescribes();
        String email = simpleUserInfo.getEmail();

        User_Info user_info = new User_Info();
        user_info.setSex(sex);
        user_info.setEmail(email);
        user_info.setCode(code);
        user_info.setDescribes(describes);

        user_infoMapper.update(user_info);

        Timestamp updateAt = new Timestamp(System.currentTimeMillis());

        userMapper.updateName(userId,name,updateAt);

    }

    /**
     * 修改手机号
     * 测速成功
     */
    @RequestMapping("/changePhone/{phone}")
    public void changePhone(HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable("phone") String phone){
        //验证手机账号是否为真实,是否为自己的
        //发送验证码
        //暂时不做

        //直接修改手机号
        String userId = JwtUtil.parseToken(request);
        String phone2 = userMapper.getUserPhone(userId);
        if (phone.equals(phone2)){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return;
        }
        Timestamp updateAt = new Timestamp(System.currentTimeMillis());
        userMapper.updatePhone(userId,phone,updateAt);
    }


    @RequestMapping("/changeUserPhoto")
    public void changeUserPhoto(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam("photo")MultipartFile photo){
        
        if (photo == null){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
            return;
        }
        String userId = JwtUtil.parseToken(request);
        String foler = "/"+userId+"/headPhoto/";
        String photoUrl = FileUtil.fileStoreInDisk2(request,response,photo,foler);
        if (!photoUrl.equals("")){
            //上传成功
            Timestamp updateAt = new Timestamp(System.currentTimeMillis());
            user_infoMapper.updateUserHeadPhoto(userId,photoUrl,updateAt);
        }else {
            ResponseInfo.setCode(response, Info.PARAM_ERR);
        }
    }

    @RequestMapping("/getUserHeadPhoto/{userId}")
    public void getUserPhoto(HttpServletRequest request,
                             HttpServletResponse response,
                             @PathVariable("userId")String userId) {
            String photoUrl = user_infoMapper.getUserHeadPhotoUrl(userId);
            FileUtil.fileDownLoad2(response,photoUrl);
    }

}
