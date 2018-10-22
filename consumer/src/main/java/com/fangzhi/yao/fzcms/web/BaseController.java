package com.fangzhi.yao.fzcms.web;

import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected UserInfo getUserInfo(){
        return (UserInfo)SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 字符串转换成JSON
     *
     * @param code
     * @param message
     * @return
     */
    protected ResultInfo respMessage(String code, String message) {
        ResultInfo json = new ResultInfo();
        json.setCode(code);
        json.setMsg(message);
        return json;
    }
}