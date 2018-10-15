package com.fangzhi.yao.fzcms.service;

import com.fangzhi.yao.fzcms.dto.UserInfo;
import com.fangzhi.yao.fzcms.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface IUserService extends IService<User> {

    UserInfo findUserInfo(String userName);

}
