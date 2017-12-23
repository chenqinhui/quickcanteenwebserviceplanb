package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.CommentBean;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.CommentDishesMapper;
import com.quickcanteen.mapper.UserCommentMapper;
import com.quickcanteen.mapper.UserInfoMapper;
import com.quickcanteen.model.UserComment;
import com.quickcanteen.util.CommentParser;
import com.quickcanteen.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/getComment")
public class GetCommentController extends APIBaseController {


    @Autowired
    private UserCommentMapper userCommentMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/getCommentByDishesId")
    //@Authentication(Role.User)
    @Authentication
    public BaseJson getCommentByDishesId(@RequestParam("dishesId") Integer dishesId) {
        BaseJson baseJson = new BaseJson();

        List<UserComment> userComments = userCommentMapper.selectByDishesId(dishesId);
        List<CommentBean> userCommentList = userComments.stream().map(this::parse).collect(Collectors.toList());
        if (Objects.isNull(userCommentList)) {
            return getResourceNotFoundResult();
        }
        baseJson.setObj(userCommentList);
        baseJson.setReturnCode("");

        return baseJson;

    }

    private CommentBean parse(UserComment comment) {
        CommentBean result = new CommentBean();
        //result.setCommenterId(comment.getCommenterId());
        result.setCommenterName(userInfoMapper.selectByPrimaryKey(comment.getCommenterId()).getRealName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result.setCommentTimeStr(sdf.format(comment.getCommentTime()));
        BeanUtils.copyProperties(comment, result);
        return result;
    }

}
