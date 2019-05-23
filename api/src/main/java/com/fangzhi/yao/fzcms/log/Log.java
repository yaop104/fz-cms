package com.fangzhi.yao.fzcms.log;

import org.slf4j.Logger;

/**
 * 日志接口
 * Created by yao
 */
public interface Log extends Logger {

    void debug(Object o);

    void info(Object o);

    void warn(Object o);

    void error(Object o);

    void warn(Throwable t);

    void error(Throwable t);

    void error(String format, Throwable t, Object... objects);

    void error(String format, Object o1, Throwable t);

    void error(String format, Object o1, Object o2, Throwable t);

    void warn(String format, Throwable t, Object... objects);

    void warn(String format, Object o1, Throwable t);

    void warn(String format, Object o1, Object o2, Throwable t);
}
