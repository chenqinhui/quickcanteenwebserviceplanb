package com.quickcanteen.controller.web;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.DishesMapper;
import com.quickcanteen.mapper.TypeMapper;
import com.quickcanteen.mapper.UserCommentMapper;
import com.quickcanteen.mapper.UserInfoMapper;
import com.quickcanteen.model.Dishes;
import com.quickcanteen.model.UserComment;
import com.quickcanteen.vo.CommentVo;
import com.quickcanteen.vo.DishesVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
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
    private UserCommentMapper userCommentMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private TypeMapper typeMapper;

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
    @Authentication(Role.Company)
    public String dishesDetail(Map<String, Object> model) {
        DishesVo dishes = new DishesVo();
        dishes = parse (dishesMapper.selectByPrimaryKey(2));

        model.put("dishes", dishes);
        model.put("module", MODULE_V_DISHES_DETAIL);
        return MODULE_V_DISHES_DETAIL;
    }

    @RequestMapping(value = "/comment")
    @Authentication(Role.Company)
    public String comment(Map<String, Object> model) {
        model.put("module", MODULE_V_COMMENT);
        return MODULE_V_COMMENT;
    }

    @RequestMapping(value = "/order_settlement")
    @Authentication(Role.Company)
    public String orderSettlement(Map<String, Object> model) {
        model.put("module", MODULE_V_ORDER_SETTLEMENT);
        return MODULE_V_ORDER_SETTLEMENT;
    }

    @RequestMapping(value = "/success")
    @Authentication(Role.Company)
    public String success(Map<String, Object> model) {
        model.put("module", MODULE_V_SUCCESS);
        return MODULE_V_SUCCESS;
    }

    @RequestMapping(value = "/unsubscribe")
    @Authentication(Role.Company)
    public String unSubscribe(Map<String, Object> model) {
        model.put("module", MODULE_V_UNSUBSCRIBE);
        return MODULE_V_UNSUBSCRIBE;
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
