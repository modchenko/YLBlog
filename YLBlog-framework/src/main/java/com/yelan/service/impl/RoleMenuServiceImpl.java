package com.yelan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yelan.domain.entity.RoleMenu;
import com.yelan.mapper.RoleMenuMapper;
import com.yelan.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-03-01 18:30:24
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
