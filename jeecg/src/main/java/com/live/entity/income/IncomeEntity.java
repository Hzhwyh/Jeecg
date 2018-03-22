package com.live.entity.income;

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
 * @Description: 收益记录表
 * @author zhangdaihao
 * @date 2018-01-21 21:52:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "a_income", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class IncomeEntity implements java.io.Serializable {
	/**ID*/
	private java.lang.String id;
	/**艺人ID*/
	private java.lang.String artistId;
	/**粉丝id*/
	private java.lang.String fansqq;
	/**收益金额*/
	private java.lang.Double amounts;
	/**收益时间*/
	private java.util.Date createdate;
	/**家族ID*/
	private java.lang.String orgid;
	/**结算状态（0未结算，1已结算）*/
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
	 *@return: java.lang.String  艺人ID
	 */
	@Column(name ="ARTIST_ID",nullable=false,length=32)
	public java.lang.String getArtistId(){
		return this.artistId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  艺人ID
	 */
	public void setArtistId(java.lang.String artistId){
		this.artistId = artistId;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  粉丝qq
	 */
	@Column(name ="FANSQQ",nullable=false,length=32)
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  收益金额
	 */
	@Column(name ="AMOUNTS",nullable=true,precision=22)
	public java.lang.Double getAmounts(){
		return this.amounts;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  收益金额
	 */
	public void setAmounts(java.lang.Double amounts){
		this.amounts = amounts;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  收益时间
	 */
	@Column(name ="CREATEDATE",nullable=true)
	public java.util.Date getCreatedate(){
		return this.createdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  收益时间
	 */
	public void setCreatedate(java.util.Date createdate){
		this.createdate = createdate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  结算状态（0未结算，1已结算）
	 */
	@Column(name ="STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  结算状态（0未结算，1已结算）
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家族id
	 */
	@Column(name ="ORGID",nullable=false,length=32)
	public java.lang.String getOrgid(){
		return this.orgid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家族id
	 */
	public void setOrgid(java.lang.String orgid){
		this.orgid = orgid;
	}
}
