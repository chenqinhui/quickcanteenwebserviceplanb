package com.quickcanteen.mapper;

import com.quickcanteen.model.OrderDishes;
import com.quickcanteen.model.OrderDishesKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderDishesMapper {
    @Delete({
        "delete from order_dishes",
        "where order_id = #{orderId,jdbcType=INTEGER}",
          "and dishes_id = #{dishesId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(OrderDishesKey key);

    @Insert({
        "insert into order_dishes (order_id, dishes_id, ",
        "count)",
        "values (#{orderId,jdbcType=INTEGER}, #{dishesId,jdbcType=INTEGER}, ",
        "#{count,jdbcType=INTEGER})"
    })
    int insert(OrderDishes record);

    int insertSelective(OrderDishes record);

    @Select({
        "select",
        "order_id, dishes_id, count",
        "from order_dishes",
        "where order_id = #{orderId,jdbcType=INTEGER}",
          "and dishes_id = #{dishesId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    OrderDishes selectByPrimaryKey(OrderDishesKey key);

    int updateByPrimaryKeySelective(OrderDishes record);

    @Update({
        "update order_dishes",
        "set count = #{count,jdbcType=INTEGER}",
        "where order_id = #{orderId,jdbcType=INTEGER}",
          "and dishes_id = #{dishesId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrderDishes record);
}