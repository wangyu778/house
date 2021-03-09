package com.love.house.service.mine.impl;

import com.love.house.common.ResponseCode;
import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseRoom;
import com.love.house.entity.User;
import com.love.house.mapper.mysqlMapper.UserMapping;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.mine.MineService;
import com.love.house.utils.Md5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: wy
 * @Date: 2020/12/10 11:02
 * @Description: 我的逻辑层实现类
 */
@Service
public class MineServiceImpl implements MineService {

    @Resource
    private UserMapping userMapping;
    @Resource
    private BaseService baseService;

    @Override
    public ServerResponse<ResponseCode> saveUser(User user) {
        try {
            Md5.stringToMd51(user.getPassword());
            user.setPassWord(Md5.stringToMd51(user.getPassword()));
            user.setCreateDate(new Date());
            user.setIsLock(0);
            userMapping.insertSelective(user);
            return ServerResponse.createBySuccessMessage("新建用户成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("新建用户失败");
        }
    }

    @Override
    public HouseRoom getRentalDetails() {
//        Map<String,Object> filterMap = new HashMap<>(2);
//        filterMap.put("userId",baseService.getUserId());
//        return houseRoomMapper.getHouseRoom(filterMap);
        return new HouseRoom();
    }
//
//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>(5);
//        Collections.addAll(list,1,6,3,4,5);
//        list.sort((o1, o2) -> o1 - o2);
//        System.out.println(list);
//
//        int num = 10;
//
//        Consumer<String> consumer = ele -> {
//            System.out.println(num);
//        };
//
////        num = num + 2;
//        consumer.accept("hello");
//    }

}
