package com.love.house.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * house_food
 * @author 
 */
public class HouseFood implements Serializable {
    /**
     * 主键Id 主键Id
     */
    private Integer id;

    /**
     * 商家名称 商家名称
     */
    private String businessName;

    /**
     * 商家图片
     */
    private String businessImg;

    /**
     * 位置信息 商家位置信息
     */
    private String businessLocation;

    /**
     * 距离 商家的距离
     */
    private Integer businessDistance;

    /**
     * 评分 评分
     */
    private BigDecimal businessScore;

    /**
     * 商家品类 1、美食，2、甜点饮品，3、超市便利，4生鲜果蔬，5：药品
     */
    private Integer businessType;

    /**
     * 销售总量 销售量
     */
    private Integer saleCount;

    /**
     * 简介 简介
     */
    private String introduction;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 是否被该用户收藏 0、否，1、是
     */
    private int isCollection;

    /**
     * 优惠政策
     */
    private List<HouseFoodDiscount> foodDiscounts;

    /**
     * 价格 没事价格
     */
    private Integer money;

    /**
     * 美食名称 美食名称
     */
    private String foodName;

    private static final long serialVersionUID = 1L;

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLocation() {
        return businessLocation;
    }

    public String getBusinessImg() {
        return businessImg;
    }

    public void setBusinessImg(String businessImg) {
        this.businessImg = businessImg;
    }

    public void setBusinessLocation(String businessLocation) {
        this.businessLocation = businessLocation;
    }

    public Integer getBusinessDistance() {
        return businessDistance;
    }

    public void setBusinessDistance(Integer businessDistance) {
        this.businessDistance = businessDistance;
    }

    public BigDecimal getBusinessScore() {
        return businessScore;
    }

    public void setBusinessScore(BigDecimal businessScore) {
        this.businessScore = businessScore;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public List<HouseFoodDiscount> getFoodDiscounts() {
        return foodDiscounts;
    }

    public void setFoodDiscounts(List<HouseFoodDiscount> foodDiscounts) {
        this.foodDiscounts = foodDiscounts;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}