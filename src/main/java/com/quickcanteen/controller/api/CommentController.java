package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseBean;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.Role;
import com.quickcanteen.mapper.*;
import com.quickcanteen.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
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

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private DishesMapper dishesMapper;

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
        DecimalFormat df = new DecimalFormat("#.00");
        if (isCompany == 1) {
            CommentCompanyKey commentCompanyKey = new CommentCompanyKey(objectId, commentId);
            commentCompanyMapper.insert(commentCompanyKey);
            double companyRating = userCommentMapper.getRatingByCompanyId(objectId);
            CompanyInfo newCompany = companyInfoMapper.selectByPrimaryKey(objectId);
            newCompany.setRating(Double.parseDouble(df.format(companyRating)));
            companyInfoMapper.updateByPrimaryKey(newCompany);
        } else {
            CommentDishesKey commentDishesKey = new CommentDishesKey(objectId, commentId);
            commentDishesMapper.insert(commentDishesKey);
            double dishesRating = userCommentMapper.getRatingByDishesId(objectId);
            Dishes newDishes = dishesMapper.selectByPrimaryKey(objectId);
            newDishes.setRating(Double.parseDouble(df.format(dishesRating)));
            dishesMapper.updateByPrimaryKey(newDishes);
        }
        baseJson.setReturnCode("9.0");
        baseJson.setErrorMessage("成功");
        BaseBean baseBean = new BaseBean();
        baseBean.setSingleResult(String.valueOf(commentId));
        baseJson.setObj(baseBean);

        return baseJson;
    }
}
