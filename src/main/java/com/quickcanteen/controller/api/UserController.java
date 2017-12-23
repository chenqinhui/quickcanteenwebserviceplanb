package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseBean;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.CompanyInfoBean;
import com.quickcanteen.dto.Role;
import com.quickcanteen.dto.UserInfoBean;
import com.quickcanteen.mapper.UserInfoMapper;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.UserInfo;
import com.quickcanteen.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * Created by 11022 on 2017/8/19.
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends APIBaseController {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/register/{accountNumber}/{userPassword}/{telephone}/{realName}",
                    method = RequestMethod.POST)
    public BaseJson register(@PathVariable String accountNumber,
                             @PathVariable String userPassword,
                             @PathVariable String telephone,
                             @PathVariable String realName) {
        BaseJson baseJson = new BaseJson();
        UserInfo userInfo = userInfoMapper.selectByAccountNumber(accountNumber);
        if (userInfo != null) {
            baseJson.setReturnCode("1.0.E.4");
            baseJson.setErrorMessage("学号已经被注册");
        } else {
            int year = 2000 + Integer.parseInt(accountNumber.substring(2, 4));
            String date = String.valueOf(year) + "-09-01";
            Date entranceYear = Date.valueOf(date);
            userInfo = new UserInfo(accountNumber, userPassword, telephone, realName, entranceYear);
            userInfoMapper.insertSelective(userInfo);
            baseJson.setReturnCode("1.0");
            baseJson.setErrorMessage("成功");
            String token = tokenService.generateToken(Role.User, userInfo.getUserId());
            BaseBean baseBean = new BaseBean();
            baseBean.setSingleResult(token + " " + userInfo.getUserId());
            baseJson.setObj(baseBean);
        }
        return baseJson;
    }

    @RequestMapping(value = "/login/{accountNumber}/{userPassword}",
                    method = RequestMethod.GET)
    public BaseJson login(@PathVariable String accountNumber, @PathVariable String userPassword) {
        BaseJson baseJson = new BaseJson();
        UserInfo userInfo = userInfoMapper.selectByAccountNumber(accountNumber);
        if (userInfo == null) {
            baseJson.setReturnCode("2.0.E.1");
            baseJson.setErrorMessage("学号未被注册");
        } else if (!userInfo.getUserPassword().equals(userPassword)) {
            baseJson.setReturnCode("2.0.E.2");
            baseJson.setErrorMessage("学号和密码不匹配");
        } else {
            baseJson.setReturnCode("2.0");
            baseJson.setErrorMessage("成功");
            String token = tokenService.generateToken(Role.User, userInfo.getUserId());
            BaseBean baseBean = new BaseBean();
            baseBean.setSingleResult(token + " " + userInfo.getUserId());
            baseJson.setObj(baseBean);
        }
        return baseJson;
    }

    @RequestMapping(value = "/Password/{userIDString}/{userPassword}/{newPassword}",
                    method = RequestMethod.PUT)
    @ResponseBody
    @Authentication({Role.User, Role.Admin})
    public BaseJson editPassword(@PathVariable String userIDString,
                                 @PathVariable String userPassword,
                                 @PathVariable String newPassword) {
        BaseJson baseJson = new BaseJson();
        int userID = Integer.parseInt(userIDString);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userID);
        switch (getToken().getRole()) {
            case User:
                if (getToken().getId() == userID) {
                    if (!userInfo.getUserPassword().equals(userPassword)) {
                        baseJson.setReturnCode("6.0.E.1");
                        baseJson.setErrorMessage("原密码错误");
                        String token = tokenService.generateToken(Role.User, userInfo.getUserId());
                        BaseBean baseBean = new BaseBean();
                        baseBean.setSingleResult(token + " " + userInfo.getUserId());
                        baseJson.setObj(baseBean);
                    } else {
                        userInfo.setUserPassword(newPassword);
                        userInfoMapper.updateByPrimaryKey(userInfo);
                        baseJson.setReturnCode("6.0");
                        baseJson.setErrorMessage("修改成功");
                        String token = tokenService.generateToken(Role.User, userInfo.getUserId());
                        BaseBean baseBean = new BaseBean();
                        baseBean.setSingleResult(token + " " + userInfo.getUserId());
                        baseJson.setObj(baseBean);
                    }
                } else {
                    return getUnauthorizedResult();
                }
                break;
            case Admin:
                userInfo.setUserPassword(newPassword);
                userInfoMapper.updateByPrimaryKey(userInfo);
                baseJson.setReturnCode("6.0");
                baseJson.setErrorMessage("修改成功");
                String token = tokenService.generateToken(Role.User, userInfo.getUserId());
                BaseBean baseBean = new BaseBean();
                baseBean.setSingleResult(token + " " + userInfo.getUserId());
                baseJson.setObj(baseBean);
                break;
        }
        return baseJson;
    }

    @RequestMapping(value = "/UserInfo/{userIDString}/{userPassword}/{infoType}/{correctInfo}",
                    method = RequestMethod.PUT)
    @ResponseBody
    @Authentication({Role.User, Role.Admin})
    public BaseJson editUserInfo(@PathVariable String userIDString,
                                 @PathVariable String userPassword,
                                 @PathVariable String infoType,
                                 @PathVariable String correctInfo) {
        BaseJson baseJson = new BaseJson();
        int userID = Integer.parseInt(userIDString);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userID);
        switch (getToken().getRole()) {
            case User:
                if (getToken().getId() == userID) {
                    if (!userInfo.getUserPassword().equals(userPassword)) {
                        baseJson.setReturnCode("6.0.E.1");
                        baseJson.setErrorMessage("密码错误");
                        String token = tokenService.generateToken(Role.User, userInfo.getUserId());
                        BaseBean baseBean = new BaseBean();
                        baseBean.setSingleResult(token + " " + userInfo.getUserId());
                        baseJson.setObj(baseBean);
                    } else {
                        switch (infoType) {
                            case "telephone":
                                userInfo.setTelephone(correctInfo);
                                userInfoMapper.updateByPrimaryKey(userInfo);
                        }
                        baseJson.setReturnCode("6.0");
                        baseJson.setErrorMessage("修改成功");
                        String token = tokenService.generateToken(Role.User, userInfo.getUserId());
                        BaseBean baseBean = new BaseBean();
                        baseBean.setSingleResult(token + " " + userInfo.getUserId());
                        baseJson.setObj(baseBean);
                    }
                } else {
                    return getUnauthorizedResult();
                }
                break;
            case Admin:
                switch (infoType) {
                    case "telephone":
                        userInfo.setTelephone(correctInfo);
                }
                baseJson.setReturnCode("6.0");
                baseJson.setErrorMessage("修改成功");
                String token = tokenService.generateToken(Role.User, userInfo.getUserId());
                BaseBean baseBean = new BaseBean();
                baseBean.setSingleResult(token + " " + userInfo.getUserId());
                baseJson.setObj(baseBean);
                break;
        }
        return baseJson;
    }

    @RequestMapping(value = "/UserInfo/{userID}",method = RequestMethod.GET)
    @Authentication({Role.User, Role.Admin})
    public BaseJson getUserInfoByUserID(@PathVariable int userID) {
        BaseJson baseJson = new BaseJson();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userID);
        switch (getToken().getRole()) {
            case Admin:
                baseJson.setObj(parse(userInfo));
                break;
            case User:
                if (getToken().getId() == userID) {
                    baseJson.setObj(parse(userInfo));
                } else {
                    return getUnauthorizedResult();
                }
                break;
            default:
                return getUnauthorizedResult();
        }
        baseJson.setReturnCode("4.0");
        return baseJson;
    }

    @RequestMapping(value = "/Deliver/{userID}", method = RequestMethod.PUT)
    @Authentication({Role.User, Role.Admin})
    public BaseJson signUpForDeliver(@PathVariable int userID) {
        BaseJson baseJson = new BaseJson();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userID);
        if (!userInfo.getDeliver()) {
            userInfo.setDeliver(true);
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            baseJson.setObj(parse(userInfo));
            baseJson.setReturnCode("4.0");
        } else {
            baseJson.setObj(parse(userInfo));
            baseJson.setReturnCode("4.0.E.1");
            baseJson.setErrorMessage("你已经申请过了");
        }
        return baseJson;
    }

    private CompanyInfoBean parse(CompanyInfo companyInfo) {
        CompanyInfoBean result = new CompanyInfoBean();
        BeanUtils.copyProperties(companyInfo, result);
        return result;
    }

    private UserInfoBean parse(UserInfo userInfo) {
        UserInfoBean userInfoBean = new UserInfoBean();
        try {
            BeanUtils.copyProperties(userInfo, userInfoBean);
            userInfoBean.setEntranceYear(userInfo.getEntranceYear().getTime());
        } catch (Exception e) {

        }
        return userInfoBean;
    }
}
