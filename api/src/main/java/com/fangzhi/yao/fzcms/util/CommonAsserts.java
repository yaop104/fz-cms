package com.fangzhi.yao.fzcms.util;

import java.util.Collection;
import java.util.Map;

import static com.fangzhi.yao.fzcms.util.StringUtils.isEmpty;


/**
 *
 * Created by yao on 2017/5/11.
 */
public class CommonAsserts {
    public static void assertTrue(boolean expr, String code, String args) {
        if (!expr){
            ExceptionUtil.throwException(code, args);
        }
    }

    public static void assertFalse(boolean expr, String code, String args) {
        assertTrue(!expr, code, args);
    }

    public static void assertNotNull(Object o, String code, String args) {
        assertTrue(o != null, code, args);
    }

    public static void assertNull(Object o, String code, String args) {
        assertTrue(o == null, code, args);
    }

    private static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        }

        return isEquals(expected, actual);
    }

    private static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }

    // TODO override all types for assertEquals

    public static void assertEquals(Object expected, Object actual, String code, String args) {
        assertTrue(equalsRegardingNull(expected, actual), code, args);
    }

    public static void assertNotEquals(Object expected, Object actual, String code, String args) {
        assertFalse(equalsRegardingNull(expected, actual), code, args);
    }

    public static void assertNotEmpty(String str, String code, String args) {
        assertFalse(isEmpty(str), code, args);
    }

    public static void assertNotEmpty(Collection<?> collection, String code, String args) {
        assertFalse(isEmpty(collection), code, args);
    }

    public static <K, V> void assertNotEmpty(Map<K, V> map, String code, String args) {
        assertFalse(isEmpty(map), code, args);
    }

    public static void assertEmpty(String str, String code, String args) {
        assertTrue(isEmpty(str), code, args);
    }

    public static void assertEmpty(Collection<?> collection, String code, String args) {
        assertTrue(isEmpty(collection), code, args);
    }

    public static <K, V> void assertEmpty(Map<K, V> map, String code, String args) {
        assertTrue(isEmpty(map), code, args);
    }

    public static void assertContain(Collection<?> collection, Object key, String code, String args) {
        assertTrue(collection.contains(key), code, args);
    }

    public static void assertNotContain(Collection<?> collection, Object key, String code, String args) {
        assertTrue(!collection.contains(key), code, args);
    }

    public static void assertContain(Map<?, ?> map, Object key, String code, String args) {
        assertTrue(map.containsKey(key), code, args);
    }

    public static void assertNotContain(Map<?, ?> map, Object key, String code, String args) {
        assertTrue(!map.containsKey(key), code, args);
    }
}
