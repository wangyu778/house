package com.love.house.mapper.mysqlMapper;

import com.love.house.entity.HouseRepair;

import java.util.Date;
import java.util.List;

public interface HouseRepairMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseRepair record);

    int insertSelective(HouseRepair record);

    HouseRepair selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseRepair record);

    int updateByPrimaryKey(Integer id, Date solveDate);

    List<HouseRepair> getListByUserId(String userId);

    List<HouseRepair> getList();
}