package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.*;
import com.quickcanteen.mapper.CollectDishesMapper;
import com.quickcanteen.mapper.CompanyInfoMapper;
import com.quickcanteen.mapper.DishesMapper;
import com.quickcanteen.model.CollectDishes;
import com.quickcanteen.model.CollectDishesKey;
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

    @Autowired
    private CollectDishesMapper collectDishesMapper;

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

    @RequestMapping(value = "/getCollectStatusByUserIdByDishesId")
    @Authentication
    public BaseJson getCollectStatusByUserIdByDishesId(@RequestParam("dishesId") Integer dishesId,@RequestParam("userId")Integer userId) {
        BaseJson baseJson = new BaseJson();
        BaseBean baseBean = new BaseBean();
        int collectStatus = 0;
        CollectDishesKey collectDishesKey = new CollectDishesKey(dishesId,userId);
        CollectDishes collectDishes = collectDishesMapper.selectByPrimaryKey(collectDishesKey);
        if(collectDishes!=null)
            collectStatus = 1;
        baseBean.setSingleResult(String.valueOf(collectStatus));
        baseJson.setObj(baseBean);
        if(collectStatus==0) {
            baseJson.setReturnCode("0");
            baseJson.setErrorMessage("未收藏");
        }
        else{
            baseJson.setReturnCode("1");
            baseJson.setErrorMessage("已收藏");
        }
        return baseJson;
    }


    @RequestMapping(value = "/changeCollectStatusByDishesByUserId")
    @Authentication
    public BaseJson getCollectStatusByUserIdByDishesId(@RequestParam("dishesId") Integer dishesId,@RequestParam("userId")Integer userId,@RequestParam("isCollect")Integer isCollect) {
        BaseJson baseJson = new BaseJson();
        BaseBean baseBean = new BaseBean();
        CollectDishesKey collectDishesKey = new CollectDishesKey(dishesId,userId);
        if(isCollect==0) {
            int collectResult = collectDishesMapper.insertSelective(collectDishesKey);
            if(collectResult==1){
                baseBean.setSingleResult(String.valueOf(collectResult));
                baseJson.setObj(baseBean);
                baseJson.setReturnCode("1");
                baseJson.setErrorMessage("收藏成功");
            }
            else{
                baseBean.setSingleResult(String.valueOf(collectResult));
                baseJson.setObj(baseBean);
                baseJson.setReturnCode("E.1");
                baseJson.setErrorMessage("收藏失败");
            }
        }
        else{
            int unCollectResult = collectDishesMapper.deleteByPrimaryKey(collectDishesKey);
            if(unCollectResult==1){
                baseBean.setSingleResult(String.valueOf(unCollectResult));
                baseJson.setObj(baseBean);
                baseJson.setReturnCode("0");
                baseJson.setErrorMessage("取消收藏成功");
            }
            else{
                baseBean.setSingleResult(String.valueOf(unCollectResult));
                baseJson.setObj(baseBean);
                baseJson.setReturnCode("E.0");
                baseJson.setErrorMessage("取消收藏失败");
            }
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
