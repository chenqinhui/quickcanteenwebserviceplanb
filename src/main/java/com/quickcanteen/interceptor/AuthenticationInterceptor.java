package com.quickcanteen.interceptor;


import com.google.common.collect.Lists;
import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.controller.AuthenticationRequiredController;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.Token;
import com.quickcanteen.service.TokenService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service("authenticationInterceptor")
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Authentication authentication = ((HandlerMethod) handler).getMethodAnnotation(Authentication.class);

            //没有声明需要权限,或者声明不验证权限
            if (authentication == null) {
                return true;
            } else {
                Token token = getToken(httpServletRequest);
                if (handlerMethod.getBean() instanceof AuthenticationRequiredController) {
                    ((AuthenticationRequiredController) handlerMethod.getBean()).setToken(token);
                }
                if (token == null /*|| token.isExpired()*/) {
                    makeResponse(handlerMethod.getBean(), httpServletRequest, httpServletResponse);
                    return false;
                }
                if (Lists.newArrayList(authentication.value()).contains(token.getRole())) {
                    return true;
                } else {
                    makeResponse(handlerMethod.getBean(), httpServletRequest, httpServletResponse);
                    return false;
                }
            }
        } else {
            return true;
        }
    }

    private void makeResponse(Object controller, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if (controller.getClass().isAnnotationPresent(RestController.class) ||
                controller.getClass().getSuperclass().isAnnotationPresent(RestController.class)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            BaseJson baseJson = new BaseJson();
            baseJson.setReturnCode("E.1");
            baseJson.setErrorMessage("没有权限");
            JSONObject jsonObject = JSONObject.fromObject(baseJson);
            httpServletResponse.setCharacterEncoding("UTF8");
            httpServletResponse.getWriter().println(jsonObject.toString());
        } else {
            //httpServletResponse.sendRedirect("/signin?redirectUrl=" + httpServletRequest.getRequestURI());
        }
    }

    private String getTokenFromHeaderOrCookie(HttpServletRequest request, String key) {
        String header = getHeader(request, key);
        if (header != null) {
            return header;
        }
        String cookie = getCookie(request, key);
        return cookie;
    }

    private String getHeader(HttpServletRequest request, String key) {
        return request.getHeader(key);
    }

    private String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    private Token getToken(HttpServletRequest request) {
        String tokenString = getTokenFromHeaderOrCookie(request, "X-TOKEN");
        if (tokenString == null) {
            return null;
        }
        try {
            return tokenService.parseToken(tokenString);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
