package com.fangzhi.yao.fzcms.api;

import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected UserInfo getUserInfo(){
        return (UserInfo)SecurityUtils.getSubject().getPrincipal();
    }

    protected boolean getUserLoginStatus(){
        Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated();
    }
    /**
     * 字符串转换成JSON
     *
     * @param code
     * @param message
     * @return
     */
    protected ResultInfo respMessage(String code, String message, Object data) {
        ResultInfo json = new ResultInfo();
        json.setCode(code);
        json.setMsg(message);
        json.setData(data);
        return json;
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

    /**
     * 字符串转换成JSON
     *
     * @param data
     * @return
     */
    protected ResultInfo resOk( Object data) {
        return this.respMessage("1","", data);
    }

    /**
     * 字符串转换成JSON
     *
     * @return
     */
    protected ResultInfo resOk() {
        return this.respMessage("1","", null);
    }


}