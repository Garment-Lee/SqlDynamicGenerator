package com.gfsolution.generator.element;

import com.gfsolution.generator.element.where.BaseWhereElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * select语句对应类
 *
 * @author Garment
 * @data 2019/6/27
 */
@Data
public class SelectElement {

    /**
     * 主表名
     */
    private String mainTableName;

    /**
     * 查询字段列表
     */
    private List<FieldElement> selectFieldList = new ArrayList<>();

    /**
     * join子句列表
     */
    private List<JoinElement> joinElementList = new ArrayList<>();

    /**
     * where子句列表
     */
    private List<BaseWhereElement> whereElementList = new ArrayList<>();

    /**
     * order by子句
     */
    private OrderByElement orderByElement;

    /**
     * limit属性
     */
    private LimitElement limitElement;

    /**
     * offset属性
     */
    private OffsetElement offsetElement;

    /**
     * group by 子句
     */
    private GroupByElement groupByElement;
}
