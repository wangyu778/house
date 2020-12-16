package com.love.house.service.mine;

import com.love.house.common.ResponseCode;
import com.love.house.common.ServerResponse;
import com.love.house.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @Date: 2020/12/10 11:01
 * @Description: 我的逻辑层
 */
@Service(value = "MineService")
public interface MineService {

    /**
     * 保存用户
     * @param user user
     * @return 保存结果
     */
    public ServerResponse<ResponseCode> saveUser(User user);

}
