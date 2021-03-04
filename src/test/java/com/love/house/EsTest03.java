package com.love.house;

import com.love.house.entity.elasticsearchDo.ESProductDO;
import com.love.house.mapper.elasticsearchDao.ProductRepository;
import com.love.house.mapper.elasticsearchDao.ProductRepository03;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * @Author: wy
 * @Date: 2021/1/26 14:33
 * @Description: esTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest03 {

    @Autowired
    private ProductRepository03 productRepository;

    @Test
    public void testSearch() {
        // 查找分类为 1 + 指定关键字，并且按照 id 升序
//        Page<ESProductDO> page = productRepository.search(1, "技术",
//                PageRequest.of(0, 5, Sort.Direction.ASC, "id"));
//        System.out.println(page.getTotalPages());
//
//        // 查找分类为 1 ，并且按照 id 升序
//        page = productRepository.search(1, null,
//                PageRequest.of(0, 5, Sort.Direction.ASC, "id"));
//        System.out.println(page.getTotalPages());
    }
}
