package com.fangzhi.yao.fzcms.dto.token;

import java.io.Serializable;

/**
 * @author yaoping
 * @date 2018/10/10 AM11:38
 */
public class PrintAccessToken implements Serializable{
    private static final long serialVersionUID = -142000539144474910L;

    private String accessToken;
    private Long   expireTime;

    public PrintAccessToken() {
    }

    public PrintAccessToken(String accessToken, Long expireTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
