package com.quickcanteen.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class DishesVo {
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
    private Integer count;
}

