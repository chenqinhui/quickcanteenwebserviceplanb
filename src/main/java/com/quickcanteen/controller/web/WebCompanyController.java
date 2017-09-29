package com.quickcanteen.controller.web;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.constants.OrderStatus;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.*;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.Dishes;
import com.quickcanteen.model.Order;
import com.quickcanteen.vo.OrderVo;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
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

    @Autowired
    DishesMapper dishesMapper;

    @Autowired
    CompanyInfoMapper companyInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private OrderMapper orderMapper;

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
        model.put("company_name", companyName);
        model.put("module", MODULE_INDEX);
        return MODULE_INDEX;
    }

    @RequestMapping(value = "/charts")
    @Authentication(Role.Company)
    public String charts(Map<String, Object> model) {
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
        model.put("module", MODULE_PANELS);
        return "panels";
    }

    @RequestMapping(value = "/widgets")
    @Authentication(Role.Company)
    public String widgets(Map<String, Object> model) {
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
        model.put("orderList", orderVos);
        model.put("module", MODULE_ORDERS);
        return MODULE_ORDERS;
    }

    private OrderVo parse(Order order) {
        OrderVo result = new OrderVo();
        BeanUtils.copyProperties(order, result);
        result.setUserName(userInfoMapper.selectByPrimaryKey(order.getUserId()).getRealName());
        return result;
    }
}
