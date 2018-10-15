package com.fangzhi.yao.fzcms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fangzhi.yao.fzcms.entity.Client;
import com.fangzhi.yao.fzcms.mapper.ClientMapper;
import com.fangzhi.yao.fzcms.service.IClientService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-08
 */
@Service(version = "1.0.0", timeout = 60000)
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

}
