package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.*;
import com.quickcanteen.mapper.CompanyInfoMapper;
import com.quickcanteen.mapper.DishesMapper;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.Dishes;
import com.quickcanteen.util.ObjectParser;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dishes")
public class DishesController extends APIBaseController{
    @Autowired
    private DishesMapper dishesMapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @RequestMapping(value = "/getCollectDishesListByUserId")
    @Authentication
    public BaseJson getCollectDishesListByUserId(@RequestParam("userId") Integer userId,@RequestParam("pageNumber")Integer pageNumber,@RequestParam("pageSize")Integer pageSize) {
        BaseJson baseJson = new BaseJson();
        List<Dishes> collectDishesList= dishesMapper.getCollectDishesListByUserIdByPage(userId,new RowBounds(pageNumber*pageSize,pageSize));
        List<DishesBean> collectDishesBeanList = collectDishesList.stream().map(this::parse).collect(Collectors.toList());
        //baseJson.setObj(collectDishesBeanList);

        if(collectDishesBeanList.size() != 0) {
            baseJson.setObj(collectDishesBeanList);
            baseJson.setReturnCode("8.0");
            baseJson.setErrorMessage("成功");
        }
        else{
            baseJson.setObj(null);
            baseJson.setReturnCode("8.0.E.1");
            baseJson.setErrorMessage("没有收藏的菜品");
        }
        return baseJson;
    }

    private DishesBean parse(Dishes dishes){
        DishesBean result=new DishesBean();
        BeanUtils.copyProperties(dishes,result);
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(dishes.getCompanyId());
        result.setCompanyName(companyInfo.getCompanyName());
        return result;
    }

}
