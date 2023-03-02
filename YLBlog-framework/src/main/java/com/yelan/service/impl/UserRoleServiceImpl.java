package com.yelan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yelan.domain.entity.UserRole;
import com.yelan.service.UserRoleService;
import com.yelan.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 18:30:45
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
