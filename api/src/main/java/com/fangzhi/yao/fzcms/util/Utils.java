package com.fangzhi.yao.fzcms.util;

import com.fangzhi.yao.fzcms.log.Log;
import com.fangzhi.yao.fzcms.log.LogFactory;
import org.slf4j.helpers.MessageFormatter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static final Log LOGGER = LogFactory.getLog(Utils.class);

    public static boolean isEmpty(String str) {
        return null == str || str.isEmpty();
    }

    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static String generateLikeStr(String str) {
        return isEmpty(str) ? null : "%" + str + "%";
    }

    // TODO 国际手机号校验
    public static boolean isMobile(String code, String mobile) {
        return true;
    }

    public static String getMethodName(String s) {
        StringBuilder str = new StringBuilder();
        str.append("set");
        str.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        return str.toString();
    }

    public static int calculateStrLength(String str) {
        int m = 0;
        for (char c : str.toCharArray()) {
            if (c >= 0x0391 && c <= 0xFFE5) {
                m = m + 1;
            } else if (c <= 0x00FF) {
                m = m + 1;
            }
        }
        return m;
    }

    public static long getCurrentMillis() {
        return System.currentTimeMillis();
    }


    public static Timestamp getNow() {
        return new Timestamp(getCurrentMillis());
    }

    public static String formatLikeLog4j(String formatter, Object... objects) {
        return MessageFormatter.arrayFormat(formatter, objects).getMessage();
    }

    public static <T> Map<String, Object> bean2Map(final Class<T> clazz, Map<String, Object> params) {
        if (null == params) {
            params = new HashMap<>();
        }

        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                field.setAccessible(true);
                Object value = field.get(key);

                params.put(key, value);
            }
        } catch (IllegalAccessException e) {
            LOGGER.warn("bean2Map got error", e);
        }

        return params;
    }

    public static <T> T map2Bean(Map map, final Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String varName = field.getName();
                Object object = map.get(varName);
                if (object != null) {
                    field.setAccessible(true);
                    field.set(varName, object);
                }
            }
            return bean;
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.warn("map2Bean got error", e);
        }

        return null;
    }

    public static <E, T> T translateBean(E source, final Class<T> destClazz) {
        try {
            T dest = destClazz.newInstance();
            Field[] sourceFields = source.getClass().getDeclaredFields();
            copyProperties(source, sourceFields, dest);
            Field[] superFields = source.getClass().getSuperclass().getDeclaredFields();
            copyProperties(source, superFields, dest);
            return dest;
        } catch (Exception e) {
            LOGGER.warn("translateBean got error", e);
        }

        return null;
    }

    private static <E, T> void copyProperties(E source, Field[] sourceFields, T dest) {
        for (Field s : sourceFields) {
            int sourceModifier = s.getModifiers();
            if (!Modifier.isStatic(sourceModifier) && !Modifier.isFinal(sourceModifier)) {
                String name = s.getName();
                s.setAccessible(true);
                try {
                    Object object = s.get(source);
                    Field destField = null;
                    // TODO fix the dirty code
                    try {
                        destField = dest.getClass().getDeclaredField(name);
                    } catch (Exception e) {
                        try {
                            destField = dest.getClass().getSuperclass().getDeclaredField(name);
                        } catch (Exception e1) {

                        }
                    }
                    if (null != destField && null != object) {
                        destField.setAccessible(true);
                        PropertyDescriptor pd = new PropertyDescriptor(name, dest.getClass());
                        Method writeMethod = pd.getWriteMethod();
                        writeMethod.invoke(dest, object);
                    }
                } catch (Exception e) {
                    LOGGER.warn("field [{}] set error", name, e);
                }
            }
        }
    }

    public static <E, T> T translateBean(E source, T dest) {
        try {
            Field[] sourceFields = source.getClass().getDeclaredFields();
            copyProperties(source, sourceFields, dest);
            Field[] superFields = source.getClass().getDeclaredFields();
            copyProperties(source, superFields, dest);
            return dest;
        } catch (Exception e) {
            LOGGER.warn("translateBean got error", e);
        }

        return null;
    }

    public static String subString(String sourceString, int maxLength) {
        String resultString = "";
        if (sourceString == null || sourceString.equals("") || maxLength < 1) {
            return resultString;
        } else if (sourceString.length() <= maxLength) {
            return sourceString;
        } else if (sourceString.length() > 2 * maxLength) {
            sourceString = sourceString.substring(0, 2 * maxLength);
        }

        if (sourceString.length() > maxLength) {
            char[] chr = sourceString.toCharArray();
            int strNum = 0;
            int strGBKNum = 0;
            int len = 0;

            for (int i = 0; i < sourceString.length(); i++) {
                if (chr[i] >= 0xa1) { //0xa1汉字最小位开始
                    strNum = strNum + 2;
                    strGBKNum++;
                } else {
                    strNum++;
                }
                len++;

                if (len == maxLength) {
                    break;
                }

                if (strNum == 2 * maxLength || strNum == 2 * maxLength + 1) {
                    break;
                }
            }
            resultString = sourceString.substring(0, strNum - strGBKNum) + "...";
        }

        return resultString;
    }


    public static HashMap<String, String> convertMap(final Map<String, String[]> inputMap) {
        HashMap<String, String> outMap = new HashMap<>();
        if (isEmpty(inputMap)) {
            return outMap;
        }
        inputMap.forEach((k, v) -> outMap.put(k, Arrays.toString(v)));
        return outMap;
    }
}
