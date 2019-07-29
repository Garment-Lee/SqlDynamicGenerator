package com.gfsolution.generator;

/**
 * 搜索参数类
 *
 * @author Garment
 * @date 2019/7/4
 */
public class SearchParam {

    /**
     * 搜索内容一
     */
    public String searchContentValueOne;

    /**
     * 搜索内容二
     */
    public String searchContentValueTwo;

    /**
     * 搜索内容对应的字段名称
     */
    public String searchFieldName;

    /**
     * 搜索内容对应的表名
     */
    public String tableName;

    /**
     * 字段类型，0：字符类型，1：int类型,2:float类型
     */
    public int fieldType;

    /**
     * 是否需要区间搜索
     */
    public boolean intervalSearch;
}
