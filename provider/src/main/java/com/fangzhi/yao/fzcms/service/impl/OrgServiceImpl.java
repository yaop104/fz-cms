package com.fangzhi.yao.fzcms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fangzhi.yao.fzcms.entity.Org;
import com.fangzhi.yao.fzcms.ex.BusinessException;
import com.fangzhi.yao.fzcms.mapper.OrgMapper;
import com.fangzhi.yao.fzcms.service.IOrgService;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

import static com.fangzhi.yao.fzcms.util.StringUtils.getRandomNumber;

/**
 * <p>
 * 组织表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-24
 */
@Service(version = "1.0.0", timeout = 60000)
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

    @Cacheable("orgCache")
    @Override
    public List<Org> getAllOrgs() {
        List<Org> orgs = this.baseMapper.selectList(new EntityWrapper<>());
        return orgs;
    }

    @CacheEvict(value = "orgCache", allEntries = true)
    @Override
    public Boolean saveOrg(Org org) {
        Boolean res = false;
        if (org.getSysOrgId()==null) {
            if(StringUtils.isBlank(org.getSysOrgCode())){
                //生成code
                String radomCode = buildUnUseCode(org);
                if(StringUtils.isBlank(radomCode)){
                    throw new BusinessException("1", "组织代码生成异常!");
                }
                org.setSysOrgCode(radomCode);
            }
            res = this.insert(org);
        } else {
            res = this.updateById(org);
        }
        return res;
    }

    //TODO 需要改成公司区分
    private String buildUnUseCode(Org org) {
        String checkCode = "";
        if(org.getSysOrgType() == 1){
            org.setSysOrgPcode("0");
        }
        EntityWrapper<Org> orgWrapper = new EntityWrapper<>();
        orgWrapper.eq("sys_org_type", org.getSysOrgType());
        orgWrapper.eq("sys_org_pcode", org.getSysOrgPcode());
        Integer count = this.baseMapper.selectCount(orgWrapper);
        if(count > 999){
            throw new BusinessException("1", "超过组织最大999个数量！");
        }
        int i = 0;
        while(i < 9999){
            String code = getRandomNumber(3);
            checkCode = org.getSysOrgPcode() + code;

            EntityWrapper<Org> codeWrapper = new EntityWrapper<>();
            codeWrapper.eq("sys_org_code", checkCode);
            Integer countCode = this.baseMapper.selectCount(codeWrapper);
            if(countCode < 1){
                break;
            }
            i++;
        }
        return checkCode;
    }

    @CacheEvict(value = "orgCache", allEntries = true)
    @Override
    public Boolean delBatchOrg(List<Integer> ids) {
        Boolean res = false;
        //目录和菜单只能单个删除
        if(ids.size() == 1){
            Org org = this.selectById(ids.get(0));
            Org con = new Org();
            con.setSysOrgPcode(org.getSysOrgCode());
            List<Org> list = this.baseMapper.selectList(new EntityWrapper<>(con));
            if(list!=null&&list.size()>0){
                throw new BusinessException("1", "有子组织不能删除！");
            }
            res = this.deleteById(ids.get(0));
        } else {
            res = this.baseMapper.deleteBatchIds(ids) > 0;
        }
        return res;
    }
}
