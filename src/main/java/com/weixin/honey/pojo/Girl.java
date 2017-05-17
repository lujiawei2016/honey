package com.weixin.honey.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 妹纸
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月13日下午1:52:55
 */
@Entity
@Table(name="tb_girl")
public class Girl implements Serializable {
	
	private static final long serialVersionUID = -1902192203962666114L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int girlId;                 // 主键
	
	@Column(name="girlName",nullable=false)
	private String girlName;            // 妹纸名称
	
	@Column(name="age")
	private int age;                    // 年龄
	
	@Column(name="hight")
	private String hight;               // 身高
	
	@Column(name="weight")
	private String weight;              // 体重
	
	@Column(name="qq")
	private String qq;                  // qq
	
	@Column(name="weixin")
	private String weixin;              // 微信
	
	@Column(name="phone")
	private String phone;               // 电话
	
	@Column(name="price")
	private int price;                  // 价格
	
	@Column(name="address")
	private String address;             // 地址
	
	@Column(name="mainImg")
	private String mainImg;             // 主页图片
	
	@Column(name="title")
	private String title;               // 标题
	
	@Column(name="description")
	private String description;         // 描述
	
	@Column(name="praise")
	private int praise;                 // 点赞数
	
	@Column(name="sort")
	private int sort;                   // 排序
	
	@Column(name="delflag",columnDefinition="INT default 0")
	private int delflag;                // 删除标志，0-未删除，1-删除
	
	@ManyToMany(targetEntity=Category.class)
	@JoinTable(name="tb_girl_category",joinColumns={@JoinColumn(name="girl_id")},inverseJoinColumns={@JoinColumn(name="category_id")})
	@JsonIgnore
	private Set<Category> category;
	
	@OneToMany(targetEntity=GirlImg.class,mappedBy="girl",orphanRemoval=true)
	@Cascade(value={CascadeType.ALL})
	@JsonIgnore
	private Set<GirlImg> girlImg;
	
	@OneToMany(targetEntity=Comments.class,mappedBy="girl",orphanRemoval=true)
	@Cascade(value={CascadeType.ALL})
	@JsonIgnore
	private Set<Comments> comments;
	
	public int getGirlId() {
		return girlId;
	}

	public void setGirlId(int girlId) {
		this.girlId = girlId;
	}

	public String getGirlName() {
		return girlName;
	}

	public void setGirlName(String girlName) {
		this.girlName = girlName;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHight() {
		return hight;
	}

	public void setHight(String hight) {
		this.hight = hight;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	public Set<GirlImg> getGirlImg() {
		return girlImg;
	}

	public void setGirlImg(Set<GirlImg> girlImg) {
		this.girlImg = girlImg;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}