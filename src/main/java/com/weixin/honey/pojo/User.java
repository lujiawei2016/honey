package com.weixin.honey.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	
	@Column(name="createTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@ManyToMany(targetEntity=Roles.class,cascade={javax.persistence.CascadeType.MERGE,javax.persistence.CascadeType.PERSIST})
	@JoinTable(name="tb_user_roles",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="roles_id")})
	@JsonIgnore
	private Set<Roles> roles;
	
	@OneToMany(targetEntity=Comments.class,mappedBy="user",orphanRemoval=true)
	@Cascade(value={CascadeType.ALL})
	@JsonIgnore
	private Set<Comments> comments;
	
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
	
	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User() {
		super();
	}

	public User(String username, String password, Date createTime) {
		super();
		this.username = username;
		this.password = password;
		this.createTime = createTime;
	}
	
}
