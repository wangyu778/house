package com.love.house.mapper.elasticsearchDao;

import com.love.house.entity.elasticsearchDo.ESProductDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @Author: wy
 * @Date: 2021/1/26 14:30
 * @Description:
 */
public interface ProductRepository02 extends ElasticsearchRepository<ESProductDO,Integer> {

    ESProductDO findByName(String name);

    Page<ESProductDO> findByNameLike(String name, Pageable pageable);

}
