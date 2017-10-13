package com.quickcanteen.controller.web;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.constants.OrderStatus;
import com.quickcanteen.dto.DishesBean;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.*;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.Dishes;
import com.quickcanteen.model.Order;
import com.quickcanteen.model.OrderDishesKey;
import com.quickcanteen.vo.DishesVo;
import com.quickcanteen.vo.OrderVo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/company")
public class WebCompanyController extends BaseController {
    private static final String MODULE_DISHES = "dishes";
    private static final String MODULE_LOGIN = "login";
    private static final String MODULE_FORMS = "forms";
    private static final String MODULE_DETAIL = "detail";
    private static final String MODULE_WIDGETS = "widgets";
    private static final String MODULE_PANELS = "panels";
    private static final String MODULE_INDEX = "index";
    private static final String MODULE_ORDERS = "orders";
    private static final String MODULE_PROFILE = "profile";

    @Autowired
    DishesMapper dishesMapper;

    @Autowired
    CompanyInfoMapper companyInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDishesMapper orderDishesMapper;

    @RequestMapping(value = "/login")
    public String login(Map<String, Object> model) {
        model.put("module", MODULE_LOGIN);
        return MODULE_LOGIN;
    }

    @RequestMapping(value = "/index")
    @Authentication(Role.Company)
    public String index(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        Double rating = companyInfo.getRating();
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
        List<Order> orderList = orderMapper.selectThisWeekOrderListByCompanyId(companyId);
        if(orderList!=null) {
            for (Order order : orderList) {
                income += order.getTotalPrice();
                int day = order.getCompleteTime().getDay();
                orderNum++;
                dayOrderArr[day] +=1;
            }
        }
        String dayOrderString = "["+ StringUtils.join(dayOrderArr,",")+"]";
        model.put("company_name",companyName);
        model.put("rating",rating);
        model.put("weekIncome",income);
        model.put("weekOrder",orderNum);
        model.put("dayOrderString",dayOrderString);

        model.put("module", MODULE_INDEX);
        return MODULE_INDEX;
    }

    @RequestMapping(value = "/charts")
    @Authentication(Role.Company)
    public String charts(Map<String, Object> model) {
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(getCurrentCompanyId());
        String companyName = companyInfo.getCompanyName();
        model.put("company_name",companyName);
        model.put("module", MODULE_FORMS);
        return "charts";
    }

    @RequestMapping(value = "/forms")
    @Authentication(Role.Company)
    public String forms(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        model.put("module", MODULE_FORMS);
        return MODULE_FORMS;
    }

    @RequestMapping(value = "/detail")
    @Authentication(Role.Company)
    public String detail(Map<String, Object> model) {
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(getCurrentCompanyId());
        String companyName = companyInfo.getCompanyName();
        model.put("company_name",companyName);
        model.put("module", MODULE_DETAIL);
        return MODULE_DETAIL;
    }

    @RequestMapping(value = "/dishes")
    @Authentication(Role.Company)
    public String tables(Map<String, Object> model) {
        List<Dishes> dishesList;
        dishesList = dishesMapper.getDishesByCompanyId(getCurrentCompanyId());
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(getCurrentCompanyId());
        String companyName = companyInfo.getCompanyName();
        model.put("company_name",companyName);
        model.put("dishesList", dishesList);
        model.put("module", MODULE_DISHES);
        return MODULE_DISHES;
    }

    @RequestMapping(value = "/panels")
    @Authentication(Role.Company)
    public String panels(Map<String, Object> model) {
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(getCurrentCompanyId());
        String companyName = companyInfo.getCompanyName();
        model.put("company_name",companyName);
        model.put("module", MODULE_PANELS);
        return "panels";
    }

    @RequestMapping(value = "/widgets")
    @Authentication(Role.Company)
    public String widgets(Map<String, Object> model) {
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(getCurrentCompanyId());
        String companyName = companyInfo.getCompanyName();
        model.put("company_name",companyName);
        model.put("module", MODULE_WIDGETS);
        return MODULE_WIDGETS;
    }

    @RequestMapping(value = "/orders")
    @Authentication(Role.Company)
    public String orders(@RequestParam(value = "status", defaultValue = "0") int status, Map<String, Object> model) {
        List<OrderVo> orderVos;
        if (status == 0) {
            orderVos = orderMapper.selectByCompanyId(getToken().getId(), new RowBounds()).stream().map(this::parse).collect(Collectors.toList());

        } else {
            model.put("status", OrderStatus.valueOf(status));
            orderVos = orderMapper.selectByOrderStatusAndCompanyId(getToken().getId(), status, new RowBounds()).stream().map(this::parse).collect(Collectors.toList());
        }
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(getCurrentCompanyId());
        String companyName = companyInfo.getCompanyName();
        model.put("company_name",companyName);
        model.put("orderList", orderVos);
        model.put("module", MODULE_ORDERS);
        return MODULE_ORDERS;
    }

    @RequestMapping(value = "/profile")
    @Authentication(Role.Company)
    public String profile(Map<String, Object> model) {
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(getCurrentCompanyId());
        String companyName = companyInfo.getCompanyName();
        model.put("company_name",companyName);
        model.put("company_info",companyInfo);
        model.put("module", MODULE_PROFILE);
        return MODULE_PROFILE;
    }

    private OrderVo parse(Order order) {
        OrderVo result = new OrderVo();
        BeanUtils.copyProperties(order, result);
        result.setUserName(userInfoMapper.selectByPrimaryKey(order.getUserId()).getRealName());
        List<Dishes> dishesList = dishesMapper.selectDishesByOrderId(result.getOrderId());
        List<DishesVo> dishesVos = dishesList.stream().map(this::parse).collect(Collectors.toList());
        for(DishesVo dishesVo :dishesVos){
            OrderDishesKey orderDishesKey = new OrderDishesKey(result.getOrderId(),dishesVo.getDishesId());
            dishesVo.setCount(orderDishesMapper.selectByPrimaryKey(orderDishesKey).getCount());
        }
        result.setDishesVos(dishesVos);
        return result;
    }

    private DishesVo parse(Dishes dishes) {
        DishesVo result = new DishesVo();
        BeanUtils.copyProperties(dishes,result);
        result.setCount(0);
        return result;
    }
}
