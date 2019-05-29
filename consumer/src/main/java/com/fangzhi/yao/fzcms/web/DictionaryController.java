package com.fangzhi.yao.fzcms.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.entity.Dictionary;
import com.fangzhi.yao.fzcms.service.IDictionaryService;
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
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "1.0.0", check = false)
    IDictionaryService iDictionaryService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("dictionary:view")
    public @ResponseBody
    ResultInfo<List<Dictionary>> listData(Dictionary modelDTO, Integer page, Integer limit){
        EntityWrapper<Dictionary> wrapper = new EntityWrapper<>(modelDTO);
        if(modelDTO!=null&&modelDTO.getDicType()!=null){
            wrapper.eq("dic_type", modelDTO.getDicType());
            modelDTO.setDicType(null);
        }
        if(modelDTO!=null&&modelDTO.getDicName()!=null){
            wrapper.like("dic_name",modelDTO.getDicName());
            modelDTO.setDicName(null);
        }
        Page<Dictionary> pageObj = iDictionaryService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("dictionary:add")
    public @ResponseBody
    ResultInfo<Boolean> add(Dictionary modelDTO){
        boolean b = iDictionaryService.insert(modelDTO);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("dictionary:del")
    public @ResponseBody
    ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = iDictionaryService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("dictionary:edit")
    public @ResponseBody
    ResultInfo<Boolean> edit(Dictionary modelDTO){
        try {
            Dictionary dictionary = iDictionaryService.selectById(modelDTO.getDicId());
            dictionary.setDicName(modelDTO.getDicName());
            dictionary.setDicType(modelDTO.getDicType());
            dictionary.setDicParent(modelDTO.getDicParent());
            boolean b = iDictionaryService.updateById(dictionary);
            return new ResultInfo<>(b);
        }catch (Exception e){
            logger.error("dictionary:edit ", e);
            return respMessage("1", "系统异常！");
        }

    }

    @RequestMapping("/selectListData")
    @ResponseBody
    public ResultInfo<List<Dictionary>> selectListData(Dictionary modelDTO){
        List<Dictionary> list = iDictionaryService.selectList(new EntityWrapper<>(modelDTO));
        return new ResultInfo<>(list);
    }
}

