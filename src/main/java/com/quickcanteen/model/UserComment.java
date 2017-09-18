package com.quickcanteen.model;

import lombok.Data;

@Data
public class UserComment {
    private Integer commentId;

    private Integer commenterId;

    private Double rating;

    private String commentContent;

    public UserComment() {

    }

    public UserComment(Integer commenterId,Double rating,String commentContent){
        this.commenterId = commenterId;
        this.rating = rating;
        this.commentContent = commentContent;
    }
/*
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Integer commenterId) {
        this.commenterId = commenterId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }*/
}