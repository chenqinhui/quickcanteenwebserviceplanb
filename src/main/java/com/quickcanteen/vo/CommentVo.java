package com.quickcanteen.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentVo {
    private Integer commentId;

    private Integer commenterId;

    private Double rating;

    private String commentContent;

    private Date commentTime;

    private String commenterName;

    private String commentTimeStr;
}
