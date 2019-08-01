package com.gfsolution.generator.element.where;

import com.gfsolution.generator.element.FieldElement;

/**
 * where子句基础类
 */
abstract public class BaseWhereElement {

    /**
     * where子句的连接运算符，"and"或者"or"
     */
    String connector;

    /**
     * where子句中的操作数
     */
    FieldElement fieldElement;

    /**
     * 字段类型，0：文本类型，1：数值类型
     */
    int fieldType;

    /**
     * 返回的用于拼接的字符串
     */
    String sqlStr;

    /**
     * 生成构建sql语句的字符串
     * @return
     */
    public abstract String sql();

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public FieldElement getFieldElement() {
        return fieldElement;
    }

    public void setFieldElement(FieldElement fieldElement) {
        this.fieldElement = fieldElement;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public String getSqlStr() {
        return sqlStr;
    }

}
