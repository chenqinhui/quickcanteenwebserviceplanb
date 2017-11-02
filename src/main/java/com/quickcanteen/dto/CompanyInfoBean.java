package com.quickcanteen.dto;

import com.quickcanteen.model.UserComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by Cynthia on 2017/8/19.
 */
@Data
@NoArgsConstructor
public class CompanyInfoBean {
    private Integer companyId;

    private String companyName;

    private String accountNumber;

    private Date startTime;

    private Date endTime;

    private Integer busyDegree;

    private Double rating;

    private String companyPortrait;

    public CompanyInfoBean(Integer companyId,String companyName,String accountNumber,Double rating,Integer busyDegree,Date startTime,Date endTime,String companyPortrait){
        this.companyId = companyId;
        this.companyName = companyName;
        this.accountNumber = accountNumber;
        this.rating = rating;
        this.busyDegree = busyDegree;
        this.startTime = startTime;
        this.endTime = endTime;
        this.companyPortrait = companyPortrait;
    }
}
