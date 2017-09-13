package com.quickcanteen.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer orderId;

    private Integer userId;

    private Integer companyId;

    private Integer orderStatus;

    private Date publishTime;

    private Date completeTime;

    private Double totalPrice;

    private Integer timeslotId;

}