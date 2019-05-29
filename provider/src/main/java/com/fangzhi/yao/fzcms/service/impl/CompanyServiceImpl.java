package com.fangzhi.yao.fzcms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fangzhi.yao.fzcms.entity.Company;
import com.fangzhi.yao.fzcms.mapper.CompanyMapper;
import com.fangzhi.yao.fzcms.service.ICompanyService;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-22
 */
@Service(version = "1.0.0", timeout = 60000)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
