package com.quickcanteen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class CompanyInfo {
    private Integer companyId;

    private String companyName;

    private String accountNumber;

    private String password;

    private Date startTime;

    private Date endTime;

    private Integer busyDegree;

    private Double rating;

    private String companyPortrait;
}