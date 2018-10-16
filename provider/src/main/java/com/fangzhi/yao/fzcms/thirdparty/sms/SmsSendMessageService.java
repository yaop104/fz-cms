package com.fangzhi.yao.fzcms.thirdparty.sms;

import com.fangzhi.yao.fzcms.dto.third.SmsMessage;

/**
 * @author yaoping
 * @date 2018/10/16 PM12:38
 */
public interface SmsSendMessageService {

    void sendMessage(SmsMessage smsMessage);


}
