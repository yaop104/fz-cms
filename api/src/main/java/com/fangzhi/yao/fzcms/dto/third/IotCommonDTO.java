package com.fangzhi.yao.fzcms.dto.third;

import com.fangzhi.yao.fzcms.base.BaseObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author yaoping
 * @date 2018/10/18 AM12:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IotCommonDTO extends BaseObject{
    private String requestMessage;
    private String productKey;
    private Integer timeout;
    private String iotInstanceId;
    private String topic;
    private String deviceName;
    private Integer pageSize;
    private Integer offset;
}
