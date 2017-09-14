package com.quickcanteen.controller.api;

import com.quickcanteen.annotation.Authentication;
import com.quickcanteen.dto.BaseJson;
import com.quickcanteen.dto.DishesBean;
import com.quickcanteen.dto.TypeBean;
import com.quickcanteen.mapper.CompanyInfoMapper;
import com.quickcanteen.mapper.DishesMapper;
import com.quickcanteen.mapper.TypeMapper;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.Type;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by 11022 on 2017/8/20.
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController extends APIBaseController {
    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private DishesMapper dishesMapper;

    @RequestMapping(value = "/getCompanyInfoById")
    @Authentication
    public BaseJson getCompanyInfoById(@RequestParam("companyID") Integer companyID) {
        BaseJson baseJson = new BaseJson();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyID);
        if (Objects.isNull(companyInfo)) {
            return getResourceNotFoundResult();
        }
        baseJson.setObj(companyInfo);
        baseJson.setReturnCode("");
        return baseJson;
    }

    @RequestMapping(value = "/getTypesAndDishesByCompanyId")
    @Authentication
    public BaseJson getTypesAndDishesByCompanyId(@RequestParam("companyID") Integer companyID) {
        BaseJson baseJson = new BaseJson();
        List<TypeBean> typeBeans = typeMapper.selectByCompanyId(companyID).stream().map(this::parse).collect(Collectors.toList());
        baseJson.setObj(typeBeans);
        baseJson.setReturnCode("");
        return baseJson;
    }

    private TypeBean parse(Type type) {
        TypeBean typeBean = new TypeBean();
        try {
            BeanUtils.copyProperties(typeBean, type);
            typeBean.setDishesBeanList(dishesMapper.selectByTypeId(type.getTypeId()));
        } catch (Exception e) {

        }
        return typeBean;
    }
}
