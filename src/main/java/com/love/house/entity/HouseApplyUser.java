package com.love.house.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * house_apply_user
 * @author 
 */
public class HouseApplyUser implements Serializable {
    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 维修名称 维修位置名称
     */
    private Integer roomId;

    private Date applyDate;

    private Integer isSolve;

    private Date solveDate;

    private String userId;

    private User user;

    private HouseRoom houseRoom;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getIsSolve() {
        return isSolve;
    }

    public void setIsSolve(Integer isSolve) {
        this.isSolve = isSolve;
    }

    public Date getSolveDate() {
        return solveDate;
    }

    public void setSolveDate(Date solveDate) {
        this.solveDate = solveDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HouseRoom getHouseRoom() {
        return houseRoom;
    }

    public void setHouseRoom(HouseRoom houseRoom) {
        this.houseRoom = houseRoom;
    }
}