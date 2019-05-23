package com.fangzhi.yao.fzcms.log.converter;


import com.fangzhi.yao.fzcms.log.marker.ParamName;

/**
 * Created by yao
 */
public class TraceIdConverter extends BaseMarkerParamConverter {
    @Override
    public String getParamName() {
        return ParamName.TRACE_ID;
    }
}
