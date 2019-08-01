package com.gfsolution.generator.test;

import com.gfsolution.generator.SQLBuilder;
import com.gfsolution.generator.element.FieldElement;
import com.gfsolution.generator.element.where.EqualWhereElement;
import com.gfsolution.generator.element.where.InWhereElement;
import com.gfsolution.generator.element.where.LikeWhereElement;

/** Example:
 * select
 *         bridge.id,
 *         bridge.uid,
 *         bridge.name,
 *         bridge.update_time,
 *         address.province,
 *         address.city,
 *         address.county
 *     from
 *         a_bridge bridge
 *     join
 *         b_address address
 *             on bridge.uid = address.bridge_uid
 *     where
 *         address.default_address = 1
 *         and bridge.uid in (
 *             select
 *                 bridge_name.bridge_uid
 *             from
 *                 a_bridge_name bridge_name
 *             where
 *                 bridge_name.name like '%%'
 *             group by
 *                 bridge_name.bridge_uid
 *         )
 *     order by
 *         CONVERT(address.county USING gbk) desc limit 10 offset 0
 */
public class TestApplication {

    public static void main(String arg[]){

        //select子句
        SQLBuilder subSqlBuilder = new SQLBuilder();
        subSqlBuilder.mainTable("a_bridge_name");

        //查询字段
        subSqlBuilder.selectField("bridge_uid", "a_bridge_name");

        //where子句
        LikeWhereElement likeWhereElement = new LikeWhereElement();
        likeWhereElement.setLikeType(2);
        likeWhereElement.setOperatorValue("ConghuaBridge");
        likeWhereElement.setConnector("and");
        likeWhereElement.setFieldElement(new FieldElement("a_bridge_name", "name"));
        subSqlBuilder.where(likeWhereElement);

        //group by子句
        subSqlBuilder.groupBy("bridge_uid", "a_bridge_name");


        //select主句
        SQLBuilder sqlBuilder = new SQLBuilder();
        sqlBuilder.mainTable("a_bridge");

        //join子句
        sqlBuilder.join("uid", "b_address", "bridge_uid");

        //查询字段
        sqlBuilder.selectField("id", "a_bridge");
        sqlBuilder.selectField("uid", "a_bridge");
        sqlBuilder.selectField("name", "a_bridge");
        sqlBuilder.selectField("update_time", "a_bridge");
        sqlBuilder.selectField("province", "b_address");
        sqlBuilder.selectField("city", "b_address");
        sqlBuilder.selectField("county", "b_address");

        //where子句
        EqualWhereElement equalWhereElement = new EqualWhereElement();
        equalWhereElement.setConnector("and");
        equalWhereElement.setFieldType(1);
        equalWhereElement.setOperatorValue("1");
        equalWhereElement.setFieldElement(new FieldElement("b_address", "default_address"));
        sqlBuilder.where(equalWhereElement);

        InWhereElement inWhereElement = new InWhereElement();
        inWhereElement.setInString("(" + subSqlBuilder.build() + ")");
        inWhereElement.setConnector("and");
        inWhereElement.setFieldType(0);
        inWhereElement.setFieldElement(new FieldElement("a_bridge", "uid"));
        sqlBuilder.where(inWhereElement);

        //sort子句
        sqlBuilder.orderBy("county", "b_address", "desc");

        //limit子句
        sqlBuilder.limit(10);

        //offset子句
        sqlBuilder.offset(0);

        String finalSqlStr = sqlBuilder.build();
        System.out.println("finalSqlStr:" + finalSqlStr);

    }
}
