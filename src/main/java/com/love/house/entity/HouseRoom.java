package com.love.house.entity;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

/**
 * house_room
 * @author 
 */
@Repository
public class HouseRoom implements Serializable {
    /**
     * 主键
     */
    private Integer roomId;

    /**
     * 房间号
     */
    private Integer roomNumber;

    /**
     * 房间大小
     */
    private Integer roomSize;

    /**
     * 房间图片
     */
    private String roomImage;

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

    /**
     * 是否出租 0、否；1、是
     */
    private Integer isLease;

    /**
     * 是否报修 0、否；1、是
     */
    private Integer isRepair;

    /**
     * 出租时间
     */
    private Date leaseDate;

    /**
     * 报修日期
     */
    private Date repairDate;

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

    public Integer getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Integer roomSize) {
        this.roomSize = roomSize;
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

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }
}