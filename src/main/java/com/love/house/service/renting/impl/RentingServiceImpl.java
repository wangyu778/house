package com.love.house.service.renting.impl;

import com.love.house.entity.HouseRoom;
import com.love.house.mapper.mysqlMapper.HouseRoomMapper;
import com.love.house.model.PageProperties;
import com.love.house.service.renting.RentingService;
import org.apache.commons.lang3.ObjectUtils;
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

    @Resource
    private HouseRoomMapper houseRoomMapper;

    @Override
    public PageProperties getHouseList(PageProperties properties) {
        Map<String, Object> filterMap = new HashMap<>(7);
        if(ObjectUtils.isNotEmpty(properties.getRoomDirection())){
            filterMap.put("roomDirection", properties.getRoomDirection());
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
        properties.setList(houseList);
        return properties;
    }

}
