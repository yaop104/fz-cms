package com.fangzhi.yao.fzcms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yaoping
 * @date 2018/10/16 PM2:24
 */
@Configuration
@PropertySource("classpath:config/config.properties")
@ConfigurationProperties(prefix = "iot")
public class IotConf {
    private String iot;
}
