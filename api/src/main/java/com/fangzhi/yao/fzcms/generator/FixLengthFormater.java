package com.fangzhi.yao.fzcms.generator;

/**
 * Created by yao
 */
public class FixLengthFormater {
    public static final String DEFAULT_FORMAT_PREFIX = "0";
    private String prefix = DEFAULT_FORMAT_PREFIX;
    private int length;
    private String[] prefixPool;

    public FixLengthFormater(String prefix, int length) {
        this.prefix = prefix;
        this.length = length;
        initPrefix();
    }

    public FixLengthFormater(int length){
        this.length = length;
        initPrefix();
    }

    protected String format(int origin){
        String src = String.valueOf(origin);
        if (src.length() < length){
            return prefixPool[length - src.length() - 1] + origin;
        }
        return src;
    }

    private void initPrefix() {
        prefixPool = new String[length - 1];
        for(int i = 0 ; i < length - 1 ; i ++ ) {
            prefixPool[i] = getPrefix(i+1);
        }
    }

    private String getPrefix(int length){
        StringBuilder builder = new StringBuilder(prefix);
        for(int i = 0 ; i < length-1; i ++){
            builder.append(prefix);
        }
        return builder.toString();
    }

}
