package com.love.house.service.manage.impl;

import com.love.house.common.ServerResponse;
import com.love.house.entity.*;
import com.love.house.mapper.mysqlMapper.*;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.manage.ManageService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Resource
    private HouseRoomUserMapper roomUserMapper;

    @Override
    public ServerResponse<String> newHouse(HouseRoom houseRoom) {
        try {
            Map<String, Object> filterMap = new HashMap<>();
            filterMap.put("roomNumber",houseRoom.getRoomNumber());
            if(houseRoomMapper.getHouseList(filterMap).size() > 0){
                return ServerResponse.createByErrorMessage("已有该房间号,请输入新的房间号！");
            }
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
        HouseRoom houseRoom = houseRoomMapper.selectByPrimaryKey(roomId);
        houseRoom.setUserList(userMapper.getListUser());
        houseRoom.setUser(houseRoomMapper.getUser(houseRoom.getRoomId()));
        return houseRoom;
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
            if(ObjectUtils.isNotEmpty(houseRoom.getUserId())){
                houseRoom.setLeaseDate(new Date());
                HouseRoomUser houseRoomUser = new HouseRoomUser();
                houseRoomUser.setRoomId(houseRoom.getRoomId());
                houseRoomUser.setUserId(houseRoom.getUserId());
                roomUserMapper.insertSelective(houseRoomUser);
            }
            houseRoomMapper.updateByPrimaryKeySelective(houseRoom);
            return ServerResponse.createBySuccessMessage("修改成功");
        } catch (Exception e) {
            LOG.error("修改失败",e);
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
            return ServerResponse.createBySuccess(houseFood.getId().toString());
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

    @Override
    public ServerResponse<String> saveRoomImg(MultipartFile headImg, String roomNumber) {
        String imgPath = null;
        try {
            imgPath = ResourceUtils.getURL("classpath:").getPath()+"/static/image/roomImg/";
            File imgFile = new File(imgPath+roomNumber+".jpg");
            headImg.transferTo(imgFile);
            HouseRoom houseRoom = new HouseRoom();
            houseRoom.setRoomNumber(Integer.parseInt(roomNumber));
            houseRoom.setRoomImage("/static/image/roomImg/"+roomNumber+".jpg");
            houseRoomMapper.updateByPrimaryKey(houseRoom);
            return ServerResponse.createBySuccessMessage("上传图片成功");
        } catch (Exception e) {
            LOG.error("上传图片失败",e);
            return ServerResponse.createByErrorMessage("上传图片失败");
        }
    }

    @Override
    public ServerResponse<String> saveFoodImg(MultipartFile headImg, String foodId) {
        String imgPath = null;
        try {
            imgPath = ResourceUtils.getURL("classpath:").getPath()+"/static/image/foodImg/";
            File imgFile = new File(imgPath+foodId+".jpg");
            headImg.transferTo(imgFile);
            HouseFood houseFood = new HouseFood();
            houseFood.setId(Integer.parseInt(foodId));
            houseFood.setBusinessImg("/static/image/foodImg/"+foodId+".jpg");
            foodMapper.updateByPrimaryKeySelective(houseFood);
            return ServerResponse.createBySuccessMessage("上传图片成功");
        } catch (Exception e) {
            LOG.error("上传图片失败",e);
            return ServerResponse.createByErrorMessage("上传图片失败");
        }
    }

}
