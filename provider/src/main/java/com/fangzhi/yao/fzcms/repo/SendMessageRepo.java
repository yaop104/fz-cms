package com.fangzhi.yao.fzcms.repo;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fangzhi.yao.fzcms.entity.SendMessage;
import com.fangzhi.yao.fzcms.mapper.SendMessageMapper;
import org.springframework.stereotype.Service;

/**
 * @author yaoping
 * @date 2018/10/16 PM6:06
 */
@Service
public class SendMessageRepo extends ServiceImpl<SendMessageMapper, SendMessage> {
    public String selectLastCode(SendMessage sendMessage) {
        String message="0";
        try {
            sendMessage = this.baseMapper.selectLastCode(sendMessage);
            if(sendMessage!=null){
                sendMessage.setFzSendState("1");
                updateById(sendMessage);

                message = "1";
            }
            return message;
        } catch (Exception e) {
            return message;
        }
    }
}
