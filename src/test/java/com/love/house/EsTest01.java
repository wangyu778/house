package com.love.house;

import com.love.house.entity.elasticsearchDo.ESProductDO;
import com.love.house.mapper.elasticsearchDao.ProductRepository;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Author: wy
 * @Date: 2021/1/26 14:33
 * @Description: esTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest01 {

    @Autowired
    private ProductRepository productRepository;

    @Test // 插入一条记录
    public void testInsert() {
        ESProductDO product = new ESProductDO ();
        product.setId(1); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
        product.setName("芋道源码");
        product.setSellPoint("愿半生编码，如一生老友");
        product.setDescription("我只是一个描述");
        product.setCid(1);
        product.setCategoryName("技术");
        productRepository.save(product);
    }

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
    // 所以，这里仅仅是作为一个示例。
    @Test // 更新一条记录
    public void testUpdate() {
        ESProductDO  product = new ESProductDO ();
        product.setId(1);
        product.setCid(2);
        product.setCategoryName("技术-Java");
        productRepository.save(product);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
        productRepository.deleteById(1);
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        Optional<ESProductDO > userDO = productRepository.findById(1);
        ESProductDO esProductDO = userDO.get();
        System.out.println(esProductDO.getCategoryName());
        System.out.println(esProductDO.getCid());
        System.out.println(esProductDO.getDescription());
        System.out.println(esProductDO.getName());
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        Iterable<ESProductDO > users = productRepository.findAllById(Arrays.asList(1, 4));
        users.forEach(System.out::println);
    }
}
