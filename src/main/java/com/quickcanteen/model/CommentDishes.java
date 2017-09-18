package com.quickcanteen.model;

import java.util.Date;

public class CommentDishes extends CommentDishesKey {
    private Date updateTime;

    public CommentDishes() {

    }

    public CommentDishes(int dishesId,int commentId,Date updateTime) {
        super(dishesId,commentId);
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}