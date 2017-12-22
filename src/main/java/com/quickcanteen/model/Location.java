package com.quickcanteen.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Location {
    private Integer locationId;

    private Integer userId;

    private Double longitude;

    private Double latitude;

    private String address;

}