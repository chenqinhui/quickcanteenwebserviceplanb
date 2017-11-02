package com.quickcanteen.util;

import com.quickcanteen.dto.CommentBean;
import com.quickcanteen.model.UserComment;
import org.apache.commons.beanutils.BeanUtils;

public final class CommentParser {
    private CommentParser(){

    }

    public static UserComment parse(CommentBean commentBean){
        UserComment userComment = new UserComment();
        try {
            BeanUtils.copyProperties(userComment, commentBean);
        } catch (Exception e) {

        }
        return userComment;
    }
}
