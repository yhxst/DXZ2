package com.saltedfish.app.util;

import com.saltedfish.app.verification.CookieUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.saltedfish.app.constant.Constants.COOKIENAME;
import static com.saltedfish.app.constant.Constants.SIGNINGKEY;

public class JwtUtil {
    private static final String REDIS_SET_ACTIVE_SUBJECTS = "active-subjects";

    public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        String token = builder.compact();

        RedisUtil.INSTANCE.sadd(REDIS_SET_ACTIVE_SUBJECTS, subject);

        return token;
    }

    public static String parseToken(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(token == null) {
            return null;
        }
        String subject;
        try {
          subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return null;
        }

        if (!RedisUtil.INSTANCE.sismember(REDIS_SET_ACTIVE_SUBJECTS, subject)) {
            return null;
        }

        //返回 userCode
        return subject;
    }

    public static String parseToken(HttpServletRequest httpServletRequest){
        String TokenName = COOKIENAME;
        String token = CookieUtil.getValue(httpServletRequest, TokenName);
        if(token == null) {
            return null;
        }
        String subject;
        try {
            subject = Jwts.parser().setSigningKey(SIGNINGKEY).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return null;
        }

        if (!RedisUtil.INSTANCE.sismember(REDIS_SET_ACTIVE_SUBJECTS, subject)) {
            return null;
        }

        //返回 userCode
        return subject;
    }
    public static void invalidateRelatedTokens(HttpServletRequest httpServletRequest) {
        RedisUtil.INSTANCE.srem(REDIS_SET_ACTIVE_SUBJECTS, (String) httpServletRequest.getAttribute("username"));
    }
}

