package com.swt.jxproject.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.swt.jxproject.annotation.NoCheck;
import com.swt.jxproject.exceptionHandler.BizException;
import com.swt.jxproject.utils.ApplicationContextUtil;
import com.swt.jxproject.utils.JWTUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        JWTUtils jWTUtils = ApplicationContextUtil.getbean("jwtutils", JWTUtils.class);
        boolean pre;
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //有NoCheck注解，跳过认证
        if (method.isAnnotationPresent(NoCheck.class)) {
            return true;
        } else {
            String token;
            try {
                token = httpServletRequest.getHeader("Authorization").substring(7);
            }catch (Exception e){
                throw new BizException("无token，请重新登录");
            }

            if (token == null || token.trim().isEmpty()) {
                throw new BizException("无token，请重新登录");
            }
            try {
                jWTUtils.verifyToken(token);
                return true;
            }catch (JWTVerificationException e){
                throw new BizException("token验证错误");
            }
        }
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
