package com.live.entity.income;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 收益记录表
 * @author zhangdaihao
 * @date 2018-01-21 21:52:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "view_artist_income", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ArtistIncomeViewEntity implements java.io.Serializable {
	/**
	 * ID
	 */
	private String id;
	/**
	 * 艺人ID
	 */
	private String artistId;
	/**
	 * 粉丝id
	 */
	private String fansqq;
	/**
	 * 收益金额
	 */
	private Double amounts;
	/**
	 * 收益时间
	 */
	private Date createdate;
	/***/
	private String nick;
	/**
	 * 家族名称
	 */
	private String departname;

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String  ID
	 */

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String  ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String  艺人ID
	 */
	@Column(name = "ARTIST_ID", nullable = false, length = 32)
	public String getArtistId() {
		return this.artistId;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String  艺人ID
	 */
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	/**
	 * 方法: 取得java.lang.String
	 *
	 * @return: java.lang.String  粉丝qq
	 */
	@Column(name = "FANSQQ", nullable = false, length = 32)
	public String getFansqq() {
		return this.fansqq;
	}

	/**
	 * 方法: 设置java.lang.String
	 *
	 * @param: java.lang.String  粉丝qq
	 */
	public void setFansqq(String fansqq) {
		this.fansqq = fansqq;
	}

	/**
	 * 方法: 取得java.lang.Double
	 *
	 * @return: java.lang.Double  收益金额
	 */
	@Column(name = "AMOUNTS", nullable = true, precision = 22)
	public Double getAmounts() {
		return this.amounts;
	}

	/**
	 * 方法: 设置java.lang.Double
	 *
	 * @param: java.lang.Double  收益金额
	 */
	public void setAmounts(Double amounts) {
		this.amounts = amounts;
	}

	/**
	 * 方法: 取得java.util.Date
	 *
	 * @return: java.util.Date  收益时间
	 */
	@Column(name = "CREATEDATE", nullable = true)
	public Date getCreatedate() {
		return this.createdate;
	}

	/**
	 * 方法: 设置java.util.Date
	 *
	 * @param: java.util.Date  收益时间
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Column(name = "NICK", nullable = true)
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Column(name = "DEPARTNAME", nullable = true)
	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}
}