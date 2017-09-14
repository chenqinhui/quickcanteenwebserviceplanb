package com.quickcanteen.controller.api;

import com.quickcanteen.dto.*;
import com.quickcanteen.mapper.CompanyInfoMapper;
import com.quickcanteen.mapper.DishesMapper;
import com.quickcanteen.mapper.TypeMapper;
import com.quickcanteen.util.ObjectParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by 11022 on 2017/8/20.
 */
@RestController
@RequestMapping("/api/search")
public class SearchController extends APIBaseController {
    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private DishesMapper dishesMapper;

    @RequestMapping(value = "/searchByText")
    public BaseJson getUserInfoByUserID(@RequestParam("searchText") String searchText) {
        String[] strings = searchText.split("\\s+");
        Set<CompanyInfoBean> companyInfoBeans = new HashSet<CompanyInfoBean>();
        Set<SearchTypeBean> searchTypeBeans = new HashSet<SearchTypeBean>();
        Set<DishesBean> dishesBeans = new HashSet<DishesBean>();
        for (String s : strings) {
            companyInfoBeans.addAll(companyInfoMapper.searchCompany(s).stream().map(ObjectParser::parse).collect(Collectors.toList()));
            searchTypeBeans.addAll(typeMapper.searchType(s).stream().map(ObjectParser::parse).collect(Collectors.toList()));
            dishesBeans.addAll(dishesMapper.searchDishes(s).stream().map(ObjectParser::parse).collect(Collectors.toList()));
        }
        BaseJson baseJson = new BaseJson();
        List<SearchBean> searchBeans = SearchBean.generate(companyInfoBeans, searchTypeBeans, dishesBeans);
        searchBeans.forEach(searchBean -> searchBean.setCompanyInfoBean(ObjectParser.parse(companyInfoMapper.selectByPrimaryKey(searchBean.getCompanyId()))));
        baseJson.setObj(searchBeans);
        return baseJson;
    }
}
