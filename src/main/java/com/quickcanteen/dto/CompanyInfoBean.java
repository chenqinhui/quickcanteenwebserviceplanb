package com.quickcanteen.dto;

import com.quickcanteen.model.UserComment;

import java.util.Date;
import java.util.List;

/**
 * Created by Cynthia on 2017/8/19.
 */
public class CompanyInfoBean {
    private Integer companyId;

    private String companyName;

    private String accountNumber;

    private Date startTime;

    private Date endTime;

    private Integer busyDegree;

    private Double rating;

    public CompanyInfoBean(){

    }

    public CompanyInfoBean(Integer companyId,String companyName,String accountNumber,Double rating,Integer busyDegree,Date startTime,Date endTime){
        this.companyId = companyId;
        this.companyName = companyName;
        this.accountNumber = accountNumber;
        this.rating = rating;
        this.busyDegree = busyDegree;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getBusyDegree() {
        return busyDegree;
    }

    public void setBusyDegree(Integer busyDegree) {
        this.busyDegree = busyDegree;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
