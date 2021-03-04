package com.love.house;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import com.love.house.entity.elasticsearchBo.*;

import javax.annotation.Resource;

/**
 * @Author: wy
 * @Date: 2021/1/27 14:20
 * @Description: ElasticsearchTemplate 在7.0版本后，就停止继续使用啦
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest04 {
//
//    @Resource
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @Test
//    public void test() {
//
//        // <1> 创建 ES 搜索条件
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withIndices("product");
//        // <2> 筛选
//        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("芋道",
//                "name", "sellPoint", "categoryName"));
//        // <3> 聚合
//        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cids").field("cid")); // 商品分类
//        // <4> 执行查询
//        ProductConditionBO condition = elasticsearchTemplate.query(nativeSearchQueryBuilder.build(), response -> {
//            ProductConditionBO result = new ProductConditionBO();
//            // categoryIds 聚合
//            Aggregation categoryIdsAggregation = response.getAggregations().get("cids");
//            if (categoryIdsAggregation != null) {
//                result.setCategories(new ArrayList<>());
//                for (LongTerms.Bucket bucket  : (((LongTerms) categoryIdsAggregation).getBuckets())) {
//                    result.getCategories().add(new ProductConditionBO.Category().setId(bucket.getKeyAsNumber().intValue()));
//                }
//            }
//            // 返回结果
//            return result;
//        });
//        // <5> 后续遍历 condition.categories 数组，查询商品分类，设置商品分类名。
//        System.out.println();
//    }

}
