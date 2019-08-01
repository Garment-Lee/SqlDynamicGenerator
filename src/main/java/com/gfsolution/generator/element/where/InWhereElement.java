package com.gfsolution.generator.element.where;

/**
 * In操作符
 */
public class InWhereElement extends BaseWhereElement{

    /**
     * in操作符的字符串，注意要包含括号()。in操作可以是多值查询，也可以是嵌套查询。
     */
    private String inString;

    public String sql() {
        sqlStr = " " + connector + " " + fieldElement.getTableName() + "." + fieldElement.getFieldName() + " in " + inString;
        return sqlStr;
    }

    public String getInString() {
        return inString;
    }

    public void setInString(String inString) {
        this.inString = inString;
    }
}
