package com.live.entity.live;

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
 * @Description: 直播记录表
 * @author zhangdaihao
 * @date 2018-01-28 10:49:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "a_liverecord", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LiveRecordEntity implements java.io.Serializable {
	/**ID*/
	private java.lang.String id;
	/**艺人ID*/
	private java.lang.String artistid;
	/**开始时间*/
	private java.util.Date starttime;
	/**结束时间*/
	private java.util.Date endtime;
	/**直播分钟数*/
	private java.lang.Integer minute;
	/**状态：0正在直播，1结束直播*/
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
	@Column(name ="ARTISTID",nullable=true,length=32)
	public java.lang.String getArtistid(){
		return this.artistid;
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
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="STARTTIME",nullable=true)
	public java.util.Date getStarttime(){
		return this.starttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStarttime(java.util.Date starttime){
		this.starttime = starttime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="ENDTIME",nullable=true)
	public java.util.Date getEndtime(){
		return this.endtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndtime(java.util.Date endtime){
		this.endtime = endtime;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  直播分钟数
	 */
	@Column(name ="MINUTE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMinute(){
		return this.minute;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  直播分钟数
	 */
	public void setMinute(java.lang.Integer minute){
		this.minute = minute;
	}

	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  状态：0正在直播，1结束直播
	 */
	@Column(name ="STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  状态：0正在直播，1结束直播
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
}
