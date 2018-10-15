package com.fangzhi.yao.fzcms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import com.fangzhi.yao.fzcms.entity.User;
import com.fangzhi.yao.fzcms.mapper.UserMapper;
import com.fangzhi.yao.fzcms.service.IUserService;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Service(version = "1.0.0", timeout = 60000)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserInfo findUserInfo(String userName) {
        return this.baseMapper.findUserInfo(userName);
    }
}