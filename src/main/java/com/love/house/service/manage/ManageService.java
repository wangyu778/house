package com.love.house.service.manage;

import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseRoom;
import org.springframework.stereotype.Service;

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

}
