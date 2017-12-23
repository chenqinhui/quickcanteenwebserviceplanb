package com.quickcanteen.mapper;

import com.quickcanteen.model.TimeSlot;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TimeSlotMapper {
    @Delete({
        "delete from time_slot",
        "where time_slot_id = #{timeSlotId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer timeSlotId);

    @Insert({
        "insert into time_slot (time_slot_id, start_time, ",
        "end_time)",
        "values (#{timeSlotId,jdbcType=INTEGER}, #{startTime,jdbcType=TIME}, ",
        "#{endTime,jdbcType=TIME})"
    })
    int insert(TimeSlot record);

    int insertSelective(TimeSlot record);

    @Select({
        "select",
        "time_slot_id, start_time, end_time",
        "from time_slot",
        "where time_slot_id = #{timeSlotId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    TimeSlot selectByPrimaryKey(Integer timeSlotId);

    int updateByPrimaryKeySelective(TimeSlot record);

    @Update({
        "update time_slot",
        "set start_time = #{startTime,jdbcType=TIME},",
          "end_time = #{endTime,jdbcType=TIME}",
        "where time_slot_id = #{timeSlotId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TimeSlot record);

    @Select({
            "select * ",
            "from time_slot"
    })
    @ResultMap("BaseResultMap")
    List<TimeSlot> selectAll();
}