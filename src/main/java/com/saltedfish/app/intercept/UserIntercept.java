package com.saltedfish.app.intercept;


import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.mapper.FileUrlMapper;
import com.saltedfish.app.session.Session;
import com.saltedfish.app.verification.CookieUtil;
import com.saltedfish.app.verification.UserVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.saltedfish.app.constant.Constants.SESSION_TOKEN;


@Component
public class UserIntercept extends HandlerInterceptorAdapter {

    @Autowired
    FileUrlMapper fileUrlMapper;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("content-type", "text/html;charset=UTF-8");

        boolean flag = true;

        //List<FileUrl> fileUrlList = fileUrlMapper.getCurrPageByDesc("stxj",0,2);

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();

        //需要拦截
        if (clazz.isAnnotationPresent(Auth.class) ||
                method.isAnnotationPresent(Auth.class)) {

            String client_token = CookieUtil.getValue(request,"UserToken");

            if (client_token != null) {
//                if (UserVerification.userVerificationByToken(request)){
                String server_token = Session.getSession().get(SESSION_TOKEN);
                if (server_token.equals(client_token)){
                    //验证成功
                   flag = true;
                }else {
                    flag = false;
                }
            }else {
                flag = false;
            }

            if (flag){
                //已经通过验证
                ResponseInfo info = new ResponseInfo().setCode(Info.SUCCESS);
                response.setStatus(info.getCode());
                response.addHeader("message",info.getMessage());
            }else {
                //未通过验证
                ResponseInfo info = new ResponseInfo().setCode(Info.NOT_LOGIN);
                response.setStatus(info.getCode());
                response.addHeader("message",info.getMessage());
            }
        }


        return flag;

    }

}
