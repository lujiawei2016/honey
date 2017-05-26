package com.weixin.honey.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 微信菜单
 * @author lujiawei
 *
 */
@Entity
@Table(name="tb_wxMenu")
public class WxMenu {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name",nullable=false)
	private String name;             // 菜单名称

	@Column(name="type")
	private String type;             // view或者click
	                                 
	@Column(name="url")
	private String url;              // 当type为view时需要跳转的地址
	                                 
	@Column(name="menuKey")
	private String menuKey;          // 当type额外click时需要接收的key
	      
	@Column(name="sort",nullable=false)
	private int sort;                // 排序
	                  
	@Column(name="fatherId")
	private int fatherId;            // 父菜单
	
	@Column(name="mark")
	private int mark;                // 判断一级菜单下面是否有二级菜单  0:没有，1:有
	
	@OneToMany(targetEntity=WxMenu.class,mappedBy="father",orphanRemoval=true)
	@Cascade(value={CascadeType.ALL})
	private Set<WxMenu> children;    // 一个父菜单对应多个子菜单
	
	@ManyToOne(targetEntity=WxMenu.class)
	@JoinColumn(name="wxMenu_id")
	private WxMenu father;           // 多个子菜单对应一个父菜单

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getFatherId() {
		return fatherId;
	}

	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Set<WxMenu> getChildren() {
		return children;
	}

	public void setChildren(Set<WxMenu> children) {
		this.children = children;
	}

	public WxMenu getFather() {
		return father;
	}

	public void setFather(WxMenu father) {
		this.father = father;
	}
	
}
