package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.mapper.TimeSlotMapper;
import com.quickcanteen.model.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/timeSlot")
public class TimeSlotController {
    @Autowired
    private TimeSlotMapper timeSlotMapper;
    @RequestMapping(value = "/getAllTimeSlots")
    @Authentication
    public BaseJson getAllTimeSlots() {
        BaseJson baseJson = new BaseJson();
        List<TimeSlot> timeSlotList= timeSlotMapper.selectAll();
        if(timeSlotList.size() != 0) {
            baseJson.setObj(timeSlotList);
            baseJson.setErrorMessage("成功");
        }
        else{
            baseJson.setObj(null);
            baseJson.setErrorMessage("时间段获取失败");
        }
        return baseJson;
    }
}
