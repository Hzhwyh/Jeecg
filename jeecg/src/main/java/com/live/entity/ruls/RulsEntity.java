package com.live.entity.ruls;

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
 * @Description: 家族规则表
 * @author zhangdaihao
 * @date 2018-01-21 23:48:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "a_ruls", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RulsEntity implements java.io.Serializable {
	/**ID*/
	private java.lang.String id;
	/**家族ID*/
	private java.lang.String orgid;
	/**家族名称*/
	private java.lang.String orgname;
	/**达标小时*/
	private java.lang.Double standard;
	/**状态标志:0生效  1失效*/
	private java.lang.Integer state;
	/**创建时间*/
	private java.util.Date createdate;

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
	 *@return: java.lang.String  家族ID
	 */
	@Column(name ="ORGID",nullable=true,length=32)
	public java.lang.String getOrgid(){
		return this.orgid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家族ID
	 */
	public void setOrgid(java.lang.String orgid){
		this.orgid = orgid;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家族名称
	 */
	@Column(name ="ORGNAME",nullable=true,length=100)
	public java.lang.String getOrgname(){
		return this.orgname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家族名称
	 */
	public void setOrgname(java.lang.String orgname){
		this.orgname = orgname;
	}

	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  达标小时
	 */
	@Column(name ="STANDARD",nullable=true,precision=22)
	public java.lang.Double getStandard(){
		return this.standard;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  达标小时
	 */
	public void setStandard(java.lang.Double standard){
		this.standard = standard;
	}

	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  状态标志
	 */
	@Column(name ="STATE",nullable=true,precision=22)
	public java.lang.Integer getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  状态标志
	 */
	public void setState(java.lang.Integer state){
		this.state = state;
	}

	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date	创建时间
	 */
	@Column(name ="CREATEDATE",nullable=true,precision=22)
	public java.util.Date getCreatedate(){
		return this.createdate;
	}

	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date	创建时间
	 */
	public void setCreatedate(java.util.Date createdate){
		this.createdate = createdate;
	}
}
