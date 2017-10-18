package com.quickcanteen.controller.web;

import com.quickcanteen.controller.AuthenticationRequiredController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class BaseController extends AuthenticationRequiredController {
    @Autowired
    protected HttpServletRequest request;

    public int getCurrentCompanyId(){
        return getToken().getId();
    }
}
