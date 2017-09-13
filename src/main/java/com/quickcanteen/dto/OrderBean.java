package com.quickcanteen.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by 11022 on 2017/8/20.
 */
@Data
public class OrderBean {
    private Integer orderId;
    private Integer userId;
    private Integer companyId;
    private String companyName;
    private Integer orderStatus;
    private Long publishTime;
    private Long completeTime;
    private Double totalPrice;
    private Integer timeslotId;
    private String timeslot;
    private List<DishesBean> dishesBeanList;

}
