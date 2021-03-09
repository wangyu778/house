package com.love.house.service.deliciousFood;

import com.love.house.common.ServerResponse;
import com.love.house.model.PageProperties;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @Date: 2021/2/26 15:34
 * @Description: 周边美食推荐逻辑层
 */
@Service
public interface DeliciousFoodService {

    /**
     * 获取美食数据
     * @param properties 分页对象
     * @return list
     */
    public PageProperties getFoodList(PageProperties properties);

    /**
     * 加入收藏
     * @param foodId 美食Id
     * @return 结果
     */
    public ServerResponse<String> collectionFood(int foodId);

}
