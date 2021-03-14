package com.love.house.service.manage.impl;

import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseRoom;
import com.love.house.mapper.mysqlMapper.HouseRoomMapper;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.manage.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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

}
