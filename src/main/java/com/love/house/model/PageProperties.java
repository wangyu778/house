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

    /**
     * 房间价格/月
     */
    private Integer roomPrice;

    /**
     * 房屋朝向 1：东，2：南，3：西，4：北
     */
    private Integer roomDirection;

    /**
     * 几单元
     */
    private Integer roomLocation;

    /**
     * 房间类型 1、普通房型；2、豪华精装；3、家庭套房
     */
    private Integer roomType;

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

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomDirection() {
        return roomDirection;
    }

    public void setRoomDirection(Integer roomDirection) {
        this.roomDirection = roomDirection;
    }

    public Integer getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(Integer roomLocation) {
        this.roomLocation = roomLocation;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }
}
