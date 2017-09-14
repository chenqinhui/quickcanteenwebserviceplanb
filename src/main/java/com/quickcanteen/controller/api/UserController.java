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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/login")
    public BaseJson login(@RequestParam("accountNumber") String accountNumber, @RequestParam("userPassword") String userPassword) {
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

    @RequestMapping(value = "/editPassword")
    @ResponseBody
    @Authentication({Role.User, Role.Admin})
    public BaseJson editPassword(@RequestParam("userID") String userIDString, @RequestParam("userPassword") String userPassword, @RequestParam("newPassword") String newPassword) {
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

    @RequestMapping(value = "/editUserInfo")
    @ResponseBody
    @Authentication({Role.User, Role.Admin})
    public BaseJson editUserInfo(@RequestParam("userID") String userIDString, @RequestParam("userPassword") String userPassword, @RequestParam("infoType") String infoType, @RequestParam("correctInfo") String correctInfo) {
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

    @RequestMapping(value = "/getUserInfoByUserID")
    @Authentication({Role.User, Role.Admin})
    public BaseJson getUserInfoByUserID(@RequestParam("userID") int userID) {
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
