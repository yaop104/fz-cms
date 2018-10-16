package com.fangzhi.yao.fzcms.service;

import com.fangzhi.yao.fzcms.dto.third.SmsMessage;
import com.fangzhi.yao.fzcms.entity.SendMessage;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 发送表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-16
 */
public interface ISendMessageService extends IService<SendMessage> {

    void sendMessage(SmsMessage smsMessage);

    String selectLastCode(SendMessage sendMessage);

}
