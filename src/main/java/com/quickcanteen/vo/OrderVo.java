package com.quickcanteen.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
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

    private List<DishesVo> dishesVos;

}
