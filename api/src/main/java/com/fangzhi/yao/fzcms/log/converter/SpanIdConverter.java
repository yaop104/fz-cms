package com.fangzhi.yao.fzcms.log.converter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fangzhi.yao.fzcms.generator.SpanIdGenerator;
import com.fangzhi.yao.fzcms.log.marker.ParamName;

/**
 * Created by yao
 */
public class SpanIdConverter extends BaseMarkerParamConverter {
    @Override
    public String convert(ILoggingEvent event) {
        String spanId = super.convert(event);
        return SpanIdGenerator.trimSpan(spanId);
    }

    @Override
    public String getParamName() {
        return ParamName.SPAN_ID;
    }
}
