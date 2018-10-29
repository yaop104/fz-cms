package com.fangzhi.yao.fzcms.service.impl;

import com.fangzhi.yao.fzcms.entity.User;
import com.fangzhi.yao.fzcms.mapper.UserMapper;
import com.fangzhi.yao.fzcms.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-29
 */
@Service(version = "1.0.0", timeout = 60000)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
