package cn.dogoo.club.pojo;

import java.util.Date;
import java.util.List;

/**
 * 学生参加活动
 * 
 * @author Dogoo
 *
 */
public class ClubActUser {
	
	private String cauUid;
	private String caUid;
	private String userId;
	private String userUid;
	private String userName;
	private Integer cauScore;// 参加活动获得的基础分数
	private Integer cauExscore;// 参加互动获得额外分数
	private Integer caScore;// 对活动的评分
	private Date cauTime;// 时间
	private String cauMsg;
    
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCauMsg() {
		return cauMsg;
	}

	public void setCauMsg(String cauMsg) {
		this.cauMsg = cauMsg;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getCauUid() {
		return cauUid;
	}

	public void setCauUid(String cauUid) {
		this.cauUid = cauUid;
	}

	public String getCaUid() {
		return caUid;
	}

	public void setCaUid(String caUid) {
		this.caUid = caUid;
	}

	public Integer getCauScore() {
		return cauScore;
	}

	public void setCauScore(Integer cauScore) {
		this.cauScore = cauScore;
	}

	public Integer getCauExscore() {
		return cauExscore;
	}

	public void setCauExscore(Integer cauExscore) {
		this.cauExscore = cauExscore;
	}

	public Integer getCaScore() {
		return caScore;
	}

	public void setCaScore(Integer caScore) {
		this.caScore = caScore;
	}

	public Date getCauTime() {
		return cauTime;
	}

	public void setCauTime(Date cauTime) {
		this.cauTime = cauTime;
	}



    
}
