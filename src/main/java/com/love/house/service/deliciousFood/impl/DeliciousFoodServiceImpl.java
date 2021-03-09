package com.love.house.service.deliciousFood.impl;

import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseCollection;
import com.love.house.entity.HouseFood;
import com.love.house.entity.HouseFoodDiscount;
import com.love.house.mapper.mysqlMapper.HouseCollectionMapper;
import com.love.house.mapper.mysqlMapper.HouseFoodDiscountMapper;
import com.love.house.mapper.mysqlMapper.HouseFoodMapper;
import com.love.house.model.Constant;
import com.love.house.model.PageProperties;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.deliciousFood.DeliciousFoodService;
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
 * @Date: 2021/2/26 15:35
 * @Description: 没事推荐逻辑层实现类
 */
@Service
public class DeliciousFoodServiceImpl implements DeliciousFoodService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HouseFoodMapper houseFoodMapper;
    @Resource
    private HouseFoodDiscountMapper foodDiscountMapper;
    @Resource
    private HouseCollectionMapper houseCollectionMapper;
    @Resource
    private BaseService baseService;

    @Override
    public PageProperties getFoodList(PageProperties properties) {
        Map<String, Object> filterMap = new HashMap<>(6);
        if(properties.getSortType() > 1){
            switch (properties.getSortType()){
                case 2: filterMap.put("sortField","saleCount");break;
                case 3: filterMap.put("sortField","businessDistance");break;
                case 4: filterMap.put("sortField","businessScore");break;
                default:break;
            }
        }
        filterMap.put("businessType", properties.getBusinessType());
        filterMap.put("userId", baseService.getUserId());
        filterMap.put("collectionType", Constant.foodCollectionType);
        List<HouseFood> list = houseFoodMapper.getList(filterMap);
        for (HouseFood houseFood : list) {
            filterMap.put("foodId",houseFood.getId());
            filterMap.put("collectionId",houseFood.getId());
            List<HouseFoodDiscount> foodDiscountMapperList = foodDiscountMapper.getList(filterMap);
            houseFood.setFoodDiscounts(foodDiscountMapperList);
            if(ObjectUtils.isNotEmpty(houseCollectionMapper.getCollection(filterMap))){
                houseFood.setIsCollection(1);
            }
        }
        properties.setList(list);
        return properties;
    }

    @Override
    public ServerResponse<String> collectionFood(int foodId) {
        try {
            HouseCollection collection = new HouseCollection();
            collection.setUserId(baseService.getUserId());
            collection.setCollectionId(foodId);
            collection.setCollectionType(Constant.foodCollectionType);
            houseCollectionMapper.insert(collection);
            return ServerResponse.createBySuccessMessage("保存成功");
        } catch (Exception e) {
            log.error("[美食推荐-加入收藏]-将"+foodId+"加入收藏失败",e);
            return ServerResponse.createByErrorMessage("保存失败");
        }
    }

}
