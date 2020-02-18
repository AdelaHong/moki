package com.im.moki.web.token;

import com.im.moki.common.expection.BaseException;
import com.im.moki.common.expection.ErrorCodeEnum;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor  implements HandlerInterceptor {
    //@Autowired
    //UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        // 执行认证
        if (token == null) {
            throw new BaseException(ErrorCodeEnum.NO_TOKEN);
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JwtTokenUtil.getObjectId(token);
        } catch (Exception j) {
            throw new Exception("token无效");
        }
        /*User user = userService.findUserById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }*/
        // 验证 token
        try {
            Jwts.parser()
                    .setSigningKey(JwtTokenUtil.SECRET)
                    .parseClaimsJws(token);

        } catch (ExpiredJwtException expired) {
            throw new BaseException(ErrorCodeEnum.PAST_TOKEN);
        } catch(SignatureException e){
            throw new BaseException(ErrorCodeEnum.PAST_TOKEN);
        } catch(MalformedJwtException malformedJwt) {
            throw new BaseException(ErrorCodeEnum.PAST_TOKEN);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
            Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        Object o, Exception e) throws Exception {
    }
}
