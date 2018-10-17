package com.fangzhi.yao.fzcms.config.third;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yaoping
 * @date 2018/10/16 PM2:24
 */
@Configuration
@PropertySource("classpath:config/push.properties")
@ConfigurationProperties(prefix = "push.jiguang")
public class FzPush {
    private String appKey;
    private String masterSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }
}
