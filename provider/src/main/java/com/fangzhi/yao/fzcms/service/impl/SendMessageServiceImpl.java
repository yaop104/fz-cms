package com.fangzhi.yao.fzcms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fangzhi.yao.fzcms.dto.third.SmsMessage;
import com.fangzhi.yao.fzcms.entity.SendMessage;
import com.fangzhi.yao.fzcms.mapper.SendMessageMapper;
import com.fangzhi.yao.fzcms.repo.SendMessageRepo;
import com.fangzhi.yao.fzcms.service.ISendMessageService;
import com.fangzhi.yao.fzcms.thirdparty.qiniu.impl.QiniuServiceImpl;
import com.fangzhi.yao.fzcms.thirdparty.sms.impl.SendMessageImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 发送表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-16
 */
@Service(version = "1.0.0", timeout = 60000)
public class SendMessageServiceImpl extends ServiceImpl<SendMessageMapper, SendMessage> implements ISendMessageService {
    @Autowired
    private SendMessageImpl smsSendMessageService;
    @Autowired
    QiniuServiceImpl qiniuService;
    @Autowired
    SendMessageRepo sendMessageRepo;

    @Override
    public void sendMessage(SmsMessage smsMessage) {
        smsSendMessageService.sendMessage(smsMessage);
    }

    @Override
    public String selectLastCode(SendMessage sendMessage) {
        return sendMessageRepo.selectLastCode(sendMessage);
    }

    @Override
    public String getSimpleUploadToken() {
        return qiniuService.getSimpleUploadToken();
    }
}
