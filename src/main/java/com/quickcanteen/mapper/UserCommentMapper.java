package com.quickcanteen.mapper;

import com.quickcanteen.model.UserComment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserCommentMapper {
    @Delete({
            "delete from user_comment",
            "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer commentId);

    @Insert({
            "insert into user_comment (comment_id, commenter_id, ",
            "rating, comment_content, comment_time)",
            "values (#{commentId,jdbcType=INTEGER}, #{commenterId,jdbcType=INTEGER}, ",
            "#{rating,jdbcType=DOUBLE}, #{commentContent,jdbcType=LONGVARCHAR}, ",
            "#{commentTime,jdbcType=TIMESTAMP})"
    })
    int insert(UserComment record);

    int insertSelective(UserComment record);

    @Select({
            "select",
            "comment_id, commenter_id, rating, comment_content, comment_time",
            "from user_comment",
            "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    UserComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(UserComment record);

    @Select({
            "select *",
            "from user_comment where comment_id in (select comment_id from comment_dishes",
            "where dishes_id = #{dishesId, jdbcType = INTEGER})"
    })
    @ResultMap("ResultMapWithBLOBs")
    //List<UserComment> selectByDishesId(Integer dishesId);
    //@ResultMap("BaseResultMap")
    //List<UserComment> selectByDishesId(Integer dishesId);
    List<UserComment> selectByDishesId(@Param("dishesId") Integer dishesId);


    @Update({
            "update user_comment",
            "set commenter_id = #{commenterId,jdbcType=INTEGER},",
            "rating = #{rating,jdbcType=DOUBLE},",
            "comment_content = #{commentContent,jdbcType=LONGVARCHAR}",
            "comment_time = #{commentTime,jdbcType=TIMESTAMP}",
            "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(UserComment record);

    @Update({
            "update user_comment",
            "set commenter_id = #{commenterId,jdbcType=INTEGER},",
            "rating = #{rating,jdbcType=DOUBLE}",
            "comment_time = #{commentTime,jdbcType=TIMESTAMP}",
            "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserComment record);

    @Select({
            "select * ",
            "from user_comment",
            "where comment_id in (select comment_id from comment_company where company_id = #{companyId}) ",
            "order by comment_time desc"
    })
    @ResultMap("BaseResultMap")
    List<UserComment> getCommentsByCompanyId(@Param("companyId") Integer companyId, RowBounds rowBounds);

    @Select({
            "select * ",
            "from user_comment",
            "where comment_id in (select comment_id from comment_company where company_id = #{companyId}) ",
            "and rating >= 4.0 order by comment_time desc"
    })
    @ResultMap("BaseResultMap")
    List<UserComment> getGoodCommentsByCompanyId(@Param("companyId") Integer companyId, RowBounds rowBounds);

    @Select({
            "select * ",
            "from user_comment",
            "where comment_id in (select comment_id from comment_company where company_id = #{companyId}) ",
            "and rating > 2.0 and rating < 4.0 order by comment_time desc"
    })
    @ResultMap("BaseResultMap")
    List<UserComment> getMiddleCommentsByCompanyId(@Param("companyId") Integer companyId, RowBounds rowBounds);

    @Select({
            "select * ",
            "from user_comment",
            "where comment_id in (select comment_id from comment_company where company_id = #{companyId}) ",
            "and rating <= 2.0 order by comment_time desc"
    })
    @ResultMap("BaseResultMap")
    List<UserComment> getBadCommentsByCompanyId(@Param("companyId") Integer companyId, RowBounds rowBounds);

    @Select({
            "select avg(rating) ",
            "from user_comment natural join comment_company ",
            "where company_id = #{companyId} "
    })
    double getRatingByCompanyId(@Param("companyId") Integer companyId);

    @Select({
            "select avg(rating) ",
            "from user_comment natural join comment_dishes ",
            "where dishes_id = #{dishesId} "
    })
    double getRatingByDishesId(@Param("dishesId") Integer dishesId);

    @Select({
            "select *",
            "from user_comment natural join comment_dishes",
            "where dishes_id = #{dishesId}"
    })
    @ResultMap("BaseResultMap")
    List<UserComment> getUserCommentsByDishId(@Param("dishesId")Integer dishesId);
}