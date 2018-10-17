package com.fangzhi.yao.fzcms.thirdparty.push.impl;

import com.fangzhi.yao.fzcms.config.third.FzPush;
import com.fangzhi.yao.fzcms.dto.third.PushMessageDTO;
import com.fangzhi.yao.fzcms.thirdparty.push.JpushClientUtil;
import com.fangzhi.yao.fzcms.thirdparty.push.PushService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yaoping
 * @date 2018/10/18 AM12:02
 */
@Service
@Log4j
public class JpushServiceImpl implements PushService{

    @Autowired
    FzPush fzPush;

    @Override
    public int sendPush(PushMessageDTO pushMessageDTO) {
        int flag = JpushClientUtil.sendToAlias(
                fzPush.getAppKey(),
                fzPush.getMasterSecret(),
                pushMessageDTO.getAlias(),
                pushMessageDTO.getNotificationTitle(),
                pushMessageDTO.getMsgTitle(),
                pushMessageDTO.getMsgContent(),
                pushMessageDTO.getExtrasParam()
        );
        return flag;
    }
}
