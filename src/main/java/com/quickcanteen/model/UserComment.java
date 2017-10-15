package com.quickcanteen.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserComment {
    private Integer commentId;

    private Integer commenterId;

    private Double rating;

    private String commentContent;

    private Date commentTime;

    public UserComment() {

    }

    public UserComment(Integer commenterId,Double rating,String commentContent,Date commentTime){
        this.commenterId = commenterId;
        this.rating = rating;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
    }

}