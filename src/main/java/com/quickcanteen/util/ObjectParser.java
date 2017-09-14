package com.quickcanteen.util;


import com.quickcanteen.dto.CompanyInfoBean;
import com.quickcanteen.dto.DishesBean;
import com.quickcanteen.dto.SearchTypeBean;
import com.quickcanteen.model.CompanyInfo;
import com.quickcanteen.model.Dishes;
import com.quickcanteen.model.Type;
import org.apache.commons.beanutils.BeanUtils;

public final class ObjectParser {

    private ObjectParser() {
    }

    public static DishesBean parse(Dishes dishes) {
        DishesBean dishesBean = new DishesBean();
        try {
            BeanUtils.copyProperties(dishesBean, dishes);
        } catch (Exception e) {

        }
        return dishesBean;
    }

    public static CompanyInfoBean parse(CompanyInfo companyInfo) {
        CompanyInfoBean companyInfoBean = new CompanyInfoBean();
        try {
            BeanUtils.copyProperties(companyInfoBean, companyInfo);
        } catch (Exception e) {

        }
        return companyInfoBean;
    }

    public static SearchTypeBean parse(Type type) {
        SearchTypeBean typeBean = new SearchTypeBean();
        try {
            BeanUtils.copyProperties(typeBean, type);
        } catch (Exception e) {

        }
        return typeBean;
    }
}
