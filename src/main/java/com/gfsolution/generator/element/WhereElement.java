package com.gfsolution.generator.element;

import lombok.Data;

/**
 * where元素对应类
 *
 * @author Garment
 * @date 2019/6/27
 */
@Data
public class WhereElement {

    /**
     * where子句的连接运算符，"and"或者"or"
     */
    private String connector;

    /**
     * where子句中的操作数
     */
    private FieldElement fieldElement;

    /**
     * where子句中的操作符，如"=",">","<","between","like"等
     */
    private String operator;

    /**
     * where子句操作数值一
     */
    private String operatorValueOne;

    /**
     * where子句操作数值二
     */
    private String operatorValueTwo;

    /**
     * 字段类型，0：文本类型，1：整形，2：浮点型
     */
    private int fieldType;
}
