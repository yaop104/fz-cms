package com.fangzhi.yao.fzcms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import com.fangzhi.yao.fzcms.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    UserInfo findUserInfo(String userName);

}
