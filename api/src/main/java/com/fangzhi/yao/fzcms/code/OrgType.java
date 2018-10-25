package com.fangzhi.yao.fzcms.code;

import com.fangzhi.yao.fzcms.dto.EnumInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OrgType {

    COMPANY("1","公司"),
    DEPARTMENT("2","部门"),
    TEAM("3","小组");

    private String code;

    private String name;

    private OrgType(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static List<EnumInfo> getAllEnumInfo() {
        List<EnumInfo> list = new ArrayList<>();
        for (OrgType rt : Arrays.asList(OrgType.values())){
            list.add(new EnumInfo(rt.getCode(),rt.getName()));
        }
        return list;
    }

}