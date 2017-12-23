package com.quickcanteen.mapper;

import com.quickcanteen.dto.DishesBean;
import com.quickcanteen.model.Dishes;
import com.quickcanteen.vo.DishesVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DishesMapper {
    @Delete({
        "delete from dishes",
        "where dishes_id = #{dishesId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer dishesId);

    @Insert({
        "insert into dishes (dishes_id, company_id, ",
        "price, diagrammatic_sketch_address, ",
        "dishes_name, praise_num, ",
        "comment_num, collect_num, ",
        "publish_time, rating, ",
        "dishes_introduce, available)",
        "values (#{dishesId,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
        "#{price,jdbcType=DOUBLE}, #{diagrammaticSketchAddress,jdbcType=VARCHAR}, ",
        "#{dishesName,jdbcType=VARCHAR}, #{praiseNum,jdbcType=INTEGER}, ",
        "#{commentNum,jdbcType=INTEGER}, #{collectNum,jdbcType=INTEGER}, ",
        "#{publishTime,jdbcType=TIMESTAMP}, #{rating,jdbcType=DOUBLE}, ",
        "#{dishesIntroduce,jdbcType=LONGVARCHAR},#{available,jdbcType=INTEGER})"
    })
    int insert(Dishes record);

    int insertSelective(Dishes record);

    @Select({
        "select",
        "dishes_id, company_id, price, diagrammatic_sketch_address, dishes_name, praise_num, ",
        "comment_num, collect_num, publish_time, rating, dishes_introduce, available",
        "from dishes",
        "where dishes_id = #{dishesId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    Dishes selectByPrimaryKey(Integer dishesId);

    int  updateByPrimaryKeySelective(Dishes record);

    @Update({
        "update dishes",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DOUBLE},",
          "diagrammatic_sketch_address = #{diagrammaticSketchAddress,jdbcType=VARCHAR},",
          "dishes_name = #{dishesName,jdbcType=VARCHAR},",
          "praise_num = #{praiseNum,jdbcType=INTEGER},",
          "comment_num = #{commentNum,jdbcType=INTEGER},",
          "collect_num = #{collectNum,jdbcType=INTEGER},",
          "publish_time = #{publishTime,jdbcType=TIMESTAMP},",
          "rating = #{rating,jdbcType=DOUBLE},",
          "dishes_introduce = #{dishesIntroduce,jdbcType=LONGVARCHAR},",
          "available = #{available,jdbcType=INTEGER}",
        "where dishes_id = #{dishesId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Dishes record);

    @Update({
        "update dishes",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DOUBLE},",
          "diagrammatic_sketch_address = #{diagrammaticSketchAddress,jdbcType=VARCHAR},",
          "dishes_name = #{dishesName,jdbcType=VARCHAR},",
          "praise_num = #{praiseNum,jdbcType=INTEGER},",
          "comment_num = #{commentNum,jdbcType=INTEGER},",
          "collect_num = #{collectNum,jdbcType=INTEGER},",
          "publish_time = #{publishTime,jdbcType=TIMESTAMP},",
            "rating = #{rating,jdbcType=DOUBLE},",
          "available = #{available,jdbcType=INTEGER}",
        "where dishes_id = #{dishesId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Dishes record);

    @Select({
            "select *",
            "from dishes natural join order_dishes",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapForDishesBean")
    List<DishesBean> selectByOrderId(Integer orderId);

    @Select({
            "select dishes.*",
            "from dishes natural join order_dishes",
            "where order_id = #{orderId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<Dishes> selectDishesByOrderId(Integer orderId);

    @Select({
            "select *,'0' as count ",
            "from dishes natural join dishes_type",
            "where type_id = #{typeId,jdbcType=INTEGER} and available = 1"
    })
    @ResultType(com.quickcanteen.dto.DishesBean.class)
    List<DishesBean> selectByTypeId(Integer typeId);

    Integer checkDishesInCompany(@Param("dishesIds")List<Integer> dishesIds);

    @Select({"<script>",
            "select *",
            "from dishes ",
            "<if test = \"keyword != null and keyword != ''\">where dishes_name like CONCAT('%',#{keyword},'%')</if>",
            "</script>"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<Dishes> searchDishes(@Param("keyword") String keyword);

    @Select({
            "select dishes.* ",
            "from dishes ",
            "where dishes_id in (select distinct(dishes_id) from `order` natural join order_dishes where user_id = #{userId})"
    })
    @ResultMap("BaseResultMap")
    List<Dishes> getDishesByUserId(int userId);

    @Select({
            "select dishes.* ",
            "from dishes natural join collect_dishes ",
            "where collector_id = #{userId}"
    })
    @ResultMap("BaseResultMap")
    List<Dishes> getCollectDishesListByUserIdByPage(int userId, RowBounds rowBounds);

    @Select({
            "select * ",
            "from dishes ",
            "where company_id = #{companyId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    List<Dishes> getDishesByCompanyId(int companyId);

    @Select({
            "select * ",
            "from dishes ",
            "order by dishes.rating desc limit 0,#{count} "
    })
    @ResultMap("BaseResultMap")
    List<Dishes> selectHighRatingDishesByCount(int count);

}