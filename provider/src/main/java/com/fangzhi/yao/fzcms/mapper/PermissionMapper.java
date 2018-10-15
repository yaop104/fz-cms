package com.fangzhi.yao.fzcms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fangzhi.yao.fzcms.dto.PermissionInfo;
import com.fangzhi.yao.fzcms.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 系统权限表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionInfo> allPermissionInfo();

}
