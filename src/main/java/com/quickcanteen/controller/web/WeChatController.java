package com.quickcanteen.controller.web;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.Role;
import com.quickcanteen.model.CompanyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value = "/weChat")
public class WeChatController extends BaseController{
    private static final String MODULE_V_INDEX = "wechat/index";
    private static final String MODULE_V_LOGIN = "wechat/login";

    @RequestMapping(value = "/index")
    @Authentication(Role.User)
    public String index(Map<String, Object> model) {
        model.put("module", MODULE_V_INDEX);
        return MODULE_V_INDEX;
    }

    @RequestMapping(value = "/login")
    public String login(Map<String, Object> model) {
        model.put("module", MODULE_V_LOGIN);
        return MODULE_V_LOGIN;
    }


}
