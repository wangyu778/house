package com.love.house.mapper.mysqlMapper;

import com.love.house.entity.HouseFoodDiscount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wy
 * @date 2021/3/3
 */
public interface HouseFoodDiscountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseFoodDiscount record);

    int insertSelective(HouseFoodDiscount record);

    HouseFoodDiscount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseFoodDiscount record);

    int updateByPrimaryKey(HouseFoodDiscount record);

    List<HouseFoodDiscount> getList(@Param("filterMap") Map<String, Object> filterMap);
}