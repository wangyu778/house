package com.love.house.service.manage;

import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseApplyUser;
import com.love.house.entity.HouseFood;
import com.love.house.entity.HouseRepair;
import com.love.house.entity.HouseRoom;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: wy
 * @Date: 2021/3/12 17:25
 * @Description: 管理逻辑层
 */
@Service
public interface ManageService {

    /**
     * 新建house
     * @param houseRoom houseRoom
     * @return 结果
     */
    public ServerResponse<String> newHouse(HouseRoom houseRoom);

    /**
     * 获取房屋信息
     * @param roomId roomId
     * @return houseRoom
     */
    public HouseRoom getHouse(Integer roomId);

    /**
     * 删除房屋
     * @param roomId roomId
     * @return 结果
     */
    public ServerResponse<String> deleteHouse(Integer roomId);

    /**
     * 修改房屋
     * @param houseRoom houseRoom
     * @return 结果
     */
    public ServerResponse<String> updateHouse(HouseRoom houseRoom);

    /**
     * 删除房屋
     * @param foodId foodId
     * @return 结果
     */
    public ServerResponse<String> deleteFood(Integer foodId);

    /**
     * 新增美食商家
     * @param houseFood houseFood
     * @return 结果
     */
    public ServerResponse<String> newFood(HouseFood houseFood);


    /**
     * 获取商家信息
     * @param foodId foodId
     * @return houseFood
     */
    public HouseFood getFood(Integer foodId);

    /**
     * 删除房屋
     * @param id id
     * @return 结果
     */
    public ServerResponse<String> deleteDiscount(Integer id);

    /**
     * 获取报修列表
     * @return list
     */
    public List<HouseRepair> getRepairList();

    /**
     * 修改报修
     * @param id id
     * @return 结果
     */
    public ServerResponse<String> updateRepair(Integer id);

    /**
     * 申请看房
     * @param roomId roomId
     * @return 结果
     */
    public ServerResponse<String> applyHouse(Integer roomId);

    /**
     * 获取申请看房列表
     * @return list
     */
    public List<HouseApplyUser> getApplyList();

    /**
     * 修改申请看房信息
     * @param id id
     * @return 结果
     */
    public ServerResponse<String> updateApply(Integer id);

    /**
     * 保存房屋图片
     * @param headImg 房屋图片
     * @param roomNumber 房间号
     * @return 结果
     */
    public ServerResponse<String> saveRoomImg(MultipartFile headImg, String roomNumber);

    /**
     * 保存商家图片
     * @param headImg 商家图片
     * @param foodId 商家Id
     * @return 结果
     */
    public ServerResponse<String> saveFoodImg(MultipartFile headImg, String foodId);

}
