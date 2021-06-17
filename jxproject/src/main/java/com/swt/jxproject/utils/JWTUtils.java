package com.swt.jxproject.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("jwtutils")
public class JWTUtils {

    /**
     * 过期时间1小时
     */
    private static final int EXPIRE_TIME = 60;
    /**
     * jwt 密钥
     */
    @Value("${jwt.secret}")
    private  String secret;

    public String createToken(String userId, String userName) throws Exception {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, EXPIRE_TIME);
        Date expireDate = nowTime.getTime();
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create()
                .withHeader(map)//头
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withSubject("jxproject")//
                .withIssuedAt(new Date())//签名时间
                .withExpiresAt(expireDate)//过期时间
                .sign(Algorithm.HMAC256(secret));//签名
        return token;
    }

    /**
     * 验证Token
     * @param token
     * @return
     * @throws Exception
     */
    public Map<String, Claim> verifyToken(String token)throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            throw new RuntimeException("凭证已过期，请重新登录");
        }
        return jwt.getClaims();
    }

    /**
     * 解析Token
     * @param token
     * @return
     */
    private static Map<String, Claim> parseToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }

    public static String getUserName(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        String userName = decodedJWT.getClaims().get("userName").asString();
        return userName;
    }

    public static String getUserId(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        String userId = decodedJWT.getClaims().get("userId").asString();
        return userId;
    }
}
