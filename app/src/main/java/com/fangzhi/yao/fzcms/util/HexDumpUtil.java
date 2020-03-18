package com.fangzhi.yao.fzcms.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yaoping
 * @date 2020-03-01 15:05
 */
public class HexDumpUtil {
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        // toUpperCase将字符串中的所有字符转换为大写
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        // toCharArray将此字符串转换为一个新的字符数组。
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    //charToByte返回在指定字符的第一个发生的字符串中的索引，即返回匹配字符
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex ;
        }
        return result;
    }

    //两位一字符，倒序排序
    public static String reverseString(String str) {

        List<String> strlist=new ArrayList();

        char[] chr = str.toCharArray();

        for (int i = 0 ; i < chr.length; i=i+2) {

            String s=chr[i]+""+chr[i+1];

            strlist.add(s);

        }
        Collections.reverse(strlist);

        String result="";

        for(String v:strlist){

            result+=v;
        }
        return result;

    }

    /**
     * 16进制转换成为string类型字符串
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    /**
     * 字符串转化成为16进制字符串
     * @param s
     * @return
     */
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    //将16进制字符串自动补全到8位 并且倒序排序
    public static String full8(String lenth) {

        int a = lenth.getBytes().length;

        int b = 8 - a;

        for (int i = 0; i < b; i++) {

            lenth = "0" + lenth;

        }

        return reverseString(lenth);
    }

    /**
     * xor运算
     *
     * @param data
     * @return
     */
    public static String getBCC(byte[] data) {

        String ret = "";
        byte BCC[] = new byte[1];
        for (int i = 0; i < data.length; i++) {
            BCC[0] = (byte) (BCC[0] ^ data[i]);
        }
        String hex = Integer.toHexString(BCC[0] & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        ret += hex.toUpperCase();
        return ret;
    }

    /**
     * 字符串转换为16进制字符串
     *
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f; //位于运算
            sb.append(chars[bit]); //进行字符串的拼接
        }
        return sb.toString();
    }

    /**
     * 16进制字符串转换为字符串
     *
     * @param s
     * @return
     */

    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }


    public static void main(String[] args) {
        String str =   "00012000000000000000000000000000000000000000000035";
        System.out.println("\n***** Convert to Hex *****");
        String string = hexStr2Str("1");

        System.out.println(str2HexStr(str));
        System.out.println(string);
    }
}
