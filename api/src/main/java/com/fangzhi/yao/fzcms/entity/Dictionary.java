package com.fangzhi.yao.fzcms.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * <p>
 * 
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-22
 */
@TableName("sys_dictionary")
public class Dictionary extends Model<Dictionary> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="dic_id", type= IdType.AUTO)
	private Long dicId;
    /**
     * 类型1大类2小类
     */
    @TableField("dic_type")
	private Integer dicType;
    /**
     * 创建时间
     */
    @TableField("dic_ctime")
	private Date dicCtime;
    /**
     * 修改时间
     */
    @TableField("dic_utime")
	private Date dicUtime;
    /**
     * 修改人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("dic_admin")
	private Long dicAdmin;
    /**
     * 删除1正常2删除
     */
    @TableField("dic_delete")
	private Integer dicDelete;
    /**
     * 父id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("dic_parent")
	private Long dicParent;
    /**
     * 名字
     */
    @TableField("dic_name")
	private String dicName;
    /**
     * 公司id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("dic_company_code")
	private Long dicCompanyCode;


	public Long getDicId() {
		return dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}

	public Integer getDicType() {
		return dicType;
	}

	public void setDicType(Integer dicType) {
		this.dicType = dicType;
	}

	public Date getDicCtime() {
		return dicCtime;
	}

	public void setDicCtime(Date dicCtime) {
		this.dicCtime = dicCtime;
	}

	public Date getDicUtime() {
		return dicUtime;
	}

	public void setDicUtime(Date dicUtime) {
		this.dicUtime = dicUtime;
	}

	public Long getDicAdmin() {
		return dicAdmin;
	}

	public void setDicAdmin(Long dicAdmin) {
		this.dicAdmin = dicAdmin;
	}

	public Integer getDicDelete() {
		return dicDelete;
	}

	public void setDicDelete(Integer dicDelete) {
		this.dicDelete = dicDelete;
	}

	public Long getDicParent() {
		return dicParent;
	}

	public void setDicParent(Long dicParent) {
		this.dicParent = dicParent;
	}

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public Long getDicCompanyCode() {
		return dicCompanyCode;
	}

	public void setDicCompanyCode(Long dicCompanyCode) {
		this.dicCompanyCode = dicCompanyCode;
	}

	@Override
	protected Serializable pkVal() {
		return this.dicId;
	}

	@Override
	public String toString() {
		return "Dictionary{" +
			", dicId=" + dicId +
			", dicType=" + dicType +
			", dicCtime=" + dicCtime +
			", dicUtime=" + dicUtime +
			", dicAdmin=" + dicAdmin +
			", dicDelete=" + dicDelete +
			", dicParent=" + dicParent +
			", dicName=" + dicName +
			", dicCompanyCode=" + dicCompanyCode +
			"}";
	}
}
