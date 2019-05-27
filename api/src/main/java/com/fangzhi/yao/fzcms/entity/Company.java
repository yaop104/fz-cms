package com.fangzhi.yao.fzcms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-22
 */
@TableName("fz_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="fz_company_code", type= IdType.AUTO)
	private Long fzCompanyCode;
    /**
     * 公司名称
     */
    @TableField("fz_company_name")
	private String fzCompanyName;
    /**
     * 注册时间
     */
    @TableField(value = "fz_company_ctime", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fzCompanyCtime;
    /**
     * 公司地址
     */
    @TableField("fz_company_address")
	private String fzCompanyAddress;
    /**
     * 联系号码
     */
    @TableField("fz_company_mobile")
	private String fzCompanyMobile;
    /**
     * 联系人
     */
    @TableField("fz_company_person")
	private String fzCompanyPerson;
    /**
     * 状态1正常2注销3已过期
     */
    @TableField("fz_company_status")
	private Integer fzCompanyStatus;
    /**
     * 删除1正常2删除
     */
    @TableField("fz_company_delete")
	private Integer fzCompanyDelete;
    /**
     * 更新时间
     */
    @TableField(value = "fz_company_utime" , fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fzCompanyUtime;
    /**
     * 父账号id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("fz_company_parent_code")
	private Long fzCompanyParentCode;
    /**
     * 有效期
     */
    @TableField(value = "fz_company_limit_time", fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fzCompanyLimitTime;


	public Long getFzCompanyCode() {
		return fzCompanyCode;
	}

	public void setFzCompanyCode(Long fzCompanyCode) {
		this.fzCompanyCode = fzCompanyCode;
	}

	public String getFzCompanyName() {
		return fzCompanyName;
	}

	public void setFzCompanyName(String fzCompanyName) {
		this.fzCompanyName = fzCompanyName;
	}

	public Date getFzCompanyCtime() {
		return fzCompanyCtime;
	}

	public void setFzCompanyCtime(Date fzCompanyCtime) {
		this.fzCompanyCtime = fzCompanyCtime;
	}

	public String getFzCompanyAddress() {
		return fzCompanyAddress;
	}

	public void setFzCompanyAddress(String fzCompanyAddress) {
		this.fzCompanyAddress = fzCompanyAddress;
	}

	public String getFzCompanyMobile() {
		return fzCompanyMobile;
	}

	public void setFzCompanyMobile(String fzCompanyMobile) {
		this.fzCompanyMobile = fzCompanyMobile;
	}

	public String getFzCompanyPerson() {
		return fzCompanyPerson;
	}

	public void setFzCompanyPerson(String fzCompanyPerson) {
		this.fzCompanyPerson = fzCompanyPerson;
	}

	public Integer getFzCompanyStatus() {
		return fzCompanyStatus;
	}

	public void setFzCompanyStatus(Integer fzCompanyStatus) {
		this.fzCompanyStatus = fzCompanyStatus;
	}

	public Integer getFzCompanyDelete() {
		return fzCompanyDelete;
	}

	public void setFzCompanyDelete(Integer fzCompanyDelete) {
		this.fzCompanyDelete = fzCompanyDelete;
	}

	public Date getFzCompanyUtime() {
		return fzCompanyUtime;
	}

	public void setFzCompanyUtime(Date fzCompanyUtime) {
		this.fzCompanyUtime = fzCompanyUtime;
	}

	public Long getFzCompanyParentCode() {
		return fzCompanyParentCode;
	}

	public void setFzCompanyParentCode(Long fzCompanyParentCode) {
		this.fzCompanyParentCode = fzCompanyParentCode;
	}

	public Date getFzCompanyLimitTime() {
		return fzCompanyLimitTime;
	}

	public void setFzCompanyLimitTime(Date fzCompanyLimitTime) {
		this.fzCompanyLimitTime = fzCompanyLimitTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.fzCompanyCode;
	}

	@Override
	public String toString() {
		return "Company{" +
			", fzCompanyCode=" + fzCompanyCode +
			", fzCompanyName=" + fzCompanyName +
			", fzCompanyCtime=" + fzCompanyCtime +
			", fzCompanyAddress=" + fzCompanyAddress +
			", fzCompanyMobile=" + fzCompanyMobile +
			", fzCompanyPerson=" + fzCompanyPerson +
			", fzCompanyStatus=" + fzCompanyStatus +
			", fzCompanyDelete=" + fzCompanyDelete +
			", fzCompanyUtime=" + fzCompanyUtime +
			", fzCompanyParentCode=" + fzCompanyParentCode +
			", fzCompanyLimitTime=" + fzCompanyLimitTime +
			"}";
	}
}
