package com.quickcanteen.dto;

import com.quickcanteen.model.Dishes;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by 11022 on 2017/8/20.
 */
@Data
public class DishesBean {
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
    private Integer available;
    private String companyName;

    public DishesBean() {
        this.count = 0;
    }

    public DishesBean(Dishes dishes) {
        this.dishesId = dishes.getDishesId();
        this.companyId = dishes.getCompanyId();
        this.price = dishes.getPrice();
        this.diagrammaticSketchAddress = dishes.getDiagrammaticSketchAddress();
        this.dishesName = dishes.getDishesName();
        this.praiseNum = dishes.getPraiseNum();
        this.commentNum = dishes.getCommentNum();
        this.collectNum = dishes.getCollectNum();
        this.publishTime = dishes.getPublishTime();
        this.rating = dishes.getRating();
        this.dishesIntroduce = dishes.getDishesIntroduce();
        this.count = 0;
        this.available = dishes.getAvailable();
        this.companyName = null;
    }
}
