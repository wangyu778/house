package com.love.house.mapper;

import com.love.house.entity.SecuritySysPermission;
import com.love.house.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 用户Dao
 * @Author: wy
 * @Date: 2020/11/3
 */
@Repository
public interface UserMapping {

    int deleteByPrimaryKey(int id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询数据库用户
     * @param userId 用户Id
     * @return 用户
     */
    User loadUserByUsername(String userId);

    /**
     * 获取所有的权限菜单
     * @return SecuritySysPermission
     */
    List<SecuritySysPermission> getAllPermission();
}