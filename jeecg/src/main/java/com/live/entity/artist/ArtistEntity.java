package com.live.entity.artist;

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
 * @Description: 艺人表
 * @author zhangdaihao
 * @date 2018-01-21 20:26:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "a_artist", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ArtistEntity implements java.io.Serializable {
	/**ID*/
	private java.lang.String id;
	/**昵称*/
	private java.lang.String nick;
	/**真实姓名*/
	private java.lang.String realname;
	/**性别(0:男,1:女)*/
	private java.lang.Integer sex;
	/**身份证*/
	private java.lang.String idcard;
	/**QQ*/
	private java.lang.String qq;
	/**主播类型*/
	private java.lang.Integer type;
	/**申请时间*/
	private java.util.Date applytime;
	/**入驻时间*/
	private java.util.Date entertime;
	/**粉丝数*/
	private java.lang.Integer fans;
	/**等级*/
	private java.lang.Integer levels;
	/**总收益*/
	private java.lang.Double profit;
	/**直播小时数*/
	private java.lang.Double livetime;
	/**达标天数*/
	private java.lang.Integer standarddays;
	/**家族id*/
	private java.lang.String orgname;
	private java.lang.String orgid;
	/**直播状态(0：休息	1：直播)*/
	private java.lang.Integer livestatus;
	/**艺人状态(0：正常	1：申请	2：解约)*/
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
	 *@return: java.lang.String  昵称
	 */
	@Column(name ="NICK",nullable=true,length=100)
	public java.lang.String getNick(){
		return this.nick;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  昵称
	 */
	public void setNick(java.lang.String nick){
		this.nick = nick;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  真实姓名
	 */
	@Column(name ="REALNAME",nullable=true,length=100)
	public java.lang.String getRealname(){
		return this.realname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  真实姓名
	 */
	public void setRealname(java.lang.String realname){
		this.realname = realname;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  性别(0:男,1:女)
	 */
	@Column(name ="SEX",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  性别(0:男,1:女)
	 */
	public void setSex(java.lang.Integer sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证
	 */
	@Column(name ="IDCARD",nullable=true,length=20)
	public java.lang.String getIdcard(){
		return this.idcard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证
	 */
	public void setIdcard(java.lang.String idcard){
		this.idcard = idcard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  QQ
	 */
	@Column(name ="QQ",nullable=true,length=40)
	public java.lang.String getQq(){
		return this.qq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  QQ
	 */
	public void setQq(java.lang.String qq){
		this.qq = qq;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  主播类型
	 */
	@Column(name ="TYPE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  主播类型
	 */
	public void setType(java.lang.Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请时间
	 */
	@Column(name ="APPLyTIME",nullable=true)
	public java.util.Date getApplytime(){
		return this.applytime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请时间
	 */
	public void setApplytime(java.util.Date applytime){
		this.applytime = applytime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入驻时间
	 */
	@Column(name ="ENTERTIME",nullable=true)
	public java.util.Date getEntertime(){
		return this.entertime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入驻时间
	 */
	public void setEntertime(java.util.Date entertime){
		this.entertime = entertime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  粉丝数
	 */
	@Column(name ="FANS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getFans(){
		return this.fans;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  粉丝数
	 */
	public void setFans(java.lang.Integer fans){
		this.fans = fans;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  等级
	 */
	@Column(name ="LEVELS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getLevels(){
		return this.levels;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  等级
	 */
	public void setLevels(java.lang.Integer levels){
		this.levels = levels;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  总收益
	 */
	@Column(name ="PROFIT",nullable=true,precision=22)
	public java.lang.Double getProfit(){
		return this.profit;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  总收益
	 */
	public void setProfit(java.lang.Double profit){
		this.profit = profit;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  直播小时数
	 */
	@Column(name ="LIVETIME",nullable=true,precision=22)
	public java.lang.Double getLivetime(){
		return this.livetime;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  直播小时数
	 */
	public void setLivetime(java.lang.Double livetime){
		this.livetime = livetime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  达标天数
	 */
	@Column(name ="STANDARDDAYS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getStandarddays(){
		return this.standarddays;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  达标天数
	 */
	public void setStandarddays(java.lang.Integer standarddays){
		this.standarddays = standarddays;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家族id
	 */
	@Column(name ="ORGID",nullable=true,length=32)
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

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家族名
	 */
	@Column(name ="ORGNAME",nullable=true,length=32)
	public java.lang.String getOrgname(){
		return this.orgname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家族名
	 */
	public void setOrgname(java.lang.String orgname){
		this.orgname = orgname;
	}


	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  直播状态(0：休息	1：直播)
	 */
	@Column(name ="LIVESTATUS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getLivestatus(){
		return this.livestatus;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  直播状态(0：休息	1：直播)
	 */
	public void setLivestatus(java.lang.Integer livestatus){
		this.livestatus = livestatus;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  艺人状态(0：正常	1：申请	2：解约)
	 */
	@Column(name ="STATE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  艺人状态(0：正常	1：申请	2：解约)
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
}
