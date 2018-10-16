package com.fangzhi.yao.fzcms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 发送表
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-16
 */
@TableName("fz_send_message")
public class SendMessage extends Model<SendMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="fz_send_unid", type= IdType.AUTO)
	private Integer fzSendUnid;
    /**
     * 公司编号
     */
    @TableField("fz_company_code")
	private String fzCompanyCode;
    /**
     * 发送时间
     */
	@TableField(value = "fz_send_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date fzSendTime;
    /**
     * 状态1已发送2待发送
     */
    @TableField("fz_send_state")
	private String fzSendState;
    /**
     * 验证码
     */
    @TableField("fz_send_code")
	private String fzSendCode;
    /**
     * 手机号码
     */
    @TableField("fz_send_mobile")
	private String fzSendMobile;
    /**
     * 发送类型1短信
     */
    @TableField("fz_send_type")
	private String fzSendType;


	public Integer getFzSendUnid() {
		return fzSendUnid;
	}

	public void setFzSendUnid(Integer fzSendUnid) {
		this.fzSendUnid = fzSendUnid;
	}

	public String getFzCompanyCode() {
		return fzCompanyCode;
	}

	public void setFzCompanyCode(String fzCompanyCode) {
		this.fzCompanyCode = fzCompanyCode;
	}

	public Date getFzSendTime() {
		return fzSendTime;
	}

	public void setFzSendTime(Date fzSendTime) {
		this.fzSendTime = fzSendTime;
	}

	public String getFzSendState() {
		return fzSendState;
	}

	public void setFzSendState(String fzSendState) {
		this.fzSendState = fzSendState;
	}

	public String getFzSendCode() {
		return fzSendCode;
	}

	public void setFzSendCode(String fzSendCode) {
		this.fzSendCode = fzSendCode;
	}

	public String getFzSendMobile() {
		return fzSendMobile;
	}

	public void setFzSendMobile(String fzSendMobile) {
		this.fzSendMobile = fzSendMobile;
	}

	public String getFzSendType() {
		return fzSendType;
	}

	public void setFzSendType(String fzSendType) {
		this.fzSendType = fzSendType;
	}

	@Override
	protected Serializable pkVal() {
		return this.fzSendUnid;
	}

	@Override
	public String toString() {
		return "SendMessage{" +
			", fzSendUnid=" + fzSendUnid +
			", fzCompanyCode=" + fzCompanyCode +
			", fzSendTime=" + fzSendTime +
			", fzSendState=" + fzSendState +
			", fzSendCode=" + fzSendCode +
			", fzSendMobile=" + fzSendMobile +
			", fzSendType=" + fzSendType +
			"}";
	}


}
