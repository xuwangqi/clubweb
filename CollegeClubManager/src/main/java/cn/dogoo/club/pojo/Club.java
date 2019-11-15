package cn.dogoo.club.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Club implements Serializable{
	
	//申请者一定先成为社长
	//社团管理员会自动加入社团
	
	private String clubUid;
	private String clubId;//社团编号
	private String clubName;//社团名称
	private String clubSay;//社团口号	
	private String userId;//社长学号
	private String userName;//社长名字
	private Integer clubNownum;//现在人数
	private Integer clubTotalnum;//规定人数
	private String clubLogourl;//图片
	private Date clubTime; //成立时间
	private String clubDesc;
	private Integer clubType;
	
	
	
	
	public Integer getClubType() {
		return clubType;
	}
	public void setClubType(Integer clubType) {
		this.clubType = clubType;
	}
	public String getClubUid() {
		return clubUid;
	}
	public void setClubUid(String clubUid) {
		this.clubUid = clubUid;
	}
	public String getClubId() {
		return clubId;
	}
	public void setClubId(String clubId) {
		this.clubId = clubId;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getClubSay() {
		return clubSay;
	}
	public void setClubSay(String clubSay) {
		this.clubSay = clubSay;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getClubNownum() {
		return clubNownum;
	}
	public void setClubNownum(Integer clubNownum) {
		this.clubNownum = clubNownum;
	}
	public Integer getClubTotalnum() {
		return clubTotalnum;
	}
	public void setClubTotalnum(Integer clubTotalnum) {
		this.clubTotalnum = clubTotalnum;
	}
	public String getClubLogourl() {
		return clubLogourl;
	}
	public void setClubLogourl(String clubLogourl) {
		this.clubLogourl = clubLogourl;
	}
	
	public Date getClubTime() {
		return clubTime;
	}
	public void setClubTime(Date clubTime) {
		this.clubTime = clubTime;
	}
	public String getClubDesc() {
		return clubDesc;
	}
	public void setClubDesc(String clubDesc) {
		this.clubDesc = clubDesc;
	}
	@Override
	public String toString() {
		return "Club [clubUid=" + clubUid + ", clubId=" + clubId + ", clubName=" + clubName + ", clubSay=" + clubSay
				+ ", userId=" + userId + ", userName=" + userName + ", clubNownum=" + clubNownum + ", clubTotalnum="
				+ clubTotalnum + ", clubLogourl=" + clubLogourl + ", clubTime=" + clubTime + ", clubDesc=" + clubDesc
				+ "]";
	}
	
	
	


}
