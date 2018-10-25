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
 * 组织表
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-24
 */
@TableName("sys_org")
public class Org extends Model<Org> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="sys_org_id", type= IdType.AUTO)
	private Integer sysOrgId;
    /**
     * 编码
     */
    @TableField("sys_org_code")
	private String sysOrgCode;
    /**
     * 名字
     */
    @TableField("sys_org_name")
	private String sysOrgName;
    /**
     * 父编码
     */
    @TableField("sys_org_pcode")
	private String sysOrgPcode;
    /**
     * 状态1正常2删除
     */
    @TableField("sys_org_state")
	private String sysOrgState;
    /**
     * 排序
     */
    @TableField("sys_org_order")
	private Integer sysOrgOrder;
    /**
     * 描述
     */
    @TableField("sys_org_desc")
	private String sysOrgDesc;
    /**
     * 层级
     */
    @TableField("sys_org_type")
	private Integer sysOrgType;
    /**
     * 对外编号
     */
    @TableField("sys_org_outercode")
	private String sysOrgOutercode;
    /**
     * 创建时间
     */
    @TableField(value = "sys_org_cdate", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date sysOrgCdate;
    /**
     * 修改时间
     */
    @TableField(value = "sys_org_udate", fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date sysOrgUdate;
    /**
     * 创建人
     */
    @TableField("sys_org_cuser")
	private String sysOrgCuser;
    /**
     * 公司编号
     */
    @TableField("sys_company_code")
	private String sysCompanyCode;


	public Integer getSysOrgId() {
		return sysOrgId;
	}

	public void setSysOrgId(Integer sysOrgId) {
		this.sysOrgId = sysOrgId;
	}

	public String getSysOrgCode() {
		return sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public String getSysOrgName() {
		return sysOrgName;
	}

	public void setSysOrgName(String sysOrgName) {
		this.sysOrgName = sysOrgName;
	}

	public String getSysOrgPcode() {
		return sysOrgPcode;
	}

	public void setSysOrgPcode(String sysOrgPcode) {
		this.sysOrgPcode = sysOrgPcode;
	}

	public String getSysOrgState() {
		return sysOrgState;
	}

	public void setSysOrgState(String sysOrgState) {
		this.sysOrgState = sysOrgState;
	}

	public Integer getSysOrgOrder() {
		return sysOrgOrder;
	}

	public void setSysOrgOrder(Integer sysOrgOrder) {
		this.sysOrgOrder = sysOrgOrder;
	}

	public String getSysOrgDesc() {
		return sysOrgDesc;
	}

	public void setSysOrgDesc(String sysOrgDesc) {
		this.sysOrgDesc = sysOrgDesc;
	}

	public Integer getSysOrgType() {
		return sysOrgType;
	}

	public void setSysOrgType(Integer sysOrgType) {
		this.sysOrgType = sysOrgType;
	}

	public String getSysOrgOutercode() {
		return sysOrgOutercode;
	}

	public void setSysOrgOutercode(String sysOrgOutercode) {
		this.sysOrgOutercode = sysOrgOutercode;
	}

	public Date getSysOrgCdate() {
		return sysOrgCdate;
	}

	public void setSysOrgCdate(Date sysOrgCdate) {
		this.sysOrgCdate = sysOrgCdate;
	}

	public Date getSysOrgUdate() {
		return sysOrgUdate;
	}

	public void setSysOrgUdate(Date sysOrgUdate) {
		this.sysOrgUdate = sysOrgUdate;
	}

	public String getSysOrgCuser() {
		return sysOrgCuser;
	}

	public void setSysOrgCuser(String sysOrgCuser) {
		this.sysOrgCuser = sysOrgCuser;
	}

	public String getSysCompanyCode() {
		return sysCompanyCode;
	}

	public void setSysCompanyCode(String sysCompanyCode) {
		this.sysCompanyCode = sysCompanyCode;
	}

	@Override
	protected Serializable pkVal() {
		return this.sysOrgId;
	}

	@Override
	public String toString() {
		return "Org{" +
			", sysOrgId=" + sysOrgId +
			", sysOrgCode=" + sysOrgCode +
			", sysOrgName=" + sysOrgName +
			", sysOrgPcode=" + sysOrgPcode +
			", sysOrgState=" + sysOrgState +
			", sysOrgOrder=" + sysOrgOrder +
			", sysOrgDesc=" + sysOrgDesc +
			", sysOrgType=" + sysOrgType +
			", sysOrgOutercode=" + sysOrgOutercode +
			", sysOrgCdate=" + sysOrgCdate +
			", sysOrgUdate=" + sysOrgUdate +
			", sysOrgCuser=" + sysOrgCuser +
			", sysCompanyCode=" + sysCompanyCode +
			"}";
	}
}
