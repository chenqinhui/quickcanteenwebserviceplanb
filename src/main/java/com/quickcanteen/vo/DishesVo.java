package com.quickcanteen.vo;

import com.quickcanteen.model.UserComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private List<CommentVo>commentVos;
    private Integer available;
}

