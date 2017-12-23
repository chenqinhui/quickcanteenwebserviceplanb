package com.quickcanteen.mapper;

import com.quickcanteen.dto.CompanyInfoBean;
import com.quickcanteen.model.CompanyInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface CompanyInfoMapper {
    @Delete({
            "delete from company_info",
            "where company_id = #{companyId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer companyId);

    @Insert({
            "insert into company_info (company_id, company_name, ",
            "account_number, password, ",
            "start_time, end_time, busy_degree, rating, company_portrait)",
            "values (#{companyId,jdbcType=INTEGER}, #{companyName,jdbcType=VARCHAR}, ",
            "#{accountNumber,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
            "#{startTime,jdbcType=TIMESTAMP}, #{endTime,jdbcType=TIMESTAMP}), ",
            "#{busyDegree}, #{rating}, #{companyPortrait} "
    })
    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    @Select({
            "select * ",
            "from company_info",
            "where company_id = #{companyId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CompanyInfo selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(CompanyInfo record);

    @Update({
            "update company_info",
            "set company_name = #{companyName,jdbcType=VARCHAR},",
            "account_number = #{accountNumber,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "start_time = #{startTime,jdbcType=TIMESTAMP},",
            "end_time = #{endTime,jdbcType=TIMESTAMP},",
            "busy_degree = #{busyDegree},",
            "rating = #{rating,jdbcType=DOUBLE},",
            "company_portrait = #{companyPortrait}",
            "where company_id = #{companyId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CompanyInfo record);

    @Select({
            "select * ",
            "from company_info"
    })
    @ResultMap("BaseResultMap")
    List<CompanyInfo> getCompanyInfoByPage(RowBounds rowBounds);

    @Select({"<script>",
            "select *",
            "from company_info",
            "<if test = \"keyword != null and keyword != ''\">where company_name like CONCAT('%',#{keyword},'%')</if>",
            "</script>"
    })
    @ResultMap("BaseResultMap")
    List<CompanyInfo> searchCompany(@Param("keyword") String keyword);

    @Select({
            "select * ",
            "from company_info " ,
            "where account_number = #{accountNumber,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    List<CompanyInfo> selectByAccountNumber(@Param("accountNumber")String accountNumber);

}