package com.live.entity.fans;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 粉丝记录表
 * @author zhangdaihao
 * @date 2018-01-21 23:49:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "a_fansrecord", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FansRecordEntity implements java.io.Serializable {
	/**ID*/
	private java.lang.String id;
	/**艺人ID*/
	private java.lang.String artistid;
	/**艺人姓名*/
	private java.lang.String artistname;
	/**关注时间*/
	private java.util.Date attentiondate;
	/**取消时间*/
	private java.util.Date canceldate;
	/**粉丝qq*/
	private java.lang.String fansqq;
	/**累计贡献金额*/
	private java.lang.Double amounts;
	/**状态：0关注	1取消关注*/
	private java.lang.Integer state;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ID
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ID
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String 艺人ID
	 */
	@Column(name ="ARTISTID",nullable=true)
	public java.lang.String getArtistid(){
		return this.artistid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  艺人昵称
	 */
	public void setArtistname(java.lang.String artistname){
		this.artistname = artistname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String 艺人昵称
	 */
	@Column(name ="ARTISTNAME",nullable=true)
	public java.lang.String getArtistname(){
		return this.artistname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  艺人ID
	 */
	public void setArtistid(java.lang.String artistid){
		this.artistid = artistid;
	}

	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  关注时间
	 */
	@Column(name ="ATTENTIONDATE",nullable=true)
	public java.util.Date getAttentiondate(){
		return this.attentiondate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  关注时间
	 */
	public void setAttentiondate(java.util.Date attentiondate){
		this.attentiondate = attentiondate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  取消时间
	 */
	@Column(name ="CANCELDATE",nullable=true)
	public java.util.Date getCanceldate(){
		return this.canceldate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  取消时间
	 */
	public void setCanceldate(java.util.Date canceldate){
		this.canceldate = canceldate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  粉丝qq
	 */
	@Column(name ="FANSQQ",nullable=true,length=20)
	public java.lang.String getFansqq(){
		return this.fansqq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  粉丝qq
	 */
	public void setFansqq(java.lang.String fansqq){
		this.fansqq = fansqq;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态：0关注	1取消关注
	 */
	@Column(name ="STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态：0关注	1取消关注
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
	@Column(name ="AMOUNTS",nullable=true,precision=10,scale=0)
	public Double getAmounts() {
		return amounts;
	}

	public void setAmounts(Double amounts) {
		this.amounts = amounts;
	}
}
