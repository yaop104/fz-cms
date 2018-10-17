package com.fangzhi.yao.fzcms.thirdparty.qiniu.impl;

import com.fangzhi.yao.fzcms.config.third.FzQiniu;
import com.fangzhi.yao.fzcms.thirdparty.qiniu.QiniuPicService;
import com.qiniu.util.Auth;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yaoping
 * @date 2018/10/17 PM2:15
 */
@Service
@Log4j
public class QiniuServiceImpl implements QiniuPicService {

    @Autowired
    FzQiniu fzQiniu;

    @Override
    public String getSimpleUploadToken() {
        String accessKey = fzQiniu.getAccessKey();
        String secretKey = fzQiniu.getSecretKey();
        String bucket = fzQiniu.getBucket();

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }
}
