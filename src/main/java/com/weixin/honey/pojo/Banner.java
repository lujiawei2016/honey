package com.weixin.honey.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * banner
 * @author  lujiawei
 * @version V1.0
 * @date    2017年4月25日下午2:54:45
 */
@Entity
@Table(name="tb_banner")
public class Banner implements Serializable {

	private static final long serialVersionUID = -2958507953398184097L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bannerId;
	
	@Column(name="imgUrl",nullable=false)
	private String imgUrl;      // 图片路径
	
	@Column(name="imgName",nullable=false)
	private String imgName;     // 图片名称
	
	@Column(name="url")
	private String url;         // 跳转地址
	
	@Column(name="sort")
	private int sort;           // 排序
	
	@Column(name="delflag",columnDefinition="INT default 0")
	private int delflag;        // 删除标志，0-未删除，1-删除

	public int getBannerId() {
		return bannerId;
	}

	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Banner() {
		super();
	}

	public Banner(String imgUrl, String imgName, String url, int sort) {
		super();
		this.imgUrl = imgUrl;
		this.imgName = imgName;
		this.url = url;
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Banner [bannerId=" + bannerId + ", imgUrl=" + imgUrl + ", imgName=" + imgName + ", url=" + url
				+ ", sort=" + sort + ", delflag=" + delflag + "]";
	}
	
}
