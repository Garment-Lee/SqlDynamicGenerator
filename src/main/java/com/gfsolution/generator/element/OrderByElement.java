package com.gfsolution.generator.element;

import lombok.Data;

/**
 * order by字句对应类
 *
 * @author Garment
 * @date 2019/6/27
 */
@Data
public class OrderByElement {

    /**
     * 排序类型，升序：ASC，降序：DESC
     */
    private String sortType;

    /**
     * order by字句的字段
     */
    private FieldElement fieldElement;
}
