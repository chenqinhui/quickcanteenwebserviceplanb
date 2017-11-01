package com.quickcanteen.controller.api;

import com.google.common.collect.Lists;
import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.constants.OrderStatus;
import com.quickcanteen.constants.OrderStatusConstants;
import com.quickcanteen.dto.*;
import com.quickcanteen.mapper.*;
import com.quickcanteen.model.Dishes;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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

    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    @Authentication(Role.Company)
    public Map changeStatus(@RequestParam("orderId") String orderId, @RequestParam("toStatus") String toStatus) {
        Map result = new HashMap();
        Map<OrderStatus,List<OrderStatus>> map = new HashMap<>();
        int edit_result = 0;
        Order order = orderMapper.selectByPrimaryKey(Integer.parseInt(orderId));
        if(order.getTimeslotId()==0)
        {
            map = OrderStatusConstants.getDistributingStatusMap();
        }
        else
        {
            map = OrderStatusConstants.getTakingStatusMap();
        }
        if(map.get(OrderStatus.valueOf(order.getOrderStatus())).contains(OrderStatus.valueOf(Integer.parseInt(toStatus)))) {
            order.setOrderStatus(Integer.parseInt(toStatus));
            orderMapper.updateOrderStatus(order);
            edit_result = 1;
        }
        result.put("returnCode", String.valueOf(edit_result));
        return result;
    }

    @RequestMapping(value = "/updateOrderState")
    @Authentication
    public BaseJson updateOrderState(@RequestParam("orderId") Integer orderId,
                                     @RequestParam("orderStatus") Integer orderStatus) {
        BaseJson baseJson = new BaseJson();
        BaseBean baseBean = new BaseBean();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Map<OrderStatus,List<OrderStatus>> orderStatusListMap = OrderStatusConstants.getUserStatusMap();
        if (order == null) {
            return getResourceNotFoundResult();
        }
        else if(!orderStatusListMap.get(orderId).contains(orderStatus))
        {
            getWrongParamResult();
        }
        else {
            order.setOrderStatus(orderStatus);
        }
        switch (getToken().getRole()) {
            case User:
                if (order.getUserId().equals(getToken().getId())) {
                    orderMapper.updateOrderStatus(order);
                    baseBean.setSingleResult("0");
                    baseJson.setObj(baseBean);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            case Admin:
                orderMapper.updateOrderStatus(order);
                baseBean.setSingleResult("0");
                baseJson.setObj(baseBean);
                break;
            case Company:
                if (order.getCompanyId().equals(getToken().getId())) {
                    orderMapper.updateOrderStatus(order);
                    baseBean.setSingleResult("0");
                    baseJson.setObj(baseBean);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            default:
                return getUnauthorizedResult();
        }
        return baseJson;
    }

    @RequestMapping(value = "/updateTimeSlot")
    @Authentication
    public BaseJson updateTimeSlot(@RequestParam("orderId") Integer orderId,
                                   @RequestParam("timeSlot") Integer orderTimeSlot) {
        BaseJson baseJson = new BaseJson();
        BaseBean baseBean = new BaseBean();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            return getResourceNotFoundResult();
        } else {
            order.setTimeslotId(orderTimeSlot);
        }
        switch (getToken().getRole()) {
            case User:
                if (order.getUserId().equals(getToken().getId())) {
                    orderMapper.updateTimeSlot(order);
                    baseBean.setSingleResult("0");
                    baseJson.setObj(baseBean);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            /*case Admin:
                orderMapper.updateOrderStatus(order);
                baseBean.setSingleResult("0");
                baseJson.setObj(baseBean);
                break;
            case Company:
                if (order.getCompanyId().equals(getToken().getId())) {
                    orderMapper.updateOrderStatus(order);
                    baseBean.setSingleResult("0");
                    baseJson.setObj(baseBean);
                } else {
                    return getUnauthorizedResult();
                }
                break;*/
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
            List<DishesBean> dishesBeanList = dishesMapper.selectByOrderId(orderBean.getOrderId());
            orderBean.setDishesBeanList(dishesBeanList.size() == 0 ? null : dishesBeanList);
        } catch (NullPointerException np) {
            logger.warn("unexpected companyId " + orderBean.getCompanyId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderBean;
    }

    @RequestMapping(value = "/getTimeSlotByOrdersID")
    @Authentication
    public BaseJson getTimeSlotByOrdersID(@RequestParam("ordersID") Integer ordersID) {
        String timeSlotString;
        TimeSlot timeSlot;
        BaseJson baseJson = new BaseJson();
        BaseBean baseBean = new BaseBean();
        Order order = orderMapper.selectByPrimaryKey(ordersID);
        timeSlot = timeSlotMapper.selectByPrimaryKey(order.getTimeslotId());
        if (timeSlot == null) {
            timeSlotString = null;
        } else {
            timeSlotString = getTimeSlotString(timeSlot);
        }
        switch (getToken().getRole()) {
            case User:
                if (order.getUserId().equals(getToken().getId())) {
                    baseBean.setSingleResult(timeSlotString);
                    baseJson.setObj(baseBean);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            case Admin:
                baseBean.setSingleResult(timeSlotString);
                baseJson.setObj(baseBean);
                break;
            default:
                return getUnauthorizedResult();
        }
        return baseJson;
    }

    @RequestMapping(value = "/updateFinishTime")
    @Authentication
    public BaseJson updateFinishTime(@RequestParam("orderId") Integer ordersID) {
        BaseJson baseJson = new BaseJson();
        Order order = orderMapper.selectByPrimaryKey(ordersID);
        order.setCompleteTime(new Date());
        switch (getToken().getRole()) {
            case User:
                if (order.getUserId().equals(getToken().getId())) {
                    orderMapper.updateFinishTime(order);
                    baseJson.setObj(order);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            /*case Admin:
                orderMapper.updateFinishTime(order);
                baseBean.setSingleResult("");
                baseJson.setObj(baseBean);
                break;
            case Company:
                if (order.getCompanyId().equals(getToken().getId())) {
                    orderMapper.updateFinishTime(order);
                    baseBean.setSingleResult("");
                    baseJson.setObj(baseBean);
                } else {
                    return getUnauthorizedResult();
                }
                break;*/
            default:
                return getUnauthorizedResult();
        }
        return baseJson;
    }

    @RequestMapping(value = "/updateStartTime")
    @Authentication
    public BaseJson updateStartTime(@RequestParam("orderId") Integer ordersID) {
        BaseJson baseJson = new BaseJson();
        Order order = orderMapper.selectByPrimaryKey(ordersID);
        order.setPublishTime(new Date());
        switch (getToken().getRole()) {
            case User:
                if (order.getUserId().equals(getToken().getId())) {
                    orderMapper.updateStartTime(order);
                    baseJson.setObj(order);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            /*case Admin:
                orderMapper.updateStartTime(order);
                baseBean.setSingleResult("");
                baseJson.setObj(baseBean);
                break;
            case Company:
                if (order.getCompanyId().equals(getToken().getId())) {
                    orderMapper.updateStartTime(order);
                    baseBean.setSingleResult("");
                    baseJson.setObj(baseBean);
                } else {
                    return getUnauthorizedResult();
                }
                break;*/
            default:
                return getUnauthorizedResult();
        }
        return baseJson;
    }

    private String getTimeSlotString(TimeSlot timeSlot) {
        String origin = DateUtils.formatDateTime(timeSlot.getStartTime()) + " - " + DateUtils.formatDateTime(timeSlot.getEndTime());
        String a[] = origin.split(" ");
        String result = a[1] + a[2] + a[4];
        return result;
    }
}
