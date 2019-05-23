package com.fangzhi.yao.fzcms.generator;

/**
 * Created by yao
 */
public class SpanIdGenerator {
    /**
     * 初始spanId
     *
     * @return
     */
    public static String initSpanId() {
        return "1.0";
    }

    /**
     * span 新增一级,以0开头
     *
     * @param spanId
     * @return
     */
    public static String formatSpan(String spanId) {
        if (spanId == null || spanId.endsWith(".0")) {
            return spanId;
        }
        return spanId + ".0";
    }

    /**
     * 格式化span,将预留的下一级0去掉
     *
     * @param spanId
     * @return
     */
    public static String trimSpan(String spanId) {
        if (spanId == null || !spanId.endsWith(".0")) {
            return spanId;
        }
        return spanId.substring(0, spanId.lastIndexOf("."));
    }

    /**
     * span递增
     *
     * @param spanId
     * @return
     */
    public static String increaseSpan(String spanId) {
        if (spanId == null) {
            return null;
        }

        String fatherSpan = spanId.substring(0, spanId.lastIndexOf(".") + 1);
        String currentSpan = spanId.substring(spanId.lastIndexOf(".") + 1);
        String nexSpan = String.valueOf(Integer.parseInt(currentSpan) + 1);
        return fatherSpan + nexSpan;
    }
}
