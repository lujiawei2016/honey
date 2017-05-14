package com.weixin.honey.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 评论表
 * @author   jiawei
 * @version  V1.0
 * @date     2017年5月13日下午3:38:10
 */
@Entity
@Table(name="tb_comments")
public class Comments implements Serializable {
	
	private static final long serialVersionUID = -3254611286184355745L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentsId;                // 主键
	
	@Column(name="description",nullable=false)
	private String description;            // 评论内容
	
	@Column(name="createTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;               // 评论时间
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_is")
	@JsonIgnore
	private User user;

	@ManyToOne(targetEntity=Girl.class)
	@JoinColumn(name="girl_id")
	@JsonIgnore
	private Girl girl;

	public int getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(int commentsId) {
		this.commentsId = commentsId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Girl getGirl() {
		return girl;
	}

	public void setGirl(Girl girl) {
		this.girl = girl;
	}
	
}
