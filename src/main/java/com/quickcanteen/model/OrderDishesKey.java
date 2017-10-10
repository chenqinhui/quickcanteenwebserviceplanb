package com.quickcanteen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDishesKey {
    private Integer orderId;

    private Integer dishesId;

    public OrderDishesKey(Integer orderId,Integer dishesId){
        this.dishesId=dishesId;
        this.orderId=orderId;
    }
}