package com.platform.interceptor;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.SysTokenVo;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysTokenService;
import com.platform.service.SysUserService;
import com.platform.utils.ApiRRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: platform
 * @description: 权限(Token)验证
 * @author: Yuan
 * @create: 2020-09-04 09:49
 **/
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysTokenService tokenService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    public static final String LOGIN_TOKEN_KEY = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(LOGIN_TOKEN_KEY);
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            // 如果有@IgnoreAuth注解，则不验证token
            if (annotation != null) {
                return true;
            } else {
                throw new ApiRRException("请先登录", 401);
            }
        }

        if (request.getAttribute(LOGIN_USER_KEY) == null) {

            SysTokenVo sysTokenVo = tokenService.queryByToken(token);
            SysUserEntity userEntity = userService.queryByAdminId(sysTokenVo.getUserId().toString());
            if (userEntity == null && annotation == null) {
                throw new ApiRRException("请先登录", 401);
            }
            //设置adminId到request里，后续根据adminId，获取用户信息
            request.setAttribute(LOGIN_USER_KEY, userEntity);
        }
        return true;
    }
}
