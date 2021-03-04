package com.love.house.mapper.mysqlMapper;

import com.love.house.entity.HouseFood;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: wy
 * @Date: 2021/1/26
 */
@Repository
public interface HouseFoodMapper {

    /**
     * 删除
     * @param id id
     * @return 结果
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record 新增内容
     * @return 结果
     */
    int insertSelective(HouseFood record);

    /**
     * 更新
     * @param record 更新内容
     * @return 结果
     */
    int updateByPrimaryKeySelective(HouseFood record);

    /**
     * 获取列表
     * @param filterMap 条件
     * @return list
     */
    List<HouseFood> getList(@Param(value = "filterMap") Map<String,Object> filterMap);

}