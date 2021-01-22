package com.love.house.mapper;

import com.love.house.entity.HouseRoom;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author wy
 * @date 2021/1/19 18:49
 */
public interface HouseRoomMapper {

    /**
     * 删除房间
     * @param roomId id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer roomId);

    /**
     * 新建房间
     * @param record room
     * @return 结果
     */
    int insertSelective(HouseRoom record);

    /**
     * 获取房间信息
     * @param filterMap map
     * @return HouseRoom
     */
    HouseRoom getHouseRoom(@Param(value = "filterMap") Map<String,Object> filterMap);

    /**
     * 更新房间信息
     * @param record room
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(HouseRoom record);
}