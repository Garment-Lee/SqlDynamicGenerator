# SqlDynamicGenerator
动态构建SQL语句，一条SQL语句完成高级搜索功能。

#### 特点
* 动态添加查询参数。
* 结构化构建SQL语句。
* 跨多表查询。
* 分页。
* 排序。


#### 应用场景
当我们需要查询的信息保存在不同的数据表；查询参数是不确定的，会动态改变；查询的结果需要分页、排序。我们需要动态构建SQL语句来完成我们的查询需求。

#### 应用例子
在一个保存桥信息的数据库中，桥的主要信息保存在a_bridge表中，桥的地址信息保存在b_address中，桥的名字相关信息保存在a_bridge_name表中。

 需求：
* 首页展示桥的id，uid，name，update_time，province，city，address信息；
* 可以根据表中的所有字段进行搜索，而且是多个字段同时搜索；
* 可以对搜索结果进行分页排序。

需要构建的SQL语句：
```
SELECT a_bridge.id, a_bridge.uid, a_bridge.name, a_bridge.update_time, b_address.province, b_address.city, b_address.county from a_bridge  
a_bridge join b_address b_address on a_bridge.uid = b_address.bridge_uid where 1 = 1 and b_address.default_address = 1 and a_bridge.uid in     
(select a_bridge_name.bridge_uid from a_bridge_name a_bridge_name where 1 = 1 and a_bridge_name.name like '%仙宫桥%' group 
by a_bridge_name.bridge_uid) order by b_address.county desc limit 10 offset 0;
```

代码调用：
```
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
```

