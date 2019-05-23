package com.fangzhi.yao.fzcms.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import com.fangzhi.yao.fzcms.context.DJContext;
import com.fangzhi.yao.fzcms.generator.SpanIdGenerator;
import com.fangzhi.yao.fzcms.log.marker.BaseMarker;
import com.fangzhi.yao.fzcms.log.marker.ParamName;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;

/**
 * 日志抽象类
 * Created by yao
 */
public abstract class AbstractLog implements Log {

    private String FQNC;
    private String name;
    protected Logger logger;

    public AbstractLog(String logName) {
        this.name = logName;
        this.logger = (Logger) LoggerFactory.getLogger(logName);
        this.FQNC = this.getClass().getName();
    }

    public AbstractLog(Class clazz) {
        this.name = clazz.getName();
        this.logger = (Logger) LoggerFactory.getLogger(clazz);
        this.FQNC = this.getClass().getName();
    }

    protected void log(Level level, String message, Throwable t, Object... objects) {
        Marker baseMarker = initMarker();
        LoggingEvent loggingEvent = new LoggingEvent(FQNC, logger, level, message, t, objects);
        loggingEvent.setMarker(baseMarker);
        logger.callAppenders(loggingEvent);
    }

    protected void log(Marker marker, Level level, String message, Throwable t, Object... objects) {
        Marker baseMarker = initMarker();
        baseMarker.add(marker);
        LoggingEvent loggingEvent = new LoggingEvent(FQNC, logger, level, message, t, objects);
        loggingEvent.setMarker(baseMarker);
        logger.callAppenders(loggingEvent);
    }

    // 定制点,额外字段可重写此方法
    private Marker initMarker() {
        BaseMarker marker = new BaseMarker();
        String uniqueId = MDC.get(ParamName.TRACE_ID) != null ? MDC.get(ParamName.TRACE_ID) : DJContext.getUniqueID();
        String spanId = MDC.get(ParamName.SPAN_ID) != null ? MDC.get(ParamName.SPAN_ID) : DJContext.getSpanID();
        marker.addParam(ParamName.TRACE_ID, uniqueId == null ? "non_unique_id" : uniqueId);
        marker.addParam(ParamName.SPAN_ID, spanId == null ? "non_span_id" : SpanIdGenerator.trimSpan(spanId));
        return marker;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
