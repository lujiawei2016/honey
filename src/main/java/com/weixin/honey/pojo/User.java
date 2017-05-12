package com.weixin.honey.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 用户类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月14日下午4:51:48
 */
@Entity
@Table(name="tb_user")
public class User implements Serializable {

	private static final long serialVersionUID = -6716900269203545922L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="username",nullable=false)
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@ManyToMany(targetEntity=Roles.class,cascade={javax.persistence.CascadeType.MERGE,javax.persistence.CascadeType.PERSIST})
	@JoinTable(name="tb_user_roles",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="roles_id")})
	@JsonIgnore
	private Set<Roles> roles;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
