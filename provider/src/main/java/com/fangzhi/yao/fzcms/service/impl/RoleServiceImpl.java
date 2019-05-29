package com.fangzhi.yao.fzcms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fangzhi.yao.fzcms.entity.Role;
import com.fangzhi.yao.fzcms.mapper.RoleMapper;
import com.fangzhi.yao.fzcms.service.IRoleService;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Service(version = "1.0.0", timeout = 60000)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public Boolean saveRole(Role role) {
        Boolean res = false;
        if (role.getId() == null) {
            res = this.insert(role);
        } else {
            res = this.updateById(role);
        }
        return res;
    }
}