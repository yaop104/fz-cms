package com.fangzhi.yao.fzcms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-08
 */
@TableName("oauth2_client")
public class Client extends Model<Client> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 客户端名称
     */
    @TableField("client_name")
	private String clientName;
    /**
     * 客户端 id
     */
    @TableField("client_id")
	private String clientId;
    /**
     * 客户端安全 key
     */
    @TableField("client_secret")
	private byte[] clientSecret;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public byte[] getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(byte[] clientSecret) {
		this.clientSecret = clientSecret;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Client{" +
			", id=" + id +
			", clientName=" + clientName +
			", clientId=" + clientId +
			", clientSecret=" + clientSecret +
			"}";
	}
}
