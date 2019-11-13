package com.fangzhi.yao.fzcms.util;

/**
 * @author yaoping
 * @date 2019-11-10 08:09
 */
public class StringFixUtil {
    /**
     * 左补位,右对齐
     * @param oriStr 原字符串
     * @param len 目标字符串长度
     * @param alexin 补位字符
     * @return 目标字符串
     */
    public static String padLeft(String oriStr,int len, String alexin){
        String str = "";
        for (int i = 0; i < len; i++) {
            str = str + alexin;
        }
        str = oriStr + str;
        return str;
    }
}
