package com.fangzhi.yao.fzcms.openapi.process;

import com.fangzhi.yao.fzcms.util.Config;
import com.fangzhi.yao.fzcms.util.SignatureUtils;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yaoping
 * @date 2019-11-03 21:58
 */
public class SignProcess {
    // 1.需求修改Config.java中的AccessKey信息
    // 2."最终signature"才是你需要的签名最终结果
    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        // 公共参数
        map.put("Format", "JSON");
        map.put("Version", "2018-01-20");
        map.put("AccessKeyId", Config.accessKey);
        map.put("SignatureMethod", "HMAC-SHA1");
        map.put("Timestamp", LocalDateTime.now().toInstant(ZoneOffset.UTC).toString());
        map.put("SignatureVersion", "1.0");
        map.put("SignatureNonce", "1533023037");
        map.put("RegionId", "cn-shanghai");
        // 请求参数
        map.put("Action", "GetDeviceStatus");
        map.put("DeviceName", "1JK5FRFegiCSPlUF7lW3");
        map.put("ProductKey", "a18uZMRHmAa");
        try {
            String signature = SignatureUtils.generate("GET", map, Config.accessKeySecret);
            System.out.println("最终signature：" + signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
