package com.quickcanteen.mapper;

import com.quickcanteen.model.Location;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface LocationMapper {
    @Delete({
            "delete from location",
            "where location_id = #{locationId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer locationId);

    @Insert({
            "insert into location (location_id, user_id, ",
            "longitude, latitude, ",
            "address)",
            "values (#{locationId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
            "#{longitude,jdbcType=DOUBLE}, #{latitude,jdbcType=DOUBLE}, ",
            "#{address,jdbcType=LONGVARCHAR})"
    })
    int insert(Location record);

    int insertSelective(Location record);

    @Select({
            "select",
            "location_id, user_id, longitude, latitude, address",
            "from location",
            "where location_id = #{locationId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    Location selectByPrimaryKey(Integer locationId);

    int updateByPrimaryKeySelective(Location record);

    @Update({
            "update location",
            "set user_id = #{userId,jdbcType=INTEGER},",
            "longitude = #{longitude,jdbcType=DOUBLE},",
            "latitude = #{latitude,jdbcType=DOUBLE},",
            "address = #{address,jdbcType=LONGVARCHAR}",
            "where location_id = #{locationId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Location record);

    @Update({
            "update location",
            "set user_id = #{userId,jdbcType=INTEGER},",
            "longitude = #{longitude,jdbcType=DOUBLE},",
            "latitude = #{latitude,jdbcType=DOUBLE}",
            "where location_id = #{locationId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Location record);

    @Select({
            "select *",
            "from location",
            "where user_id = #{userID,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<Location> selectByUserID(Integer userID);
}