package com.quickcanteen.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LocationBean {
    private Integer locationId;

    private Integer userId;

    private Double longitude;

    private Double latitude;

    private String address;

    public LocationBean() {
        locationId = 0;
        userId = 0;
        latitude = 0.0;
        longitude = 0.0;
        address = "";
    }

}