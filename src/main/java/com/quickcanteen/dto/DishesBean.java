package com.quickcanteen.dto;

import com.quickcanteen.model.Dishes;

import java.util.Date;

/**
 * Created by 11022 on 2017/8/20.
 */
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
    }

    public Integer getDishesId() {
        return dishesId;
    }

    public void setDishesId(Integer dishesId) {
        this.dishesId = dishesId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiagrammaticSketchAddress() {
        return diagrammaticSketchAddress;
    }

    public void setDiagrammaticSketchAddress(String diagrammaticSketchAddress) {
        this.diagrammaticSketchAddress = diagrammaticSketchAddress;
    }

    public String getDishesName() {
        return dishesName;
    }

    public void setDishesName(String dishesName) {
        this.dishesName = dishesName;
    }

    public String getDishesIntroduce() {
        return dishesIntroduce;
    }

    public void setDishesIntroduce(String dishesIntroduce) {
        this.dishesIntroduce = dishesIntroduce;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
