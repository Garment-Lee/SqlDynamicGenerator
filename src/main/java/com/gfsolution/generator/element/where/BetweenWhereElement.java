package com.gfsolution.generator.element.where;

/**
 * between运算符
 */
public class BetweenWhereElement extends BaseWhereElement{

    /**
     * 操作数一
     */
    private String operatorValueOne;

    /**
     * 操作数二
     */
    private String operatorValueTwo;

    public String sql() {
        sqlStr = " " + connector + " " + fieldElement.getTableName() + "." + fieldElement.getFieldName() + " between " + operatorValueOne + " and " + operatorValueTwo;
        return sqlStr;
    }

    public String getOperatorValueTwo() {
        return operatorValueTwo;
    }

    public void setOperatorValueTwo(String operatorValueTwo) {
        this.operatorValueTwo = operatorValueTwo;
    }

    public String getOperatorValueOne() {
        return operatorValueOne;
    }

    public void setOperatorValueOne(String operatorValueOne) {
        this.operatorValueOne = operatorValueOne;
    }
}
