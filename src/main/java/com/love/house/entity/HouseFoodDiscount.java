package com.love.house.entity;

import java.io.Serializable;

/**
 * house_food_discount
 * @author 
 */
public class HouseFoodDiscount implements Serializable {
    /**
     * 主键Id 主键Id
     */
    private Integer id;

    /**
     * 美食Id 美食Id
     */
    private Integer foodId;

    /**
     * 价格 没事价格
     */
    private Integer money;

    /**
     * 美食名称 美食名称
     */
    private String foodName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}