package com.quickcanteen.model;

import java.util.Date;

public class CommentCompany extends CommentCompanyKey {
    private Date updateTime;

    public CommentCompany() {

    }

    public CommentCompany(int companyId,int commentId,Date updateTime) {
        super(companyId,commentId);
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}