package com.love.house.service.manage.impl;

import com.love.house.common.ServerResponse;
import com.love.house.entity.*;
import com.love.house.mapper.mysqlMapper.*;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.manage.ManageService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wy
 * @Date: 2021/3/12 17:26
 * @Description:
 */
@Service
public class ManageServiceImpl implements ManageService {

    private static final Logger LOG = LoggerFactory.getLogger(ManageServiceImpl.class);

    @Resource
    private BaseService baseService;
    @Resource
    private HouseRoomMapper houseRoomMapper;
    @Resource
    private HouseFoodMapper foodMapper;
    @Resource
    private HouseFoodDiscountMapper discountMapper;
    @Resource
    private HouseRepairMapper repairMapper;
    @Resource
    private HouseApplyUserMapper applyUserMapper;
    @Resource
    private UserMapping userMapper;

    @Override
    public ServerResponse<String> newHouse(HouseRoom houseRoom) {
        try {
            houseRoom.setCreateDate(new Date());
            houseRoom.setCreateUser(baseService.getUserId());
            houseRoom.setIsLease(0);
            houseRoom.setIsRepair(0);
            houseRoomMapper.insertSelective(houseRoom);
            if(LOG.isInfoEnabled()){
                LOG.info("create room success, createUser : {}, createRoom info:{}", baseService.getUserId(), houseRoom.toString());
            }
            return ServerResponse.createBySuccessMessage("创建成功");
        } catch (Exception e) {
            LOG.error("create room error, createUser : {}, createRoom info:{}", baseService.getUserId(), houseRoom.toString(), e);
            return ServerResponse.createByErrorMessage("创建失败");
        }

    }

    @Override
    public HouseRoom getHouse(Integer roomId) {
        return houseRoomMapper.selectByPrimaryKey(roomId);
    }

    @Override
    public ServerResponse<String> deleteHouse(Integer roomId) {
        try {
            houseRoomMapper.deleteByPrimaryKey(roomId);
            return ServerResponse.createBySuccessMessage("删除成功");
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public ServerResponse<String> updateHouse(HouseRoom houseRoom) {
        try {
            houseRoom.setUpdateUser(baseService.getUserId());
            houseRoom.setUpdateDate(new Date());
            houseRoomMapper.updateByPrimaryKeySelective(houseRoom);
            return ServerResponse.createBySuccessMessage("修改成功");
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }

    @Override
    public ServerResponse<String> deleteFood(Integer foodId) {
        try {
            foodMapper.deleteByPrimaryKey(foodId);
            discountMapper.deleteByFoodId(foodId);
            return ServerResponse.createBySuccessMessage("删除成功");
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public ServerResponse<String> newFood(HouseFood houseFood) {
        try {
            houseFood.setCreateUser(baseService.getUserId());
            houseFood.setCreateDate(new Date());
            foodMapper.insertSelective(houseFood);
            if(ObjectUtils.isNotEmpty(houseFood.getId()) && ObjectUtils.isNotEmpty(houseFood.getFoodName())){
                HouseFoodDiscount foodDiscount = new HouseFoodDiscount();
                foodDiscount.setFoodId(houseFood.getId());
                foodDiscount.setMoney(houseFood.getMoney());
                foodDiscount.setFoodName(houseFood.getFoodName());
                discountMapper.insertSelective(foodDiscount);
            }
            return ServerResponse.createBySuccessMessage("新建商家成功");
        } catch (Exception e) {
            LOG.error("新建商家失败",e);
            return ServerResponse.createByErrorMessage("新建商家失败");
        }
    }

    @Override
    public HouseFood getFood(Integer foodId) {
        HouseFood houseFood = foodMapper.getHouseFood(foodId);
        Map<String, Object> filterMap = new HashMap<>(2);
        filterMap.put("foodId",foodId);
        houseFood.setFoodDiscounts(discountMapper.getList(filterMap));
        return houseFood;
    }

    @Override
    public ServerResponse<String> deleteDiscount(Integer id) {
        try {
            discountMapper.deleteByKey(id);
            return ServerResponse.createBySuccessMessage("删除优惠信息成功");
        } catch (Exception e) {
            LOG.error("删除优惠信息失败",e);
            return ServerResponse.createByErrorMessage("删除优惠信息失败");
        }
    }

    @Override
    public List<HouseRepair> getRepairList() {
        return repairMapper.getList();
    }

    @Override
    public ServerResponse<String> updateRepair(Integer id) {
         try {
            repairMapper.updateByPrimaryKey(id, new Date());
            return ServerResponse.createBySuccessMessage("修改报修信息成功");
         } catch (Exception e) {
            LOG.error("修改报修信息失败",e);
            return ServerResponse.createByErrorMessage("修改报修信息失败");
         }
    }

    @Override
    public ServerResponse<String> applyHouse(Integer roomId) {
        try {
            HouseApplyUser houseApplyUser = new HouseApplyUser();
            houseApplyUser.setApplyDate(new Date());
            houseApplyUser.setIsSolve(0);
            houseApplyUser.setRoomId(roomId);
            houseApplyUser.setUserId(baseService.getUserId());
            applyUserMapper.insertSelective(houseApplyUser);
            return ServerResponse.createBySuccessMessage("申请成功");
        } catch (Exception e) {
            LOG.error("申请失败",e);
            return ServerResponse.createByErrorMessage("申请失败");
        }
    }

    @Override
    public List<HouseApplyUser> getApplyList() {
        List<HouseApplyUser> applyList = applyUserMapper.getApplyList();
        for (HouseApplyUser houseApplyUser : applyList) {
            houseApplyUser.setUser(userMapper.getUser(houseApplyUser.getUserId()));
            houseApplyUser.setHouseRoom(houseRoomMapper.selectByPrimaryKey(houseApplyUser.getRoomId()));
        }
        return applyList;
    }

    @Override
    public ServerResponse<String> updateApply(Integer id) {
        try {
            HouseApplyUser houseApplyUser = new HouseApplyUser();
            houseApplyUser.setId(id);
            houseApplyUser.setIsSolve(1);
            houseApplyUser.setSolveDate(new Date());
            applyUserMapper.updateByPrimaryKeySelective(houseApplyUser);
            return ServerResponse.createBySuccessMessage("修改成功");
        } catch (Exception e) {
            LOG.error("修改失败",e);
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }


}
