package com.fangzhi.yao.fzcms.service;

import com.fangzhi.yao.fzcms.entity.Org;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 组织表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-24
 */
public interface IOrgService extends IService<Org> {

    List<Org> getAllOrgs();

    Boolean saveOrg(Org org);

    Boolean delBatchOrg(List<Integer> integers);
}
