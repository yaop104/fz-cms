package com.fangzhi.yao.fzcms.mapper;

import com.fangzhi.yao.fzcms.entity.SendMessage;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 发送表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-16
 */
public interface SendMessageMapper extends BaseMapper<SendMessage> {

    SendMessage selectLastCode(SendMessage sendMessage);
}
