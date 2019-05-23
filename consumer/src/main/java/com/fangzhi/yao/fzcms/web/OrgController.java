package com.fangzhi.yao.fzcms.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fangzhi.yao.fzcms.code.OrgType;
import com.fangzhi.yao.fzcms.dto.EnumInfo;
import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.entity.Org;
import com.fangzhi.yao.fzcms.service.IOrgService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 组织表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-24
 */
@Controller
@RequestMapping("/org")
public class OrgController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0", check = false)
    IOrgService iOrgService;

    @RequestMapping("/*")
    public void toHtml() {

    }

    @RequestMapping("/listData")
    @RequiresPermissions("org:view")
    public @ResponseBody
    ResultInfo<List<Org>> listData() {
        return new ResultInfo<>(iOrgService.getAllOrgs());
    }

    @RequestMapping("/save")
    @RequiresPermissions(value = {"org:add", "org:edit"}, logical = Logical.OR)
    public @ResponseBody
    ResultInfo<Boolean> save(Org org) {
        return new ResultInfo<>(iOrgService.saveOrg(org));
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("org:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr) {
        return new ResultInfo<>(iOrgService.delBatchOrg(Arrays.asList(idArr)));
    }

    @RequestMapping("/typeSelectData")
    public @ResponseBody
    ResultInfo<List<EnumInfo>> typeSelectData() {
        return new ResultInfo<>(OrgType.getAllEnumInfo());
    }


    @RequestMapping("/parentSelectData")
    public @ResponseBody
    ResultInfo<List<Org>> parentSelectData(String sysOrgType) {
        List<Org> list = new ArrayList<>();
        if (sysOrgType.equals(OrgType.COMPANY.getCode())) {
            return new ResultInfo<>(list);
        } else if (sysOrgType.equals(OrgType.DEPARTMENT.getCode())) {
            List<Org> allOrgs = iOrgService.getAllOrgs();
            for (Org p : allOrgs) {
                if (p.getSysOrgType().equals(Integer.valueOf(OrgType.COMPANY.getCode()))) {
                    list.add(p);
                }
            }
        } else if (sysOrgType.equals(OrgType.TEAM.getCode())) {
            List<Org> allOrgs = iOrgService.getAllOrgs();
            for (Org p : allOrgs) {
                if (p.getSysOrgType().equals(Integer.valueOf(OrgType.DEPARTMENT.getCode()))) {
                    list.add(p);
                }
            }
        }
        return new ResultInfo<>(list);
    }
}

