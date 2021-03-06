package com.love.house.mapper.elasticsearchDao;

import com.love.house.entity.elasticsearchDo.ESMessDO;
import com.love.house.entity.elasticsearchDo.ESProductDO;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.util.StringUtils;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;


/**
 * @Author: wy
 * @Date: 2021/1/26 14:30
 * @Description:
 */
public interface ProductRepository03 extends ElasticsearchRepository<ESMessDO,Integer> {

    default Page<ESMessDO> search(Integer cid, String keyword, Pageable pageable) {
        // <1> 创建 NativeSearchQueryBuilder 对象
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // <2.1> 筛选条件 cid
        if (cid != null) {
            nativeSearchQueryBuilder.withFilter(QueryBuilders.termQuery("cid", cid));
        }
        // <2.2> 筛选
        if (StringUtils.hasText(keyword)) {
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] functions = { // TODO 芋艿，分值随便打的
                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("name", keyword),
                            ScoreFunctionBuilders.weightFactorFunction(2)),
                    new FunctionScoreQueryBuilder.FilterFunctionBuilder(matchQuery("message", keyword),
                            ScoreFunctionBuilders.weightFactorFunction(10))
            };
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(functions)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2F);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }
        // 排序
        if (StringUtils.hasText(keyword)) { // <3.1> 关键字，使用打分
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        } else if (pageable.getSort().isSorted()) { // <3.2> 有排序，则进行拼接
            pageable.getSort().get().forEach(sortField -> nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortField.getProperty())
                    .order(sortField.getDirection().isAscending() ? SortOrder.ASC : SortOrder.DESC)));
        } else { // <3.3> 无排序，则按照 ID 倒序
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        }
        // <4> 分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        // <5> 执行查询
        return search(nativeSearchQueryBuilder.build());
    }

}
