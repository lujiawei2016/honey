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
 * 种类
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月13日下午1:51:12
 */
@Entity
@Table(name="tb_category")
public class Category implements Serializable {

	private static final long serialVersionUID = -6627041798013649547L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryId;         // 主键
	
	@Column(name="cateName",nullable=false)
	private String cateName;        // 种类名
	
	@Column(name="imgUrl",nullable=false)
	private String imgUrl;          // 图片地址
	
	@Column(name="sort")
	private int sort;               // 排序
	
	@Column(name="delflag",columnDefinition="INT default 0")
	private int delflag;            // 删除标志，0-未删除，1-删除
	
	@ManyToMany(targetEntity=Girl.class,mappedBy="category")
	@JsonIgnore
	private Set<Girl> girl;
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	public Set<Girl> getGirl() {
		return girl;
	}

	public void setGirl(Set<Girl> girl) {
		this.girl = girl;
	}
	
	public Category() {
		super();
	}

	public Category(String cateName, String imgUrl, Set<Girl> girl) {
		super();
		this.cateName = cateName;
		this.imgUrl = imgUrl;
		this.girl = girl;
	}
	
}
