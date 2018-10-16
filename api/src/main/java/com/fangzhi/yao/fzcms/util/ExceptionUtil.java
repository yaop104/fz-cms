package com.fangzhi.yao.fzcms.util;

import com.fangzhi.yao.fzcms.ex.BusinessException;

/**
 * @author yaoping
 * @date 2018/10/16 PM8:02
 */
public class ExceptionUtil {
    public static void throwException(String code, String args) {
        throw new BusinessException(code, args);
    }
}
