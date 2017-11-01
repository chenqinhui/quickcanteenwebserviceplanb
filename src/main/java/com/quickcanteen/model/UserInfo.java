package com.quickcanteen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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

    private Boolean deliver;

    public UserInfo(String accountNumber, String userPassword, String telephone, String realName, Date entranceYear) {
        this.accountNumber = accountNumber;
        this.userPassword = userPassword;
        this.telephone = telephone;
        this.realName = realName;
        this.entranceYear = entranceYear;
    }
}