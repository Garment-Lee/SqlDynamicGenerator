package com.gfsolution.generator.element.where;

/**
 * like操作符
 */
public class LikeWhereElement extends BaseWhereElement{

    /**
     * 模糊搜索类型，0：完整搜索；1：%M，匹配以字符串M开头的字符串；2：%M%，匹配包含子字符串M的字符串；
     */
    private int likeType;

    private String operatorValue;

    public String sql() {
        if (likeType == 0){
            sqlStr = " " + connector + " " + fieldElement.getTableName() + "." + fieldElement.getFieldName() + " like " + "'" + operatorValue + "'";
        } else if (likeType == 1){
            sqlStr = " " + connector + " " + fieldElement.getTableName() + "." + fieldElement.getFieldName() + " like " + "'%" + operatorValue + "'";
        } else if (likeType == 2){
            sqlStr = " " + connector + " " + fieldElement.getTableName() + "." + fieldElement.getFieldName() + " like " + "'%" + operatorValue + "%'";
        }
        return sqlStr;
    }

    public int getLikeType() {
        return likeType;
    }

    public void setLikeType(int likeType) {
        this.likeType = likeType;
    }

    public String getOperatorValue() {
        return operatorValue;
    }

    public void setOperatorValue(String operatorValue) {
        this.operatorValue = operatorValue;
    }
}
