package com.quickcanteen.controller.api;

import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.CompanyInfoBean;
import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.controller.api.APIBaseController;
import com.quickcanteen.mapper.CompanyInfoMapper;
import com.quickcanteen.model.CompanyInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Cynthia on 2017/7/14.
 */

@RestController
@RequestMapping("/api/main")
public class MainController extends APIBaseController {
    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @RequestMapping(value = "/getCompanyInfoByPage")
    @Authentication
    public BaseJson getCompanyInfoByPage(@RequestParam("pageNumber")int pageNumber, @RequestParam("pageSize")int pageSize) {
        BaseJson baseJson = new BaseJson();
        List<CompanyInfo> companyInfoList = companyInfoMapper.getCompanyInfoByPage(new RowBounds(pageNumber*pageSize,pageSize));
        List<CompanyInfoBean> companyInfoBeans= companyInfoList.stream().map(this::parse).collect(Collectors.toList());
        if (companyInfoBeans == null) {
            return getResourceNotFoundResult();
        }
        switch (getToken().getRole()) {
            case User:
                baseJson.setObj(companyInfoBeans);
                baseJson.setErrorMessage("成功");
                baseJson.setReturnCode("4.0");
                break;
            case Admin:
                baseJson.setObj(companyInfoBeans);
                baseJson.setErrorMessage("成功");
                baseJson.setReturnCode("4.0");
                break;
            case Company:
                baseJson.setObj(companyInfoBeans);
                baseJson.setErrorMessage("成功");
                baseJson.setReturnCode("4.0");
                break;
            default:
                return getUnauthorizedResult();
        }
        return baseJson;
    }

    private CompanyInfoBean parse(CompanyInfo companyInfo){
        CompanyInfoBean result=new CompanyInfoBean();
        BeanUtils.copyProperties(companyInfo,result);
        return result;
    }
}
