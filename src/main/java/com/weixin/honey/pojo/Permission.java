package com.weixin.honey.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 权限类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月14日下午4:52:03
 */
@Entity
@Table(name="tb_permission")
public class Permission implements Serializable {

	private static final long serialVersionUID = 124105438171108227L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="mark",nullable=false)
	private String mark;
	
	@ManyToMany(targetEntity=Roles.class,mappedBy="permission")
	@JsonIgnore
	private Set<Roles> roles;
	
	@OneToMany(targetEntity=Permission.class,mappedBy="father",orphanRemoval=true)
	@Cascade(value={CascadeType.ALL})
	@JsonIgnore
	private Set<Permission> children;
	
	@ManyToOne(targetEntity=Permission.class)
	@JoinColumn(name="father_id")
	@JsonIgnore
	private Permission father;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Set<Permission> getChildren() {
		return children;
	}

	public void setChildren(Set<Permission> children) {
		this.children = children;
	}

	public Permission getFather() {
		return father;
	}

	public void setFather(Permission father) {
		this.father = father;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Permission() {
		super();
	}
	
	public Permission(int id, String name, String mark) {
		super();
		this.id = id;
		this.name = name;
		this.mark = mark;
	}

	public Permission(String name, String mark) {
		super();
		this.name = name;
		this.mark = mark;
	}
	
	public Permission(String name, String mark, Permission father) {
		super();
		this.name = name;
		this.mark = mark;
		this.father = father;
	}

	public Permission(int id, String name, String mark, Permission father) {
		super();
		this.id = id;
		this.name = name;
		this.mark = mark;
		this.father = father;
	}
	
}
