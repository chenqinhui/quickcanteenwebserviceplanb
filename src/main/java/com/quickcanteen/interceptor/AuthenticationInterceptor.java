package com.quickcanteen.interceptor;


import com.google.common.collect.Lists;
import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.controller.AuthenticationRequiredController;
import com.quickcanteen.dto.Token;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service("authenticationInterceptor")
public class AuthenticationInterceptor extends BaseInterceptor {

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
}
