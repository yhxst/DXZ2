package com.saltedfish.app.verification;

import com.saltedfish.app.session.Session;
import com.saltedfish.app.util.JwtUtil;
import com.saltedfish.app.util.MD5;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.saltedfish.app.constant.Constants.*;

public class CookieUtil {
    public static void create(HttpServletResponse httpServletResponse, String name, String token, Boolean secure, Integer maxAge, String domain) {
        Cookie cookie = new Cookie(name, token);
        cookie.setSecure(secure);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setDomain(DOMAIN);
        httpServletResponse.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Cookie tokenCookie = null;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(COOKIENAME))
                tokenCookie = cookie;
        }


        //Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return tokenCookie.getValue() != null ? tokenCookie.getValue() : null;
    }

    public static Cookie create(String code) {

        //String token = JwtUtil.generateToken(SIGNINGKEY,code);

        long time = System.currentTimeMillis();
        String code1 = code + time;
        String token = MD5.getMD5(code1);
        Session.getSession().put(SESSION_TOKEN,token);

        Cookie cookie = new Cookie(COOKIENAME, token);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*3600);
        cookie.setDomain(DOMAIN);//切换网络时，记得修改
        cookie.setPath("/");
        return cookie;
    }
}

