package com.quickcanteen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDishes extends OrderDishesKey {
    private Integer count;
}