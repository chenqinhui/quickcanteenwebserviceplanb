package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.LocationBean;
import com.quickcanteen.mapper.LocationMapper;
import com.quickcanteen.model.Location;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/location")
public class LocationController extends APIBaseController {
    @Autowired
    private LocationMapper locationMapper;

    @RequestMapping(value = "/getLocationsByUserID")
    @Authentication
    public BaseJson getLocationsByUserID(@RequestParam("userID") Integer userId) {
        BaseJson baseJson = new BaseJson();
        List<LocationBean> locationBeans = locationMapper.selectByUserID(userId).stream().map(this::parse).collect(Collectors.toList());
        switch (getToken().getRole()) {
            case User:
                if (userId.equals(getToken().getId())) {
                    baseJson.setObj(locationBeans);
                } else {
                    return getUnauthorizedResult();
                }
                break;
            case Admin:
                baseJson.setObj(locationBeans);
                break;
            default:
                return getUnauthorizedResult();
        }
        return baseJson;
    }

    private LocationBean parse(Location location) {
        LocationBean locationBean = new LocationBean();
        BeanUtils.copyProperties(location, locationBean);
        return locationBean;
    }
}
