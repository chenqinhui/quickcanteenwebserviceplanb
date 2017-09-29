package com.quickcanteen.controller.web;

import com.quickcanteen.mapper.CompanyInfoMapper;
import com.quickcanteen.mapper.OrderMapper;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.Order;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/company")
public class WebIndexController extends BaseController{
    @Autowired
    CompanyInfoMapper companyInfoMapper;
    @Autowired
    OrderMapper orderMapper;

    @RequestMapping(value = "/index")
    public String index(Map<String,Object> model){
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        Double rating = companyInfo.getRating();
        Map map = weekResult();
        Double weekIncome = (Double) map.get("weekIncome");
        int weekOrder = (Integer)map.get("weekOrderNum");
        Integer[] dayOrderArr = (Integer[])map.get("dayOrderArr");
        String dayOrderString = "["+StringUtils.join(dayOrderArr,",")+"]";
        model.put("company_name",companyName);
        model.put("rating",rating);
        model.put("weekIncome",weekIncome);
        model.put("weekOrder",weekOrder);
        model.put("dayOrderString",dayOrderString);
        return "index";
    }

    public Map<String,Object> weekResult(){
        Map map = new HashMap();
        int companyId = getCurrentCompanyId();
        Double income = 0.0;
        int orderNum = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        String curDateString = sdf.format(calendar.getTime());

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String startDateString = sdf.format(calendar.getTime());

        Integer[] dayOrderArr = new Integer[7];
        for(int i=0;i<dayOrderArr.length;i++){
            dayOrderArr[i]=0;
        }
        int num = 0;
        List<Order> orderList = orderMapper.selectThisWeekOrderListByCompanyId(companyId);
        if(orderList!=null) {
            for (Order order : orderList) {
                income += order.getTotalPrice();
                int day = order.getCompleteTime().getDay();
                orderNum++;
                dayOrderArr[day] +=1;
            }
        }
        map.put("weekIncome",income);
        map.put("weekOrderNum",orderNum);
        map.put("dayOrderArr",dayOrderArr);
        return map;
    }

}
