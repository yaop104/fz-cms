package com.fangzhi.yao.fzcms.generator;

/**
 * Created by yao
 * 消息唯一id迭代器
 * 2   +        13        +      5      =    20
 * xx      xxxxxxxxxxxxx        xxxxx
 * 节点编号      13时间戳         自增序列号
 */
public class UniqueIdGenerator {
    private static UniqueIdGenerator instance = new UniqueIdGenerator();
    private SequenceGenerator sequenceGenerator = new SequenceGenerator(5);
    public StringBuffer buffer = new StringBuffer(0);

    public static UniqueIdGenerator getInstance() {
        return instance;
    }

    public String generate() {
        buffer.setLength(0);
        buffer.append(NodeManager.logId);
        buffer.append(System.currentTimeMillis());
        buffer.append(sequenceGenerator.next());
        return buffer.toString();
    }

    private UniqueIdGenerator() {

    }
}