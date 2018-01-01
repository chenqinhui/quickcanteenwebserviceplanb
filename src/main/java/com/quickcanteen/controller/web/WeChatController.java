package com.quickcanteen.controller.web;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.*;
import com.quickcanteen.model.*;
import com.quickcanteen.vo.CommentVo;
import com.quickcanteen.vo.DishesVo;
import com.quickcanteen.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/weChat")
public class WeChatController extends BaseController{
    private static final String MODULE_V_INDEX = "wechat/index";
    private static final String MODULE_V_LOGIN = "wechat/login";
    private static final String MODULE_V_DISHES_DETAIL = "wechat/dishes_details";
    private static final String MODULE_V_COMMENT = "wechat/evaluate";
    private static final String MODULE_V_ORDER_SETTLEMENT = "wechat/order_settlement";
    private static final String MODULE_V_SUCCESS = "wechat/success";
    private static final String MODULE_V_UNSUBSCRIBE = "wechat/unsubscribe";

    @Autowired
    private DishesMapper dishesMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private OrderDishesMapper orderDishesMapper;

    @Autowired
    private UserCommentMapper userCommentMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private TimeSlotMapper timeSlotMapper;

    @RequestMapping(value = "/index")
    @Authentication(Role.User)
    public String index(Map<String, Object> model) {
        model.put("module", MODULE_V_INDEX);
        return MODULE_V_INDEX;
    }

    @RequestMapping(value = "/login")
    public String login(Map<String, Object> model) {
        model.put("module", MODULE_V_LOGIN);
        return MODULE_V_LOGIN;
    }



    @RequestMapping(value = "/dishes_detail")
    @Authentication(Role.User)
    public String dishesDetail(Map<String, Object> model) {
        model.put("module", MODULE_V_DISHES_DETAIL);
        return MODULE_V_DISHES_DETAIL;
    }

    @RequestMapping(value = "/comment")
    @Authentication(Role.User)
    public String comment(Map<String, Object> model) {
        model.put("module", MODULE_V_COMMENT);
        return MODULE_V_COMMENT;
    }

    @RequestMapping(value = "/order_settlement/{orderId}")
    @Authentication(Role.User)
    public String orderSettlement(Map<String, Object> model,@PathVariable Integer orderId) {
        OrderVo order = parse(orderMapper.selectByPrimaryKey(orderId));
        List<Location> address = locationMapper.selectByUserID(getCurrentCompanyId());//之后改成LocationVo类型
        String defaultAdd = address.get(0).getAddress();
        model.put("module", MODULE_V_ORDER_SETTLEMENT);
        model.put("order",order);
        model.put("defaultAddress",defaultAdd);
        return MODULE_V_ORDER_SETTLEMENT;
    }

    @RequestMapping(value = "/success/{orderId}")
    @Authentication(Role.User)
    public String success(Map<String, Object> model,@PathVariable Integer orderId) {
        OrderVo order = parse(orderMapper.selectByPrimaryKey(orderId));
        model.put("order",order);
        model.put("module", MODULE_V_SUCCESS);
        return MODULE_V_SUCCESS;
    }

    @RequestMapping(value = "/unsubscribe/{orderId}")
    @Authentication(Role.User)
    public String unSubscribe(Map<String, Object> model,@PathVariable Integer orderId) {
        OrderVo order = parse(orderMapper.selectByPrimaryKey(orderId));
        model.put("order",order);
        model.put("module", MODULE_V_UNSUBSCRIBE);
        return MODULE_V_UNSUBSCRIBE;
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
        if(order.getTimeslotId()!=0) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date startTime = timeSlotMapper.selectByPrimaryKey(order.getTimeslotId()).getStartTime();
            Date endTime = timeSlotMapper.selectByPrimaryKey(order.getTimeslotId()).getEndTime();
            String timeSlot = sdf.format(startTime) + "~" + sdf.format(endTime);
            result.setMealTime(timeSlot);
        }
        else
            result.setMealTime("deliverTime");
        result.setDishesVos(dishesVos);
        result.setDeliverManId(order.getDeliverManId());
        return result;
    }

    private DishesVo parse(Dishes dishes) {
        DishesVo result = new DishesVo();
        BeanUtils.copyProperties(dishes,result);
        result.setCount(0);
        result.setCommentVos(userCommentMapper.getUserCommentsByDishId(dishes.getDishesId()).stream().map(this::parse).collect(Collectors.toList()));
        return result;
    }

    private CommentVo parse(UserComment comment) {
        CommentVo result = new CommentVo();
        BeanUtils.copyProperties(comment, result);
        result.setCommenterName(userInfoMapper.selectByPrimaryKey(comment.getCommenterId()).getRealName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.setCommentTimeStr(sdf.format(comment.getCommentTime()));
        return result;
    }

}
