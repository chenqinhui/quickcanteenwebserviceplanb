package com.quickcanteen.dto;

public class CommentBean {
    private Integer commentId;

    private Double rating;

    private String commentContent;

    private String commenterName;

    private String commentTimeStr;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommentTimeStr() {
        return commentTimeStr;
    }

    public void setCommentTimeStr(String commentTimeStr) {
        this.commentTimeStr = commentTimeStr;
    }
}


