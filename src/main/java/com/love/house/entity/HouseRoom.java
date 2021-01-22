package com.love.house.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * house_room
 * @author wy
 * @date 2021/1/19 18:47
 */
public class HouseRoom implements Serializable {
    private Integer roomId;

    /**
     * 房间号
     */
    private Integer roomNumber;

    /**
     * 房间图片
     */
    private String roomImage;

    /**
     * 价格/月
     */
    private Integer roomPrice;

    /**
     * 房屋类型 1、普通房；2、豪华精装；3、家庭套房
     */
    private Integer roomType;

    /**
     * 是否出租 0：否、1：是
     */
    private Integer isLease;

    /**
     * 是否维修中 0：否，1、是
     */
    private Integer isRepair;

    /**
     * 出租时间
     */
    private Date leaseDate;

    /**
     * 截至租房日期
     */
    private Date toLeaseDate;

    /**
     * 抱修日期
     */
    private Date repairDate;

    private String createUser;

    /**
     * 创建时间
     */
    private Date createDate;

    private String updateUser;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Integer getIsLease() {
        return isLease;
    }

    public void setIsLease(Integer isLease) {
        this.isLease = isLease;
    }

    public Integer getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Integer isRepair) {
        this.isRepair = isRepair;
    }

    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    public Date getToLeaseDate() {
        return toLeaseDate;
    }

    public void setToLeaseDate(Date toLeaseDate) {
        this.toLeaseDate = toLeaseDate;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
}