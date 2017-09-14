package com.quickcanteen.controller.api;

import com.google.common.collect.Lists;
import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseBean;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.OrderBean;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.*;
import com.quickcanteen.model.Order;
import com.quickcanteen.model.OrderDishes;
import com.quickcanteen.model.TimeSlot;
import com.quickcanteen.util.DateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by 11022 on 2017/8/20.
 */
@RestController
@RequestMapping("/api/order")
public class OrderController extends APIBaseController {
    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Autowired
    private TimeSlotMapper timeSlotMapper;
    @Autowired
    private OrderDishesMapper orderDishesMapper;
    @Autowired
    private DishesMapper dishesMapper;

    @RequestMapping(value = "/getOrderById")
    @Authentication
    public BaseJson getOrderById(@RequestParam("orderId") Integer orderId) {
        BaseJson baseJson = new BaseJson();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            return getResourceNotFoundResult();
        }
        switch (getToken().getRole()) {
            case User:
                if (order.getUserId().equals(getToken().getId())) {
                    baseJson.setObj(order);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            case Admin:
                baseJson.setObj(order);
                break;
            case Company:
                if (order.getCompanyId().equals(getToken().getId())) {
                    baseJson.setObj(order);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            default:
                return getUnauthorizedResult();
        }
        BaseBean baseBean = new BaseBean();
        baseBean.setSingleResult(String.valueOf(1));
        baseJson.setObj(baseBean);
        return baseJson;
    }

    @RequestMapping(value = "/updateOrderState")
    @Authentication
    public BaseJson updateOrderState(@RequestParam("orderId") Integer orderId,
                                     @RequestParam("orderStatus") Integer orderStatus) {
        BaseJson baseJson = new BaseJson();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            return getResourceNotFoundResult();
        }else {
            order.setOrderStatus(orderStatus);
        }
        switch (getToken().getRole()) {
            case User:
                if (order.getUserId().equals(getToken().getId())) {
                    orderMapper.updateOrderStatus(order);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            case Admin:
                orderMapper.updateOrderStatus(order);
                break;
            case Company:
                if (order.getCompanyId().equals(getToken().getId())) {
                    orderMapper.updateOrderStatus(order);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            default:
                return getUnauthorizedResult();
        }
        return baseJson;
    }

    @RequestMapping(value = "/getOrdersListByUserIDByPage")
    @Authentication
    public BaseJson getOrdersListByUserIDByPage(@RequestParam("userID") Integer userId,
                                                @RequestParam("pageNumber") Integer pageNumber,
                                                @RequestParam("pageSize") Integer pageSize) {
        BaseJson baseJson = new BaseJson();
        List<Order> orders = orderMapper.selectByUserId(userId, new RowBounds(pageNumber * pageSize, pageSize));
        List<OrderBean> orderBeans = orders.stream().map(this::parse).collect(Collectors.toList());
        switch (getToken().getRole()) {
            case User:
                if (userId.equals(getToken().getId())) {
                    baseJson.setObj(orderBeans);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            case Admin:
                baseJson.setObj(orderBeans);
                break;
            default:
                return getUnauthorizedResult();
        }
        return baseJson;
    }

    @RequestMapping(value = "/placeOrder")
    @Authentication(Role.User)
    public BaseJson getOrderById(@RequestParam("companyID") int companyID,
                                 @RequestParam("totalPrice") double totalPrice,
                                 @RequestParam("dishesIDList") String dishesIDList,
                                 @RequestParam("dishesCountList") String dishesCountList) {
        BaseJson baseJson = new BaseJson();
        List<Integer> dishesIDs = Lists.newArrayList(dishesIDList.split("_")).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> dishesCounts = Lists.newArrayList(dishesCountList.split("_")).stream().map(Integer::parseInt).collect(Collectors.toList());
        if (!Objects.equals(dishesIDs.size(), dishesCounts.size())) {
            return getWrongParamResult();
        }
        if (dishesIDs.size() == 0 || dishesCounts.size() == 0) {
            return getWrongParamResult();
        }
        int result = dishesMapper.checkDishesInCompany(dishesIDs);
        if (result != 1) {
            return getWrongParamResult();
        }
        if (dishesCounts.stream().mapToInt(Integer::intValue).min().getAsInt() <= 0) {
            return getWrongParamResult();
        }
        Order order = new Order();
        order.setCompanyId(companyID);
        order.setPublishTime(new Date());
        order.setUserId(getToken().getId());
        order.setTotalPrice(totalPrice);
        orderMapper.insertSelective(order);
        int orderId = order.getOrderId();
        for (int i = 0; i < dishesIDs.size(); i++) {
            /*OrderDishesKey orderDishesKey = new OrderDishesKey();
            orderDishesKey.setDishesId(dishesIDs.get(i));
            orderDishesKey.setOrderId(orderId);*/
            OrderDishes orderDishes = new OrderDishes();
            orderDishes.setCount(dishesCounts.get(i));
            orderDishes.setDishesId(dishesIDs.get(i));
            orderDishes.setOrderId(orderId);
            orderDishesMapper.insertSelective(orderDishes);
        }
        order = orderMapper.selectByPrimaryKey(order.getOrderId());
        baseJson.setObj(parse(order));
        return baseJson;
    }

    private OrderBean parse(Order order) {
        OrderBean orderBean = new OrderBean();
        try {
            BeanUtils.copyProperties(orderBean, order);
            orderBean.setCompanyName(companyInfoMapper.selectByPrimaryKey(orderBean.getCompanyId()).getCompanyName());
            orderBean.setTimeslot(orderBean.getTimeslotId() == 0 ? "" : getTimeSlotString(timeSlotMapper.selectByPrimaryKey(orderBean.getTimeslotId())));
            orderBean.setDishesBeanList(dishesMapper.selectByOrderId(orderBean.getOrderId()));
        } catch (NullPointerException np) {
            logger.warn("unexpected companyId " + orderBean.getCompanyId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderBean;
    }

    private String getTimeSlotString(TimeSlot timeSlot) {
        return DateUtils.formatDateTime(timeSlot.getStartTime()) + " - " + DateUtils.formatDateTime(timeSlot.getEndTime());
    }
}
