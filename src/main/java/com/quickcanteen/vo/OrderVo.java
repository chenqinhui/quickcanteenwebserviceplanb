package com.quickcanteen.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OrderVo {
    private Integer orderId;

    private Integer userId;

    private Integer companyId;

    private Integer orderStatus;

    private Date publishTime;

    private Date completeTime;

    private Double totalPrice;

    private Integer timeslotId;

    private String userName;

}
