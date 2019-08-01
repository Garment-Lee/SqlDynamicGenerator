package com.gfsolution.generator;


import com.gfsolution.generator.element.*;
import com.gfsolution.generator.element.where.BaseWhereElement;

/**
 * SQL语句创建者
 *
 * @author Garment
 * @date 2019/6/26
 */
public class SQLBuilder {

    /**
     * 主表名
     */
    private String mainTableName;

    /**
     * select语句对象
     */
    private SelectElement selectElement;

    /**
     * 设置主表名
     * @param tableName 主表名
     * @return
     */
    public SQLBuilder mainTable(String tableName){
        this.mainTableName = tableName;
        selectElement = new SelectElement();
        selectElement.setMainTableName(mainTableName);
        return this;
    }

    /**
     * 添加查询字段
     * @param fieldName
     * @param fieldTableName
     * @return
     */
    public SQLBuilder selectField(String fieldName, String fieldTableName){
        FieldElement fieldElement = new FieldElement();
        fieldElement.setTableName(fieldTableName);
        fieldElement.setFieldName(fieldName);

        //添加到select对象中
        selectElement.getSelectFieldList().add(fieldElement);
        return this;
    }

    /**
     * 生成join子句对象
     * @param mainTableField 主表字段
     * @param childTableName 子表名
     * @param childTableField 子表字段
     * @return
     */
    public SQLBuilder join(String mainTableField, String childTableName, String childTableField){
        //表名已经添加过了，就不生成join子句
        if (childTableName.equals(selectElement.getMainTableName())){
            return this;
        }
        for (JoinElement joinElement : selectElement.getJoinElementList()){
            if (childTableName.equals(joinElement.getChildTableName())){
                return this;
            }
        }

        JoinElement joinElement = new JoinElement();
        joinElement.setChildTableName(childTableName);

        FieldElement childTableFieldElement = new FieldElement();
        childTableFieldElement.setFieldName(childTableField);
        childTableFieldElement.setTableName(childTableName);
        joinElement.setChildTableField(childTableFieldElement);

        FieldElement mainTableFieldElement = new FieldElement();
        mainTableFieldElement.setTableName(mainTableName);
        mainTableFieldElement.setFieldName(mainTableField);
        joinElement.setMainTableField(mainTableFieldElement);

        //添加到select对象中
        selectElement.getJoinElementList().add(joinElement);
        return this;
    }

    /**
     * 生成where子句对象，参数包括连接符，操作符和操作值
     * @param baseWhereElement
     * @return
     */
    public SQLBuilder where(BaseWhereElement baseWhereElement){
        //添加到select对象中
        selectElement.getWhereElementList().add(baseWhereElement);
        return this;
    }

    /**
     * 生成group by子句对象
     * @param fieldName
     * @param fieldTableName
     * @return
     */
    public SQLBuilder groupBy(String fieldName, String fieldTableName){
        GroupByElement groupByElement = new GroupByElement();
        FieldElement fieldElement = new FieldElement();
        fieldElement.setTableName(fieldTableName);
        fieldElement.setFieldName(fieldName);
        groupByElement.setFieldElement(fieldElement);

        //添加到select对象中
        selectElement.setGroupByElement(groupByElement);
        return this;
    }

    /**
     * 生成order by子句对象
     * @param fieldName
     * @param fieldTableName
     * @return
     */
    public SQLBuilder orderBy(String fieldName, String fieldTableName, String sortType){
        OrderByElement orderByElement = new OrderByElement();
        FieldElement fieldElement = new FieldElement();
        fieldElement.setFieldName(fieldName);
        fieldElement.setTableName(fieldTableName);
        orderByElement.setFieldElement(fieldElement);
        orderByElement.setSortType(sortType);

        //添加到select对象中
        selectElement.setOrderByElement(orderByElement);
        return this;
    }

    /**
     * 生成limit子句对象
     * @param limitValue
     * @return
     */
    public SQLBuilder limit(int limitValue){
        LimitElement limitElement = new LimitElement();
        limitElement.setLimitVal(limitValue);

        //添加到select对象中
        selectElement.setLimitElement(limitElement);
        return this;
    }

    /**
     * 生成offset子句对象
     * @param offsetValue
     * @return
     */
    public SQLBuilder offset(int offsetValue){
        OffsetElement offsetElement = new OffsetElement();
        offsetElement.setOffsetVal(offsetValue);

        //添加到select对象中
        selectElement.setOffsetElement(offsetElement);
        return this;
    }

    /**
     * 生成最终的sql语句
     * @return
     */
    public String build(){
        StringBuilder sqlStringBuilder = new StringBuilder();

        sqlStringBuilder.append("select ");

        String selectString;

        //查询字段
        int size = selectElement.getSelectFieldList().size();
        for (int i = 0; i < size; i ++){
            FieldElement fieldElement = selectElement.getSelectFieldList().get(i);
            selectString = fieldElement.getTableName() + "." + fieldElement.getFieldName();
            if (i < size - 1){
                selectString = selectString + ", ";
            } else {
                selectString = selectString + " ";
            }
            sqlStringBuilder.append(selectString);
        }

        sqlStringBuilder.append("from " + selectElement.getMainTableName() + " " + selectElement.getMainTableName());

        //join子句
        for(JoinElement joinElement : selectElement.getJoinElementList()){
            selectString = " join " + joinElement.getChildTableName() + " " + joinElement.getChildTableName()
                    + " on " + joinElement.getMainTableField().getTableName() + "." + joinElement.getMainTableField().getFieldName()
                    + " = " + joinElement.getChildTableField().getTableName() + "." + joinElement.getChildTableField().getFieldName();
            sqlStringBuilder.append(selectString);
        }

        //where子句
        sqlStringBuilder.append(" where 1 = 1");
        for (BaseWhereElement baseWhereElement : selectElement.getWhereElementList()){
            selectString = baseWhereElement.sql();
            sqlStringBuilder.append(selectString);
        }

        //group by子句
        if (selectElement.getGroupByElement() != null){
            sqlStringBuilder.append(" group by ");
            sqlStringBuilder.append(selectElement.getGroupByElement().getFieldElement().getTableName() + "." + selectElement.getGroupByElement().getFieldElement().getFieldName());
        }

        //order by子句
        if (selectElement.getOrderByElement() != null){
            sqlStringBuilder.append(" order by " + selectElement.getOrderByElement().getFieldElement().getTableName() + "." + selectElement.getOrderByElement().getFieldElement().getFieldName() + " " + selectElement.getOrderByElement().getSortType());
        }

        //limit子句
        if (selectElement.getLimitElement() != null){
            sqlStringBuilder.append(" limit " + selectElement.getLimitElement().getLimitVal());
        }

        //offset子句
        if (selectElement.getOffsetElement() != null){
            sqlStringBuilder.append(" offset " + selectElement.getOffsetElement().getOffsetVal());
        }

        return sqlStringBuilder.toString();
    }

}
