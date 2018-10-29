package com.fangzhi.yao.fzcms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 角色ID
     */
    @TableField("role_id")
	private Integer roleId;
    /**
     * 名称
     */
	private String name;
    /**
     * 用户名
     */
    @TableField("user_name")
	private String userName;
    /**
     * 密码
     */
    @TableField("pass_word")
    @JsonIgnore
	private String passWord;
    /**
     * 盐值
     */
	@TableField("salt")
	@JsonIgnore
	private String salt;
    /**
     * 状态(0：禁用，1：启用，2：锁定)
     */
	@TableField("state")
	private Integer state;
    /**
     * 修改时间
     */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 操作人
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@TableField("user_admin")
	@JsonIgnore
	private Long userAdmin;
	/**
	 * 公司id
	 */
	@TableField("user_company_code")
	private String userCompanyCode;
	/**
	 * 公司组织树层级
	 */
	@TableField("user_company_type")
	private Integer userCompanyType;
	/**
	 * 删除1正常2删除
	 */
	@TableField("user_delete")
	@JsonIgnore
	private Integer userDelete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(Long userAdmin) {
		this.userAdmin = userAdmin;
	}

	public String getUserCompanyCode() {
		return userCompanyCode;
	}

	public void setUserCompanyCode(String userCompanyCode) {
		this.userCompanyCode = userCompanyCode;
	}

	public Integer getUserCompanyType() {
		return userCompanyType;
	}

	public void setUserCompanyType(Integer userCompanyType) {
		this.userCompanyType = userCompanyType;
	}

	public Integer getUserDelete() {
		return userDelete;
	}

	public void setUserDelete(Integer userDelete) {
		this.userDelete = userDelete;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
				", id=" + id +
				", roleId=" + roleId +
				", name=" + name +
				", userName=" + userName +
				", passWord=" + passWord +
				", salt=" + salt +
				", state=" + state +
				", updateTime=" + updateTime +
				", createTime=" + createTime +
				", userAdmin=" + userAdmin +
				", userCompanyCode=" + userCompanyCode +
				", userCompanyType=" + userCompanyType +
				", userDelete=" + userDelete +
				"}";
	}
}
