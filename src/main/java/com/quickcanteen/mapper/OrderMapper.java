package com.quickcanteen.mapper;

import com.quickcanteen.model.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OrderMapper {
    @Delete({
            "delete from `order`",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer orderId);

    @Insert({
            "insert into `order` (order_id, user_id, ",
            "company_id, order_status, ",
            "publish_time, complete_time, ",
            "total_price, timeslot_id, ",
            "deliver_man_id, deliver_price, ",
            "location_id)",
            "values (#{orderId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
            "#{companyId,jdbcType=INTEGER}, #{orderStatus,jdbcType=INTEGER}, ",
            "#{publishTime,jdbcType=TIMESTAMP}, #{completeTime,jdbcType=TIMESTAMP}, ",
            "#{totalPrice,jdbcType=DOUBLE}, #{timeslotId,jdbcType=INTEGER}, ",
            "#{deliverManId,jdbcType=INTEGER}, #{deliverPrice,jdbcType=DOUBLE}, ",
            "#{locationId,jdbcType=INTEGER})"
    })
    int insert(Order record);

    int insertSelective(Order record);

    @Select({
            "select",
            "order_id, user_id, company_id, order_status, publish_time, complete_time, total_price, ",
            "timeslot_id, deliver_man_id, deliver_price, location_id",
            "from `order`",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    @Update({
            "update `order`",
            "set user_id = #{userId,jdbcType=INTEGER},",
            "company_id = #{companyId,jdbcType=INTEGER},",
            "order_status = #{orderStatus,jdbcType=INTEGER},",
            "publish_time = #{publishTime,jdbcType=TIMESTAMP},",
            "complete_time = #{completeTime,jdbcType=TIMESTAMP},",
            "total_price = #{totalPrice,jdbcType=DOUBLE},",
            "timeslot_id = #{timeslotId,jdbcType=INTEGER},",
            "deliver_man_id = #{deliverManId,jdbcType=INTEGER},",
            "deliver_price = #{deliverPrice,jdbcType=DOUBLE},",
            "location_id = #{locationId,jdbcType=INTEGER}",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Order record);

    @Select({
            "select * ",
            "from `order`",
            "where user_id = #{userId} order by publish_time desc"
    })
    @ResultMap("BaseResultMap")
    List<Order> selectByUserId(Integer userId, RowBounds rowBounds);

    @Update({
            "update `order`",
            "set order_status = #{orderStatus,jdbcType=INTEGER}",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    int updateOrderStatus(Order record);

    @Update({
            "update `order`",
            "set complete_time = #{completeTime,jdbcType=TIMESTAMP}",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    int updateFinishTime(Order record);

    @Update({
            "update `order`",
            "set publish_time = #{publishTime,jdbcType=TIMESTAMP}",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    int updateStartTime(Order record);

    @Update({
            "update `order`",
            "set timeslot_id = #{timeslotId,jdbcType=INTEGER}",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    int updateTimeSlot(Order record);

    @Select({
            "select * ",
            "from `order` ",
            "where company_id = 1 and ",
            "complete_time>(select subdate(curdate(),date_format(curdate(),'%w')-1)) ",
            "and order_status != 80 order by publish_time desc"
    })
    @ResultMap("BaseResultMap")
    List<Order> selectThisWeekOrderListByCompanyId(Integer companyId);


    @Select("select count(*) from `order` where order_status = #{status} and company_id = #{companyId}")
    int countByStatusAndCompanyId(@Param("status") int status, @Param("companyId") int companyId);

    @Select({
            "select * ",
            "from `order`",
            "where company_id = #{companyId} and order_status = #{status} order by publish_time desc"
    })
    @ResultMap("BaseResultMap")
    List<Order> selectByOrderStatusAndCompanyId(@Param("companyId") Integer companyId, @Param("status") Integer status, RowBounds rowBounds);

    @Select({
            "select * ",
            "from `order`",
            "where company_id = #{companyId} order by publish_time desc"
    })
    @ResultMap("BaseResultMap")
    List<Order> selectByCompanyId(@Param("companyId") Integer companyId, RowBounds rowBounds);

    @Select({
            "select * ",
            "from `order`",
            "where order_status = 110 and deliver_man_id = 0 and user_id != #{userId} order by publish_time desc"
    })
    @ResultMap("BaseResultMap")
    List<Order> selectNeedDeliver(@Param("userId") Integer userId,RowBounds rowBounds);

    @Select({
            "select * ",
            "from `order`",
            "where deliver_man_id = #{userId} order by publish_time desc"
    })
    @ResultMap("BaseResultMap")
    List<Order> selectDeliverOrders(@Param("userId") Integer userId, RowBounds rowBounds);
}