package com.love.house.mapper.elasticsearchDao;

import com.love.house.entity.elasticsearchDo.ESMessDO;
import com.love.house.entity.elasticsearchDo.ESProductDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @Author: wy
 * @Date: 2021/1/26 14:30
 * @Description:
 */
public interface ProductRepository extends ElasticsearchRepository<ESMessDO,Integer> {
}
