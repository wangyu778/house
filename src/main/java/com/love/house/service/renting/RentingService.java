package com.love.house.service.renting;

import com.love.house.common.ServerResponse;
import com.love.house.model.PageProperties;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @Date: 2021/3/9 15:41
 * @Description: 租房逻辑层
 */
@Service
public interface RentingService {

    /**
     * 获取房屋list
     * @param properties 传参对象
     * @return 结果list
     */
    public PageProperties getHouseList(PageProperties properties);

    /**
     * 加入收藏
     * @param roomId 房间Id
     * @return 结果
     */
    public ServerResponse<String> collectionHouse(int roomId);

}
