package com.love.house.mapper;

import com.love.house.entity.HouseCollection;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface HouseCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseCollection record);

    int insertSelective(HouseCollection record);

    HouseCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseCollection record);

    int updateByPrimaryKey(HouseCollection record);

    HouseCollection getCollection(@Param(value = "filterMap")Map<String, Object> filterMap);
}