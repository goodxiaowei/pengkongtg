package com.pengkongtec.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1221757517583306523L;
	private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String telephone;
    private String status;
    private Date createTime;
    private Date updateTime;
    
	public User() {
	}

	public User(Integer id, String username, String password, Integer age,
			String telephone, String status, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
		this.telephone = telephone;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", age=" + age + ", telephone=" + telephone
				+ ", status=" + status + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
	
}
