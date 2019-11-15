package cn.dogoo.club.pojo;

import java.util.Date;

/**
 * 社团留言
 * 
 * @author Dogoo
 *
 */
public class ClubMsg {
	private String cmUid;
	private String clubUid;
	private String userUid;
	private String userName;
	private String cmTitle;
	private String cmBody;
	private Date cmTime;
	
	
	
	public String getClubUid() {
		return clubUid;
	}

	public void setClubUid(String clubUid) {
		this.clubUid = clubUid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCmUid() {
		return cmUid;
	}

	public void setCmUid(String cmUid) {
		this.cmUid = cmUid;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public String getCmTitle() {
		return cmTitle;
	}

	public void setCmTitle(String cmTitle) {
		this.cmTitle = cmTitle;
	}

	public String getCmBody() {
		return cmBody;
	}

	public void setCmBody(String cmBody) {
		this.cmBody = cmBody;
	}

	public Date getCmTime() {
		return cmTime;
	}

	public void setCmTime(Date cmTime) {
		this.cmTime = cmTime;
	}




}
