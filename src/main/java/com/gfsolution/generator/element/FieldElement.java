package com.gfsolution.generator.element;

import lombok.Data;

/**
 * 字段元素对应类
 *
 * @author Garment
 * @date 2019/6/26
 */
@Data
public class FieldElement {

    /**
     * 字段所属表的名称
     */
    private String tableName;

    /**
     * 字段名称
     */
    private String fieldName;

    public FieldElement(){

    }

    public FieldElement(String tableName, String fieldName){
        this.tableName = tableName;
        this.fieldName = fieldName;
    }
}
