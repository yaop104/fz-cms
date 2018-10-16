package com.fangzhi.yao.fzcms.validator.user;

import com.fangzhi.yao.fzcms.dto.user.UserInfoDTO;
import com.fangzhi.yao.fzcms.util.CommonAsserts;

/**
 * @author yaoping
 * @date 2018/10/16 PM7:51
 */
public class UserValidator {

    public static void userInfoDTOExists(UserInfoDTO userInfoDTO){
        CommonAsserts.assertNotNull(userInfoDTO, "1", "非法参数！");
    }
    public static void userInfoDTOParamCode(UserInfoDTO userInfoDTO){
        userInfoDTOExists(userInfoDTO);
        CommonAsserts.assertNotEmpty(userInfoDTO.getSendCode(), "1", "验证码参数缺失！");
        CommonAsserts.assertNotEmpty(userInfoDTO.getUsername(), "1", "用户名参数缺失！");
    }
    public static void userInfoDTOParam(UserInfoDTO userInfoDTO){
        userInfoDTOExists(userInfoDTO);
        CommonAsserts.assertNotEmpty(userInfoDTO.getPassword(), "1", "密码参数缺失！");
        CommonAsserts.assertNotEmpty(userInfoDTO.getUsername(), "1", "用户名参数缺失！");
    }

}
