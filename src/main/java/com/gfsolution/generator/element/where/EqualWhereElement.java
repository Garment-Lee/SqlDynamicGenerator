package com.gfsolution.generator.element.where;

/**
 * 等于运算符
 */
public class EqualWhereElement extends BaseWhereElement{

    /**
     * 运算符操作数值
     */
    private String operatorValue;

    public String sql() {
        if (fieldType == 0){
            sqlStr = " " + connector + " " + fieldElement.getTableName() + "." + fieldElement.getFieldName() + " = " + "'" + operatorValue + "'";
        }else {
            sqlStr = " " + connector + " " + fieldElement.getTableName() + "." + fieldElement.getFieldName() + " = " + operatorValue;
        }
        return sqlStr;
    }

    public String getOperatorValue() {
        return operatorValue;
    }

    public void setOperatorValue(String operatorValue) {
        this.operatorValue = operatorValue;
    }
}
