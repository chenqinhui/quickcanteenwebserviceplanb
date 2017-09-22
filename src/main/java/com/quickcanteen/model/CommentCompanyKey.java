package com.quickcanteen.model;

public class CommentCompanyKey {
    private Integer companyId;

    private Integer commentId;

    public CommentCompanyKey() {

    }

    public CommentCompanyKey(int companyId,int commentId) {
        this.companyId = companyId;
        this.commentId = commentId;
    }


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}