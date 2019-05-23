package com.fangzhi.yao.fzcms.log;

import com.google.gson.Gson;
import org.slf4j.Marker;

import static ch.qos.logback.classic.Level.*;

/**
 * 日志实现
 * Created by yao
 */
public class TracedLog extends AbstractLog {
    public static final Gson GSON = new Gson();

    public TracedLog(Class clazz) {
        super(clazz);
    }

    public TracedLog(String name) {
        super(name);
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        if (isTraceEnabled()) {
            log(TRACE, msg, null);
        }
    }

    @Override
    public void trace(String format, Object arg) {
        if (isTraceEnabled()) {
            log(TRACE, format, null, arg);
        }
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (isTraceEnabled()) {
            log(TRACE, format, null, arg1, arg2);
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (isTraceEnabled()) {
            log(TRACE, format, null, arguments);
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        if (isTraceEnabled()) {
            log(TRACE, msg, t);
        }
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        if (isTraceEnabled()) {
            log(marker, TRACE, msg, null);
        }
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        if (isTraceEnabled()) {
            log(marker, TRACE, format, null, arg);
        }
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        if (isTraceEnabled()) {
            log(marker, TRACE, format, null, arg1, arg2);
        }
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        if (isTraceEnabled()) {
            log(marker, TRACE, format, null, argArray);
        }
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        if (isTraceEnabled()) {
            log(marker, TRACE, msg, t);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        if (isDebugEnabled()) {
            log(DEBUG, msg, null);
        }
    }

    @Override
    public void debug(String format, Object arg) {
        if (isDebugEnabled()) {
            log(DEBUG, format, null, arg);
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (isDebugEnabled()) {
            log(DEBUG, format, null, arg1);
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (isDebugEnabled()) {
            log(DEBUG, format, null, arguments);
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (isDebugEnabled()) {
            log(DEBUG, msg, t);
        }
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        if (isDebugEnabled()) {
            log(DEBUG, msg, null);
        }
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        if (isDebugEnabled()) {
            log(marker, DEBUG, format, null, arg);
        }
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        if (isDebugEnabled()) {
            log(marker, DEBUG, format, null, arg1, arg2);
        }
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        if (isDebugEnabled()) {
            log(marker, DEBUG, format, null, arguments);
        }
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        if (isDebugEnabled()) {
            log(marker, DEBUG, msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        if (isInfoEnabled()) {
            log(INFO, msg, null);
        }
    }

    @Override
    public void info(String format, Object arg) {
        if (isInfoEnabled()) {
            log(INFO, format, null, arg);
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (isInfoEnabled()) {
            log(INFO, format, null, arg1, arg2);
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (isInfoEnabled()) {
            log(INFO, format, null, arguments);
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (isInfoEnabled()) {
            log(INFO, msg, t);
        }
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg) {
        if (isInfoEnabled()) {
            log(marker, INFO, msg, null);
        }
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        if (isInfoEnabled()) {
            log(marker, INFO, format, null, arg);
        }
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        if (isInfoEnabled()) {
            log(marker, INFO, format, null, arg1, arg2);
        }
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        if (isInfoEnabled()) {
            log(marker, INFO, format, null, arguments);
        }
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        if (isInfoEnabled()) {
            log(marker, INFO, msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        if (isWarnEnabled()) {
            log(WARN, msg, null);
        }
    }

    @Override
    public void warn(String format, Object arg) {
        if (isWarnEnabled()) {
            log(WARN, format, null, arg);
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (isWarnEnabled()) {
            log(WARN, format, null, arguments);
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (isWarnEnabled()) {

            if (arg1 instanceof Throwable) {
                log(WARN, format, (Throwable) arg1, arg2);
            } else {
                log(WARN, format, null, arg1, arg2);
            }
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (isWarnEnabled()) {
            log(WARN, msg, t);
        }
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg) {
        if (isWarnEnabled()) {
            log(marker, WARN, msg, null);
        }
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        if (isWarnEnabled()) {
            log(marker, WARN, format, null, arg);
        }
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        if (isWarnEnabled()) {
            log(marker, WARN, format, null, arg1, arg2);
        }
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        if (isWarnEnabled()) {
            log(marker, WARN, format, null, arguments);
        }
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        if (isWarnEnabled()) {
            log(marker, WARN, msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        if (isErrorEnabled()) {
            log(ERROR, msg, null);
        }
    }

    @Override
    public void error(String format, Object arg) {
        if (isErrorEnabled()) {
            log(ERROR, format, null, arg);
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (isErrorEnabled()) {
            if (arg1 instanceof Throwable) {
                log(ERROR, format, (Throwable) arg1, arg2);
            } else {
                log(ERROR, format, null, arg1, arg2);
            }
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (isErrorEnabled()) {
            log(ERROR, format, null, arguments);
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        if (isErrorEnabled()) {
            log(ERROR, msg, t);
        }
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg) {
        if (isErrorEnabled()) {
            log(marker, ERROR, msg, null);
        }
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        if (isErrorEnabled()) {
            log(marker, ERROR, format, null, arg);
        }
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        if (isErrorEnabled()) {
            log(marker, ERROR, format, null, arg1, arg2);
        }
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        if (isErrorEnabled()) {
            log(marker, ERROR, format, null, arguments);
        }
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        if (isErrorEnabled()) {
            log(marker, ERROR, msg, t);
        }
    }

    @Override
    public void debug(Object o) {
        if (isDebugEnabled()) {
            log(DEBUG, GSON.toJson(o), null);
        }
    }

    @Override
    public void info(Object o) {
        if (isInfoEnabled()) {
            log(INFO, GSON.toJson(o), null);
        }
    }

    @Override
    public void warn(Object o) {
        if (isWarnEnabled()) {
            log(WARN, GSON.toJson(o), null);
        }
    }

    @Override
    public void warn(Throwable e) {
        if (isWarnEnabled()) {
            log(WARN, null, e);
        }
    }

    @Override
    public void error(Object o) {
        if (isErrorEnabled()) {
            log(ERROR, GSON.toJson(o), null);
        }
    }

    @Override
    public void error(Throwable e) {
        if (isErrorEnabled()) {
            log(ERROR, null, e);
        }
    }


    @Override
    public void error(String format, Throwable t, Object... objects) {
        if (isErrorEnabled()) {
            log(ERROR, format, t, objects);
        }
    }

    @Override
    public void error(String format, Object o1, Throwable t) {
        if (isErrorEnabled()) {
            log(ERROR, format, t, o1);
        }
    }

    @Override
    public void error(String format, Object o1, Object o2, Throwable t) {
        if (isErrorEnabled()) {
            log(ERROR, format, t, o1, o2);
        }
    }

    @Override
    public void warn(String format, Throwable t, Object... objects) {
        if (isErrorEnabled()) {
            log(WARN, format, t, objects);
        }
    }

    @Override
    public void warn(String format, Object o1, Throwable t) {
        if (isWarnEnabled()) {
            log(WARN, format, t, o1);
        }

    }

    @Override
    public void warn(String format, Object o1, Object o2, Throwable t) {
        if (isWarnEnabled()) {
            log(WARN, format, t, o1, o2);
        }
    }
}
