package com.love.house.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wy
 * @Date: 2021/2/26 15:47
 * @Description: 分页参数类
 */
public class PageProperties implements Serializable {

    /**页面列表*/
    private List<?> list;

    /**
     * 美食排序字段
     * 1、综合排序
     * 2、销量排序
     * 3、距离排序
     * 4、评分排序
     */
    private int sortType;

    /**
     * 品类
     */
    private int businessType;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }
}
