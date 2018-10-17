package com.fangzhi.yao.fzcms.thirdparty.push;

import com.fangzhi.yao.fzcms.dto.third.PushMessageDTO;

/**
 * @author yaoping
 * @date 2018/10/17 PM11:54
 */
public interface PushService {
    int sendPush(PushMessageDTO pushMessageDTO);
}
