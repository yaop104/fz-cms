package com.fangzhi.yao.fzcms.config;

import org.crazycake.shiro.RedisManager;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
public class CustomRedisManager extends RedisManager {

}