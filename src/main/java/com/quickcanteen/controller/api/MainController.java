package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.CompanyInfoBean;
import com.quickcanteen.dto.DishesBean;
import com.quickcanteen.mapper.CompanyInfoMapper;
import com.quickcanteen.mapper.DishesMapper;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.Dishes;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "/CompanyInfo/{pageNumber}/{pageSize}", method = RequestMethod.GET)
    @Authentication
    public BaseJson getCompanyInfoByPage(@PathVariable int pageNumber,@PathVariable int pageSize) {
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

    @RequestMapping(value = "/RecommendList/{userId}", method = RequestMethod.GET)
    @Authentication
    public BaseJson getRecommendListByUserId(@PathVariable int userId){
        BaseJson baseJson = new BaseJson();
        List<Dishes> dishesList = dishesMapper.getDishesByUserId(userId);
        List<DishesBean> recommendList = new ArrayList<>();
        DishesBean dishesBean = new DishesBean();
        int dishesCount = dishesList.size();

        //推荐5个菜，从用户最近点单中选择。如果最近点菜数不超过5个，则选择菜品中评分最高的补全。
        int highRatingDishesCount = 5 - dishesList.size();
        List<Dishes> highRatingDishesList = dishesMapper.selectHighRatingDishesByCount(highRatingDishesCount);
        dishesList.addAll(highRatingDishesList);
        recommendList = dishesList.stream().map(this::parse).collect(Collectors.toList());

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

    private DishesBean parse(Dishes dishes){
        DishesBean result=new DishesBean();
        BeanUtils.copyProperties(dishes,result);
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(dishes.getCompanyId());
        result.setCompanyName(companyInfo.getCompanyName());
        return result;
    }


}
