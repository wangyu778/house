package com.love.house.mapper.mysqlMapper;

import com.love.house.entity.HouseRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: wy
 * @Date: 2021/3/9
 */
public interface HouseRoomMapper {
    int deleteByPrimaryKey(Integer roomId);

    int insert(HouseRoom record);

    int insertSelective(HouseRoom record);

    HouseRoom selectByPrimaryKey(Integer roomId);

    int updateByPrimaryKeySelective(HouseRoom record);

    int updateByPrimaryKey(HouseRoom record);

    /**
     * 获取房屋list
     * @param filterMap 条件
     * @return list
     */
    List<HouseRoom> getHouseList(@Param(value = "filterMap") Map<String, Object> filterMap);

}