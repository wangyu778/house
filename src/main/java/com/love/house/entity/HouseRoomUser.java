package com.love.house.entity;

import java.io.Serializable;

/**
 * house_room_user
 * @author 
 */
public class HouseRoomUser implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 房间Id
     */
    private Integer roomId;

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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}