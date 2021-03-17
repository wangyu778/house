package com.love.house.service.renting.impl;

import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseCollection;
import com.love.house.entity.HouseFood;
import com.love.house.entity.HouseFoodDiscount;
import com.love.house.entity.HouseRoom;
import com.love.house.mapper.mysqlMapper.HouseCollectionMapper;
import com.love.house.mapper.mysqlMapper.HouseRoomMapper;
import com.love.house.model.Constant;
import com.love.house.model.PageProperties;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.renting.RentingService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wy
 * @Date: 2021/3/9 15:43
 * @Description: 租房实现类
 */
@Service
public class RentingServiceImpl implements RentingService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HouseRoomMapper houseRoomMapper;
    @Resource
    private HouseCollectionMapper houseCollectionMapper;
    @Resource
    private BaseService baseService;

    @Override
    public PageProperties getHouseList(PageProperties properties) {
        Map<String, Object> filterMap = new HashMap<>(7);
        if(ObjectUtils.isNotEmpty(properties.getRoomDirection())){
            filterMap.put("roomDirection", properties.getRoomDirection());
        }
        if(ObjectUtils.isNotEmpty(properties.getRoomNumber())){
            filterMap.put("roomNumber", properties.getRoomNumber());
        }
        if(ObjectUtils.isNotEmpty(properties.getRoomLocation())){
            filterMap.put("roomLocation", properties.getRoomLocation());
        }
        if(ObjectUtils.isNotEmpty(properties.getRoomType())){
            filterMap.put("roomType", properties.getRoomType());
        }
        if(ObjectUtils.isNotEmpty(properties.getRoomPrice())){
            switch (properties.getRoomPrice()){
                case 1 :
                    filterMap.put("beginPrice", 0);
                    filterMap.put("endPrice", 1000);
                    break;
                case 2:
                    filterMap.put("beginPrice", 1000);
                    filterMap.put("endPrice", 2000);
                    break;
                case 3:
                    filterMap.put("beginPrice", 2000);
                    filterMap.put("endPrice", 5000);
                    break;
                case 4:
                    filterMap.put("beginPrice", 5000);
                    break;
                default: break;
            }
        }
        List<HouseRoom> houseList = houseRoomMapper.getHouseList(filterMap);
        for (HouseRoom houseRoom : houseList) {
            houseRoom.setUser(houseRoomMapper.getUser(houseRoom.getRoomId()));
        }
        filterMap.put("collectionType", Constant.houseCollectionType);
        for (HouseRoom houseRoom : houseList) {
            filterMap.put("collectionId", houseRoom.getRoomId());
            filterMap.put("userId", baseService.getUserId());
            if(ObjectUtils.isNotEmpty(houseCollectionMapper.getCollection(filterMap))){
                houseRoom.setIsCollection(1);
            }
        }
        properties.setList(houseList);
        return properties;
    }

    @Override
    public ServerResponse<String> collectionHouse(int roomId) {
        try {
            HouseCollection collection = new HouseCollection();
            collection.setUserId(baseService.getUserId());
            collection.setCollectionId(roomId);
            collection.setCollectionType(Constant.houseCollectionType);
            houseCollectionMapper.insert(collection);
            return ServerResponse.createBySuccessMessage("保存成功");
        } catch (Exception e) {
            log.error("[租房-加入收藏]-将"+roomId+"加入收藏失败",e);
            return ServerResponse.createByErrorMessage("保存失败");
        }
    }

}
