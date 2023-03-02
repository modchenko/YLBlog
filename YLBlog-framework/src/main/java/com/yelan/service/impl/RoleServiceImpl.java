package com.yelan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yelan.domain.entity.Role;
import com.yelan.mapper.RoleMapper;
import com.yelan.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 18:30:17
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
