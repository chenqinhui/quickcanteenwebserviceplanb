package com.quickcanteen.dto;

import com.quickcanteen.model.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by 11022 on 2017/6/30.
 */
@Data
@NoArgsConstructor
public class TypeBean {
    private Integer typeId;
    private String typeName;
    private List<DishesBean> dishesBeanList;

    public TypeBean(Type type){
        this.typeId = type.getTypeId();
        this.typeName = type.getTypeName();
    }
}
