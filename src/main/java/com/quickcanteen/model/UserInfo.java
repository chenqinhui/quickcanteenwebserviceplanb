package com.quickcanteen.model;

import java.util.Date;

public class UserInfo {
    private Integer userId;

    private String accountNumber;

    private String userPassword;

    private String headPortrait;

    private String nickName;

    private String realName;

    private String telephone;

    private Date entranceYear;

    private String collegeName;

    private String universityName;

    private Integer isAdmin;

    private Integer points;

    public UserInfo() {

    }

    public UserInfo(String accountNumber, String userPassword, String telephone, String realName,Date entranceYear) {
        this.accountNumber = accountNumber;
        this.userPassword = userPassword;
        this.telephone = telephone;
        this.realName = realName;
        this.entranceYear = entranceYear;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait == null ? null : headPortrait.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Date entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName == null ? null : universityName.trim();
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}