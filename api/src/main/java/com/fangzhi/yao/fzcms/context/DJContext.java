package com.fangzhi.yao.fzcms.context;

import com.fangzhi.yao.fzcms.base.Base;
import com.fangzhi.yao.fzcms.generator.SpanIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yao
 * 封装自定义上下文
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DJContext extends Base {

    /**
     * 请求唯一ID，用来追踪整个请求的处理流程
     */
    private String uniqueId = null;

    /**
     * 请求调用标识，记录调用父子关系
     */
    private String spanId = null;

    /**
     * 初始的spanId
     */
    private String initialSpanId = null;


    private String nextSpanId = null;

    /**
     * adminFlag,标识用户类型:0 普通用户 1 管理员 2 系统用户
     */
    private Integer af = Af.USER.get();

    /**
     * 操作人uid
     */
    private Long uid = null;

    /**
     * 操作人customerID
     */
    private Long customerId = null;

    /**
     * APP版本号
     */
    private String appVersion = null;

    /**
     * 附加字段
     */
    private Map<String, String> attachments = new HashMap<>();

    /**
     * Af 枚举
     */
    public enum Af {
        USER(0), ADMIN(1), SYSTEM(2);

        private int code;

        private Af(int code) {
            this.code = code;
        }

        public int get() {
            return code;
        }
    }

    /**
     * ThreadLocal对象
     */
    private static ThreadLocal<DJContext> LOCAL = ThreadLocal.withInitial(DJContext::new);

    public static DJContext getContext() {
        return LOCAL.get();
    }

    public static void setContext(DJContext context) {
        LOCAL.set(context);
    }

    public static void removeContext() {
        LOCAL.remove();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getAf() {
        return af;
    }

    public void setAf(Integer af) {
        this.af = af;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }

    //对外提供变量读写静态方法
    public static String getUniqueID() {
        return getContext().uniqueId;
    }

    //对外提供变量读写静态方法
    public static String getSpanID() {
        return getContext().spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public static String getInitialSpanId() {
        return getContext().initialSpanId;
    }

    public void setInitialSpanId(String initialSpanId) {
        this.initialSpanId = initialSpanId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setAttachment(String key, String value) {
        if (value == null) {
            attachments.remove(key);
        } else {
            attachments.put(key, value);
        }
    }

    public void increaseSpanId() {
        SpanIdGenerator.increaseSpan(this.spanId);
    }

    public String increaseNextSpandId() {
        if (this.nextSpanId == null) {
            this.nextSpanId = this.spanId;
        }

        this.nextSpanId = SpanIdGenerator.increaseSpan(this.nextSpanId);

        return this.nextSpanId;
    }

    public static Integer getAF() {
        return getContext().getAf();
    }

    public static Long getUID() {
        return getContext().uid;
    }

    public static Long getCustomerID() {
        return getContext().customerId;
    }

    public static String getAppVer() {
        return getContext().appVersion;
    }

    public static Map<String, String> getAttachmentsStatic() {
        return getContext().attachments;
    }

    public static String getAttachment(String key) {
        return getContext().attachments.get(key);
    }

    public static void addAttachment(String key, String value) {
        if (value == null) {
            getContext().attachments.remove(key);
        } else {
            getContext().attachments.put(key, value);
        }
    }

    public static void removeAttachment(String key) {
        getContext().attachments.remove(key);
    }

    public String getNextSpanId() {
        return nextSpanId;
    }

    public void setNextSpanId(String nextSpanId) {
        this.nextSpanId = nextSpanId;
    }
}
