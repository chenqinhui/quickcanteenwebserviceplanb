package com.quickcanteen.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping(value = "")
    public String login(Map<String,Object> model) {
        return "redirect:/company/login";
    }
}
