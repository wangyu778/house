package com.love.house.mapper.mysqlMapper;

import com.love.house.entity.HouseRoomUser;

public interface HouseRoomUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseRoomUser record);

    int insertSelective(HouseRoomUser record);

    HouseRoomUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(HouseRoomUser record);

    int updateByPrimaryKey(HouseRoomUser record);
}