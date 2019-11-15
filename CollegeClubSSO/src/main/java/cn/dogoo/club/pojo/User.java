package cn.dogoo.club.pojo;

import java.io.Serializable;

public class User implements Serializable {


	private String userUid;
	private String userId;//学号
	private String userPassword;//密码
	private String userName;//真实姓名
	private String userGender;//性别
	private String userClass;//班级
	private String userEmail;//email邮箱
	private String userQQ;//QQ
	private String userPhone;//手机号（长号）
	private String userPhonemin;//手机号（短号）
	private Integer userGrade;//学分
	private Integer userType;//用户类型
	
	
	
	public String getUserUid() {
		return userUid;
	}
	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}
	public Integer getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(Integer userGrade) {
		this.userGrade = userGrade;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserClass() {
		return userClass;
	}
	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPhonemin() {
		return userPhonemin;
	}
	public void setUserPhonemin(String userPhonemin) {
		this.userPhonemin = userPhonemin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userClass == null) {
			if (other.userClass != null)
				return false;
		} else if (!userClass.equals(other.userClass))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userGender == null) {
			if (other.userGender != null)
				return false;
		} else if (!userGender.equals(other.userGender))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userPhone == null) {
			if (other.userPhone != null)
				return false;
		} else if (!userPhone.equals(other.userPhone))
			return false;
		if (userPhonemin == null) {
			if (other.userPhonemin != null)
				return false;
		} else if (!userPhonemin.equals(other.userPhonemin))
			return false;
		if (userQQ == null) {
			if (other.userQQ != null)
				return false;
		} else if (!userQQ.equals(other.userQQ))
			return false;
		return true;
	}
	
	
	 
	
	

}
