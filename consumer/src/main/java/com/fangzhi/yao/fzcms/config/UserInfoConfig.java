package com.fangzhi.yao.fzcms.config;

import com.fangzhi.yao.fzcms.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaoping
 * @date 2019/5/24 14:58
 */
@Configuration
public class UserInfoConfig{
    @Reference(version = "1.0.0", check = false)
    private IUserService iUserService;

    @Bean(name = "iUserService")
    public IUserService getIUserService(){
        return iUserService;
    }
}
