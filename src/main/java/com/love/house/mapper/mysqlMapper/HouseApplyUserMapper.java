package com.love.house.mapper.mysqlMapper;

import com.love.house.entity.HouseApplyUser;

import java.util.List;

public interface HouseApplyUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseApplyUser record);

    int insertSelective(HouseApplyUser record);

    HouseApplyUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(HouseApplyUser record);

    int updateByPrimaryKey(HouseApplyUser record);

    List<HouseApplyUser> getApplyList();
}