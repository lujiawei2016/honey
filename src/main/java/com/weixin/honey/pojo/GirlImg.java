package com.weixin.honey.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 衣服照片
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月13日下午3:44:50
 */
@Entity
@Table(name="tb_girlImg")
public class GirlImg implements Serializable {
	
	private static final long serialVersionUID = -1183157090098516552L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;                       // 主键
	
	@Column(name="imgUrl",nullable=false)
	private String imgUrl;                 // 图片地址
	
	@ManyToOne(targetEntity=Girl.class)
	@JoinColumn(name="girl_id")
	private Girl girl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Girl getGirl() {
		return girl;
	}

	public void setGirl(Girl girl) {
		this.girl = girl;
	}
	
	public GirlImg() {
		super();
	}

	public GirlImg(String imgUrl, Girl girl) {
		super();
		this.imgUrl = imgUrl;
		this.girl = girl;
	}
	
}
