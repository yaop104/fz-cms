package com.fangzhi.yao.fzcms.openapi.process;

import com.fangzhi.yao.fzcms.util.StringFixUtil;

import java.io.UnsupportedEncodingException;

/**
 * @author yaoping
 * @date 2019-11-03 21:58
 */
public class StringFixProcess {
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            String head = StringFixUtil.padLeft("", 20, "O");
            String tail = StringFixUtil.padLeft("", 30, "0");
            String prefix = "00";
            System.out.println("最终String {}：" + prefix+head+prefix+tail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
