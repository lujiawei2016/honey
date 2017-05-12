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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 角色类
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月14日下午4:51:56
 */
@Entity
@Table(name="tb_roles")
public class Roles implements Serializable {

	private static final long serialVersionUID = 8666726173565183631L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="rolesName",nullable=false)
	private String rolesName;

	@ManyToMany(targetEntity=User.class,mappedBy="roles",cascade={javax.persistence.CascadeType.MERGE,javax.persistence.CascadeType.PERSIST})
	@JsonIgnore
	private Set<User> user;
	
	@ManyToMany(targetEntity=Permission.class)
	@JoinTable(name="tb_roles_permission",joinColumns={@JoinColumn(name="roles_id")},inverseJoinColumns={@JoinColumn(name="permission_id")})
	@JsonIgnore
	private Set<Permission> permission;
	
	@OneToMany(targetEntity=Roles.class,mappedBy="father",orphanRemoval=true)
	@Cascade(value={CascadeType.ALL})
	@JsonIgnore
	private Set<Roles> children;
	
	@ManyToOne(targetEntity=Roles.class)
	@JoinColumn(name="father_id")
	@JsonIgnore
	private Roles father;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Set<Permission> getPermission() {
		return permission;
	}

	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}

	public Set<Roles> getChildren() {
		return children;
	}

	public void setChildren(Set<Roles> children) {
		this.children = children;
	}

	public Roles getFather() {
		return father;
	}

	public void setFather(Roles father) {
		this.father = father;
	}

	public Roles() {
		super();
	}

	public Roles(String rolesName) {
		super();
		this.rolesName = rolesName;
	}
	
	public Roles(int id, String rolesName) {
		super();
		this.id = id;
		this.rolesName = rolesName;
	}

	public Roles(String rolesName, Set<Permission> permission, Roles father) {
		super();
		this.rolesName = rolesName;
		this.permission = permission;
		this.father = father;
	}
	
}
