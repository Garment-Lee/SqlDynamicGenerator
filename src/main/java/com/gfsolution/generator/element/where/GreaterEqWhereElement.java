package com.gfsolution.generator.element.where;

/**
 * 大于等于运算符
 */
public class GreaterEqWhereElement extends BaseWhereElement {

    /**
     * 运算符操作数值
     */
    private String operatorValue;

    public String sql() {
        sqlStr = " " + connector + " " + fieldElement.getTableName() + "." + fieldElement.getFieldName() + " >= " + operatorValue;
        return sqlStr;
    }

    public String getOperatorValue() {
        return operatorValue;
    }

    public void setOperatorValue(String operatorValue) {
        this.operatorValue = operatorValue;
    }
}
