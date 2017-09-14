package com.quickcanteen.dto;

import java.util.*;

/**
 * Created by 11022 on 2017/7/3.
 */
public class SearchBean {
    private CompanyInfoBean companyInfoBean;
    private Integer companyId;
    private Boolean hasType;
    private Boolean hasDishes;
    private ArrayList<SearchTypeBean> searchTypeBeans;
    private ArrayList<DishesBean> dishesBeans;

    public static List<SearchBean> generate(Set<CompanyInfoBean> companyInfoBeans, Set<SearchTypeBean> searchTypeBeans, Set<DishesBean> dishesBeans) {
        Map<Integer, SearchBean> map = new HashMap<Integer, SearchBean>();
        for (CompanyInfoBean companyInfoBean : companyInfoBeans) {
            if (!map.containsKey(companyInfoBean.getCompanyId())) {
                SearchBean searchBean = new SearchBean(companyInfoBean);
                map.put(searchBean.getCompanyId(), searchBean);
            }
        }
        for (SearchTypeBean searchTypeBean : searchTypeBeans) {
            if (map.containsKey(searchTypeBean.getCompanyId())) {
                SearchBean searchBean = map.get(searchTypeBean.getCompanyId());
                searchBean.setHasType(true);
                searchBean.getSearchTypeBeans().add(searchTypeBean);
            } else {
                SearchBean searchBean = new SearchBean(searchTypeBean);
                map.put(searchBean.getCompanyId(), searchBean);
            }
        }
        for (DishesBean dishesBean : dishesBeans) {
            if (map.containsKey(dishesBean.getCompanyId())) {
                SearchBean searchBean = map.get(dishesBean.getCompanyId());
                searchBean.setHasDishes(true);
                searchBean.getDishesBeans().add(dishesBean);
            } else {
                SearchBean searchBean = new SearchBean(dishesBean);
                map.put(searchBean.getCompanyId(), searchBean);
            }
        }
        List<SearchBean> searchBeans = new ArrayList<SearchBean>(map.values());
        return searchBeans;
    }

    public SearchBean(CompanyInfoBean companyInfoBean) {
        this.companyId = companyInfoBean.getCompanyId();
        hasType = false;
        hasDishes = false;
        searchTypeBeans = new ArrayList<SearchTypeBean>();
        dishesBeans = new ArrayList<DishesBean>();
    }

    public SearchBean(SearchTypeBean searchTypeBean) {
        //this.companyInfoBean = searchTypeBean.getCompanyInfoBean();
        this.companyId=searchTypeBean.getCompanyId();
        hasType = true;
        hasDishes = false;
        searchTypeBeans = new ArrayList<SearchTypeBean>();
        dishesBeans = new ArrayList<DishesBean>();
        searchTypeBeans.add(searchTypeBean);
    }

    public SearchBean(DishesBean dishesBean) {
        //this.companyInfoBean = dishesBean.getCompanyInfoBean();
        this.companyId=dishesBean.getCompanyId();
        hasType = false;
        hasDishes = true;
        searchTypeBeans = new ArrayList<SearchTypeBean>();
        dishesBeans = new ArrayList<DishesBean>();
        dishesBeans.add(dishesBean);
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public CompanyInfoBean getCompanyInfoBean() {
        return companyInfoBean;
    }

    public void setCompanyInfoBean(CompanyInfoBean companyInfoBean) {
        this.companyInfoBean = companyInfoBean;
    }

    public Boolean getHasType() {
        return hasType;
    }

    public void setHasType(Boolean hasType) {
        this.hasType = hasType;
    }

    public Boolean getHasDishes() {
        return hasDishes;
    }

    public void setHasDishes(Boolean hasDishes) {
        this.hasDishes = hasDishes;
    }

    public ArrayList<SearchTypeBean> getSearchTypeBeans() {
        return searchTypeBeans;
    }

    public void setSearchTypeBeans(ArrayList<SearchTypeBean> searchTypeBeans) {
        this.searchTypeBeans = searchTypeBeans;
    }


    public ArrayList<DishesBean> getDishesBeans() {
        return dishesBeans;
    }

    public void setDishesBeans(ArrayList<DishesBean> dishesBeans) {
        this.dishesBeans = dishesBeans;
    }
}
