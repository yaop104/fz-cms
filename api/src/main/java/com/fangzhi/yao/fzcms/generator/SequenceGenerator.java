package com.fangzhi.yao.fzcms.generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yao
 */
public class SequenceGenerator {
    //迭代字符的标准长度
    private int length;
    private int maxValue;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private FixLengthFormater formater;

    public SequenceGenerator(int length) {
        this.length = length;
        this.formater = new FixLengthFormater(length);
        this.maxValue = (int) Math.pow(10,length);
    }

    public String next(){
        int nextVaule = atomicInteger.incrementAndGet();
        if (nextVaule == maxValue){
            atomicInteger.set(1);
            nextVaule = 1;
        }
        return formater.format(nextVaule);
    }
}
