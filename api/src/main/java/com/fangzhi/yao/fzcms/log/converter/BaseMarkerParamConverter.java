package com.fangzhi.yao.fzcms.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.fangzhi.yao.fzcms.log.marker.BaseMarker;
import org.slf4j.Marker;

/**
 * Created by yao
 */
public abstract class BaseMarkerParamConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        Marker marker = event.getMarker();
        if (marker instanceof BaseMarker) {
            BaseMarker baseMarker = (BaseMarker) event.getMarker();
            return baseMarker.getParam(getParamName());
        }
        return getParamName();
    }

    public abstract String getParamName();
}
