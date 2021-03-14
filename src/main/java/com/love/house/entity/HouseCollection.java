package com.love.house.entity;

import java.io.Serializable;
import java.util.List;

/**
 * house_collection
 * @author 
 */
public class HouseCollection implements Serializable {
    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 收藏人Id 人员Id
     */
    private String userId;

    /**
     * 收藏Id 被收藏的Id
     */
    private Integer collectionId;

    /**
     * 收藏类型 1、美食，2、房屋
     */
    private Integer collectionType;

    private HouseRoom houseRoom;

    private HouseFood houseFood;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(Integer collectionType) {
        this.collectionType = collectionType;
    }

    public HouseRoom getHouseRoom() {
        return houseRoom;
    }

    public void setHouseRoom(HouseRoom houseRoom) {
        this.houseRoom = houseRoom;
    }

    public HouseFood getHouseFood() {
        return houseFood;
    }

    public void setHouseFood(HouseFood houseFood) {
        this.houseFood = houseFood;
    }
}