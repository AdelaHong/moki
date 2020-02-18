package com.im.moki.web.token;

import com.im.moki.common.expection.BaseException;
import com.im.moki.common.expection.ErrorCodeEnum;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String SECRET = "jwtsecrettest";
    private static final String ISS = "leopard";
    public static final long EXPIRATION = 3600L; //过期时间 一小时
    public static final long EXPIRATION_REMEMBER = 604800L; //选择记住我之后的过期时间为7天

    /*
     * 生成token方法
     * @param username 
     * @param isRemenberMe
     */
    public static String generateToken(String id, String username,boolean isRemenberMe) {

        long expiration = isRemenberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET)
            .setIssuer(ISS)
            .setId(id)
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
            .compact();
    }


    /**
      * 从token中获取用户名
      * @param token
      * @return
      * @throws BusinessExpection 
      */
    public static String getUsername(String token) throws BaseException {
        return getTokenBody(token).getSubject();
    }


     /**
      * 从token中获取ID，同时做解密处理
      * @param token
      * @return
      * @throws BusinessExpection 
      */
    public static String getObjectId(String token) throws BaseException{
        return getTokenBody(token).getId();
    }


    /**
      * 是否已过期
      * @param token
      * @throws     过期无法判断，只能通过捕获ExpiredJwtException异常
      * @return
      * @throws BusinessExpection 
      */
     @Deprecated
    public static boolean isExpiration(String token) throws BaseException{
        return getTokenBody(token).getExpiration().before(new Date());
    }


    /*
     * 获取token信息，同时也做校验处理
     * 
     */
    public static Claims getTokenBody(String token) throws BaseException {
        try {
            return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        } catch (ExpiredJwtException expired) {
            // 过期
            throw new BaseException(ErrorCodeEnum.PAST_TOKEN);
        } catch(SignatureException e){
            //无效
            throw new BaseException(ErrorCodeEnum.PAST_TOKEN);
        }catch(MalformedJwtException malformedJwt) {
            //无效
            throw new BaseException(ErrorCodeEnum.PAST_TOKEN);
        }
    }


    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token,String id, String username,boolean isRemenberMe) {
        String refreshedToken;
        try {
            Claims claims = getTokenBody(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(id, username, isRemenberMe);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public static void main(String [] args){
        System.out.println(JwtTokenUtil.generateToken("1","1",false));
    }
}
