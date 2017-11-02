package com.quickcanteen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Dishes {
    private Integer dishesId;

    private Integer companyId;

    private Double price;

    private String diagrammaticSketchAddress;

    private String dishesName;

    private Integer praiseNum;

    private Integer commentNum;

    private Integer collectNum;

    private Date publishTime;

    private Double rating;

    private String dishesIntroduce;

    private Integer available;

    public Dishes(String name,Double price,String introduction,Integer dishesId,Integer companyId){
        this.dishesId = dishesId;
        this.companyId = companyId;
        this.dishesName = name;
        this.price = price;
        this.dishesIntroduce = introduction;
        this.available = 1;
    }

    public void setDishesIntroduce(String dishesIntroduce) {
        this.dishesIntroduce = dishesIntroduce == null ? null : dishesIntroduce.trim();
    }
}