package com.weixin.honey.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 备战区
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月21日下午1:00:01
 */
@Entity
@Table(name="tb_car")
public class Car implements Serializable {

	private static final long serialVersionUID = 8280121398732062094L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int carId;
	
	@Column(name="userId",nullable=false)
	private int userId;         // 用户id
	
	@Column(name="girlId",nullable=false)
	private int girlId;         // girlid
	
	@Column(name="delflag",columnDefinition="INT default 0")
	private int delflag;        // 删除标志，0-未删除，1-删除

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGirlId() {
		return girlId;
	}

	public void setGirlId(int girlId) {
		this.girlId = girlId;
	}
	
	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	public Car() {
		super();
	}

	public Car(int userId, int girlId) {
		super();
		this.userId = userId;
		this.girlId = girlId;
	}
	
}
