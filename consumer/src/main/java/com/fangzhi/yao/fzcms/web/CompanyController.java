package com.fangzhi.yao.fzcms.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.entity.Company;
import com.fangzhi.yao.fzcms.service.ICompanyService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-22
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0", check = false)
    private ICompanyService iCompanyService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("company:view")
    public @ResponseBody
    ResultInfo<List<Company>> listData(Company company, Integer page, Integer limit){
        EntityWrapper<Company> wrapper = new EntityWrapper<>(company);
        if(company!=null&&company.getFzCompanyName()!=null){
            wrapper.like("fz_company_name", company.getFzCompanyName());
            company.setFzCompanyName(null);
        }
        if(company!=null&&company.getFzCompanyPerson()!=null){
            wrapper.like("fz_company_name",company.getFzCompanyPerson());
            company.setFzCompanyPerson(null);
        }
        if(company!=null&&company.getFzCompanyMobile()!=null){
            wrapper.like("fz_company_mobile",company.getFzCompanyMobile());
            company.setFzCompanyMobile(null);
        }
        if(company!=null&&company.getFzCompanyLimitTime()!=null){
            wrapper.le("fz_company_limit_time",company.getFzCompanyLimitTime());
            company.setFzCompanyLimitTime(null);
        }
        Page<Company> pageObj = iCompanyService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("company:add")
    public @ResponseBody
    ResultInfo<Boolean> add(Company company){
        boolean b = iCompanyService.insert(company);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("company:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = iCompanyService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("company:edit")
    public @ResponseBody
    ResultInfo<Boolean> edit(Company company){
        try {
            Company company1 = iCompanyService.selectById(company.getFzCompanyCode());
            company1.setFzCompanyName(company.getFzCompanyName());
            company1.setFzCompanyLimitTime(company.getFzCompanyLimitTime());
            company1.setFzCompanyMobile(company.getFzCompanyMobile());
            company1.setFzCompanyPerson(company.getFzCompanyPerson());
            company1.setFzCompanyAddress(company.getFzCompanyAddress());
            company1.setFzCompanyStatus(company.getFzCompanyStatus());
            boolean b = iCompanyService.updateById(company1);
            return new ResultInfo<>(b);
        }catch (Exception e){
            logger.error("company:edit ", e);
            return respMessage("1", "系统异常！");
        }

    }

}

