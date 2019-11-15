package cn.dogoo.club.pojo;

import java.io.Serializable;
import java.util.Date;

public class ClubChoose implements Serializable {

	private String cuUid;
	private String userUid;
	private String clubUid;
	private Date cuTime;
	private Integer cuType;
	
	private Club club;
	private User user;

		
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Date getCuTime() {
		return cuTime;
	}

	public void setCuTime(Date cuTime) {
		this.cuTime = cuTime;
	}

	public String getCuUid() {
		return cuUid;
	}

	public void setCuUid(String cuUid) {
		this.cuUid = cuUid;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public String getClubUid() {
		return clubUid;
	}

	public void setClubUid(String clubUid) {
		this.clubUid = clubUid;
	}

	public Integer getCuType() {
		return cuType;
	}

	public void setCuType(Integer cuType) {
		this.cuType = cuType;
	}

	@Override
	public String toString() {
		return "ClubChoose [cuUid=" + cuUid + ", userUid=" + userUid + ", clubUid=" + clubUid + ", cuTime=" + cuTime
				+ ", cuType=" + cuType + "]";
	}


}
