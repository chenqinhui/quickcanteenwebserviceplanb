package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseBean;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.CommentCompanyMapper;
import com.quickcanteen.mapper.CommentDishesMapper;
import com.quickcanteen.mapper.UserCommentMapper;
import com.quickcanteen.model.CommentCompanyKey;
import com.quickcanteen.model.CommentDishesKey;
import com.quickcanteen.model.UserComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/comment")
public class CommentController extends APIBaseController {
    @Autowired
    private UserCommentMapper userCommentMapper;

    @Autowired
    private CommentDishesMapper commentDishesMapper;

    @Autowired
    private CommentCompanyMapper commentCompanyMapper;

    @RequestMapping(value = "/postComment")
    @Authentication(Role.User)
    public BaseJson postComment(@RequestParam("objectId") int objectId,
                                @RequestParam("userId") int userId,
                                @RequestParam("commentContent") String commentContent,
                                @RequestParam("rating") double rating,
                                @RequestParam("isCompany") int isCompany) {
        BaseJson baseJson = new BaseJson();
        Date commentTime = new Date();
        UserComment userComment = new UserComment(userId, rating, commentContent, commentTime);
        userCommentMapper.insertSelective(userComment);
        int commentId = userComment.getCommentId();
        if (isCompany == 1) {
            CommentCompanyKey commentCompanyKey = new CommentCompanyKey(objectId, commentId);
            commentCompanyMapper.insert(commentCompanyKey);
        } else {
            CommentDishesKey commentDishesKey = new CommentDishesKey(objectId, commentId);
            commentDishesMapper.insert(commentDishesKey);
        }
        baseJson.setReturnCode("9.0");
        baseJson.setErrorMessage("成功");
        BaseBean baseBean = new BaseBean();
        baseBean.setSingleResult(String.valueOf(commentId));
        baseJson.setObj(baseBean);

        return baseJson;
    }
}
