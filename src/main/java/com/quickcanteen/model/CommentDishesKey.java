package com.quickcanteen.model;

public class CommentDishesKey {
    private Integer dishesId;

    private Integer commentId;

    public CommentDishesKey() {

    }

    public CommentDishesKey(int dishesId,int commentId) {
        this.dishesId = dishesId;
        this.commentId = commentId;
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}