package com.gfsolution.generator.element;

import lombok.Data;

/**
 * Join元素对应的类
 *
 * @author Garment
 * @date 2019/6/26
 */
@Data
public class JoinElement {

    /**
     * 子表表名
     */
    private String childTableName;

    /**
     * 主表字段（用于on属性）
     */
    private FieldElement mainTableField;

    /**
     * 子表字段（用于on属性）
     */
    private FieldElement childTableField;

}
