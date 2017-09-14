package com.quickcanteen.dto;


/**
 * Created by 11022 on 2017/7/3.
 */
public class SearchTypeBean {
    private Integer companyId;
    private Integer typeId;
    private String typeName;


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchTypeBean)) return false;

        SearchTypeBean that = (SearchTypeBean) o;

        if (getCompanyId() != null ? !getCompanyId().equals(that.getCompanyId()) : that.getCompanyId() != null)
            return false;
        if (getTypeId() != null ? !getTypeId().equals(that.getTypeId()) : that.getTypeId() != null) return false;
        return getTypeName() != null ? getTypeName().equals(that.getTypeName()) : that.getTypeName() == null;
    }

    @Override
    public int hashCode() {
        int result = getCompanyId() != null ? getCompanyId().hashCode() : 0;
        result = 31 * result + (getTypeId() != null ? getTypeId().hashCode() : 0);
        result = 31 * result + (getTypeName() != null ? getTypeName().hashCode() : 0);
        return result;
    }
}
