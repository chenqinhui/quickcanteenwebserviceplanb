package com.quickcanteen.controller.api;

import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.CompanyInfoBean;
import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.controller.api.APIBaseController;
import com.quickcanteen.dto.DishesBean;
import com.quickcanteen.mapper.CompanyInfoMapper;
import com.quickcanteen.mapper.DishesMapper;
import com.quickcanteen.mapper.OrderDishesMapper;
import com.quickcanteen.mapper.OrderMapper;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.Dishes;
import com.quickcanteen.model.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Cynthia on 2017/7/14.
 */

@RestController
@RequestMapping("/api/main")
public class MainController extends APIBaseController {
    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private DishesMapper dishesMapper;

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

    @RequestMapping(value = "/getRecommendListByUserId")
    @Authentication
    public BaseJson getRecommendListByUserId(@RequestParam("userId") int userId){
        BaseJson baseJson = new BaseJson();
        List<Dishes> dishesList = dishesMapper.getDishesByUserId(userId);
        List<DishesBean> recommendList = new ArrayList<>();
        DishesBean dishesBean = new DishesBean();
        for(int i=0;i<5;i++){
            if(i<dishesList.size()) {
                dishesBean = new DishesBean(dishesList.get(i));
            }
            else{
                dishesBean = new DishesBean(dishesMapper.selectByPrimaryKey(i));
            }
            recommendList.add(dishesBean);
        }
        baseJson.setReturnCode("10.0");
        baseJson.setObj(recommendList);
        baseJson.setErrorMessage("成功");
        return baseJson;
    }

    private CompanyInfoBean parse(CompanyInfo companyInfo){
        CompanyInfoBean result=new CompanyInfoBean();
        BeanUtils.copyProperties(companyInfo,result);
        return result;
    }
}
