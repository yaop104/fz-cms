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
public class PushMessageDTO extends BaseObject{
    private String notificationTitle;
    private String msgTitle;
    private String msgContent;
    private String extrasParam;
    private String alias;
    private String registrationId;
    private String tag;
}
