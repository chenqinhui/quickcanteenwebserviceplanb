package com.quickcanteen.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@Data
@NoArgsConstructor
public class UserInfoBean implements java.io.Serializable {

    // Fields

    private Integer userId;
    private String accountNumber;
    private String headPortrait;
    private String nickName;
    private String realName;
    private String telephone;
    private Long entranceYear;
    private String collegeName;
    private String universityName;
    private Integer isAdmin;
    private Integer points;
    private Boolean deliver;


}