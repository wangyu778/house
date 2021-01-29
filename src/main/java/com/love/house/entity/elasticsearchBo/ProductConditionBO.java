package com.love.house.entity.elasticsearchBo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wy
 * @Date: 2021/1/27 14:03
 * @Description: 商品搜索条件BO
 */
@Component
public class ProductConditionBO {
    /**
     * 商品分类数组
     */
    private List<Category> categories;

    public static class Category {

        /**
         * 分类编号
         */
        private Integer id;
        /**
         * 分类名称
         */
        private String name;

        // ... 省略 setting/getting 方法


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // ... 省略 setting/getting 方法

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

