package com.yelan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yelan.domain.entity.User;
import com.yelan.domain.result.ResponseResult;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-03-01 18:30:35
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult register(User user);

    ResponseResult updateUserInfo(User user);
}
