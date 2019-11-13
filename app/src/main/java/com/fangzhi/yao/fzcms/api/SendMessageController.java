package com.fangzhi.yao.fzcms.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.dto.third.SmsMessage;
import com.fangzhi.yao.fzcms.ex.BusinessException;
import com.fangzhi.yao.fzcms.service.ISendMessageService;
import com.fangzhi.yao.fzcms.util.RegexValidateUtil;
import com.fangzhi.yao.fzcms.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 发送表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-16
 */
@RestController
@RequestMapping("/sendMessage")
public class SendMessageController extends BaseController{

    @Reference(version = "1.0.0", check = false)
    private ISendMessageService iSendMessageService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/getCode")
    public ResultInfo getCode(@RequestBody SmsMessage smsMessage){
        try {
            //检查数据完整性
            if(!RegexValidateUtil.checkMobileNumber(smsMessage.getPhone())){
                throw new BusinessException("-1", "手机格式不正确,请重新输入");
            }

            String code = StringUtils.getRandomNumber(4);
            smsMessage.setSmsCode(code);

            iSendMessageService.sendMessage(smsMessage);

            return  respMessage("1", "发送成功，稍后验证码将发送至手机！");

        } catch (Exception e) {
            logger.error( "getCode-获取验证码错误：",  e.getMessage());
            throw new BusinessException("-1", "失败，系统异常，稍后再试！");
        }
    }
}

