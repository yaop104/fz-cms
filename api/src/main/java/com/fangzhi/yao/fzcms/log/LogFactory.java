package com.fangzhi.yao.fzcms.log;

/**
 * 日志工程
 * Created by yao
 */
public class LogFactory {

    public static Log getLog(Class clazz) {
        return new TracedLog(clazz);
    }

    public static Log getLog(String logName) {
        return new TracedLog(logName);
    }
}
