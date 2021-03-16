package com.love.house.service.mine.impl;

import com.love.house.common.ResponseCode;
import com.love.house.common.ServerResponse;
import com.love.house.entity.*;
import com.love.house.mapper.mysqlMapper.*;
import com.love.house.model.Constant;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.mine.MineService;
import com.love.house.utils.Md5;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: wy
 * @Date: 2020/12/10 11:02
 * @Description: 我的逻辑层实现类
 */
@Service
public class MineServiceImpl implements MineService {

    @Resource
    private UserMapping userMapping;
    @Resource
    private BaseService baseService;
    @Resource
    private HouseCollectionMapper collectionMapper;
    @Resource
    private HouseFoodMapper houseFoodMapper;
    @Resource
    private HouseRoomMapper houseRoomMapper;
    @Resource
    private HouseRepairMapper repairMapper;
    @Resource
    private HouseApplyUserMapper applyUserMapper;

    @Override
    public ServerResponse<ResponseCode> saveUser(User user) {
        try {
            Md5.stringToMd51(user.getPassword());
            user.setPassWord(Md5.stringToMd51(user.getPassword()));
            user.setCreateDate(new Date());
            user.setIsLock(0);
            userMapping.insertSelective(user);
            return ServerResponse.createBySuccessMessage("新建用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("新建用户失败");
        }
    }

    @Override
    public HouseRoom getRentalDetails() {
        HouseRoom houseRoom = houseRoomMapper.selectByUserId(baseService.getUserId());
        houseRoom.setHouseApplyUser(applyUserMapper.selectByPrimaryKey(baseService.getUserId()));
        return houseRoom;
    }

    @Override
    public List<HouseCollection> getCollectionList() {
        List<HouseCollection> collectionList = collectionMapper.getCollectionList(baseService.getUserId());
        for (HouseCollection collection : collectionList) {
            if(collection.getCollectionType().equals(Constant.foodCollectionType)){
                HouseFood houseFood = houseFoodMapper.getHouseFood(collection.getCollectionId());
                if(ObjectUtils.isNotEmpty(houseFood)){
                    collection.setHouseFood(houseFood);
                }
            }
            if(collection.getCollectionType().equals(Constant.houseCollectionType)){
                HouseRoom houseRoom = houseRoomMapper.selectByPrimaryKey(collection.getCollectionId());
                if(ObjectUtils.isNotEmpty(houseRoom)){
                    collection.setHouseRoom(houseRoom);
                }
            }
        }
        return collectionList;
    }

    @Override
    public ServerResponse<String> newRepair(HouseRepair repairInfo) {
        try {
            repairInfo.setIsSolve(0);
            repairInfo.setRepairDate(new Date());
            repairInfo.setUserId(baseService.getUserId());
            repairMapper.insertSelective(repairInfo);
            return ServerResponse.createBySuccessMessage("新建报修成功");
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("新建报修失败");
        }
    }

    @Override
    public List<HouseRepair> getRepairList() {
        return repairMapper.getListByUserId(baseService.getUserId());
    }

}
