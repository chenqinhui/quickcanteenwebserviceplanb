package com.quickcanteen.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by 11022 on 2017/6/30.
 */
@Data
public class TypeBean {
    private Integer typeId;
    private String typeName;
    private List<DishesBean> dishesBeanList;
}
