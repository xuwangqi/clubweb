package cn.dogoo.club.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 管理员权限:社团管理员能添加和修改社团活动信息
 * 
 * 社团活动信息: 唯一标识: 包含活动内容 包含活动时间 包含活动类型
 * 活动的时间格式统一到秒
 * @author Dogoo
 *
 */
public class ClubActivity implements Serializable {
	/**
	 * 成绩录入采取的是人工调查,通过社团管理员输入信息即可
	 */
	private String caUid;// uid
	private String clubUid;// 社团
	private String clubId;//社团编号
	private String clubName;//社团名称
	private Integer caScore;
	private String caTitle;// 活动标题
	private String caBody;// 活动主题
	private String caAddress;// 活动地址
	private Integer caNownumber;//现在人数-二次获取
	private Integer caNumber;// 活动人数
	private Date caTime;// 参加活动的时间
	private Integer caGrade;// 参加活动基础学分
	private String caFile;//活动文件
	private Integer caType;//活动状态 //-1 活动未通过申请 0 表示活动通过申请 1表示活动已进行、
	//活动控制 -1 -> 0 系统管理员   0 ->  1社团管理员   
	private String caRemsg;
	
	private List<ClubActUser> clubuser;
	
	
	
	
	public Integer getCaScore() {
		return caScore;
	}
	public void setCaScore(Integer caScore) {
		this.caScore = caScore;
	}
	public String getCaRemsg() {
		return caRemsg;
	}
	public void setCaRemsg(String caRemsg) {
		this.caRemsg = caRemsg;
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
	public String getCaFile() {
		return caFile;
	}
	public void setCaFile(String caFile) {
		this.caFile = caFile;
	}
	public Integer getCaType() {
		return caType;
	}
	public void setCaType(Integer caType) {
		this.caType = caType;
	}
	public Integer getCaNownumber() {
		return caNownumber;
	}
	public void setCaNownumber(Integer caNownumber) {
		this.caNownumber = caNownumber;
	}
	public List<ClubActUser> getClubuser() {
		return clubuser;
	}
	public void setClubuser(List<ClubActUser> clubuser) {
		this.clubuser = clubuser;
	}
	public String getClubUid() {
		return clubUid;
	}
	public void setClubUid(String clubUid) {
		this.clubUid = clubUid;
	}
	public String getCaUid() {
		return caUid;
	}
	public void setCaUid(String caUid) {
		this.caUid = caUid;
	}
	public String getCaTitle() {
		return caTitle;
	}
	public void setCaTitle(String caTitle) {
		this.caTitle = caTitle;
	}
	public String getCaBody() {
		return caBody;
	}
	public void setCaBody(String caBody) {
		this.caBody = caBody;
	}
	public String getCaAddress() {
		return caAddress;
	}
	public void setCaAddress(String caAddress) {
		this.caAddress = caAddress;
	}
	public Integer getCaNumber() {
		return caNumber;
	}
	public void setCaNumber(Integer caNumber) {
		this.caNumber = caNumber;
	}
	
	public Date getCaTime() {
		return caTime;
	}
	public void setCaTime(Date caTime) {
		this.caTime = caTime;
	}
	public Integer getCaGrade() {
		return caGrade;
	}
	public void setCaGrade(Integer caGrade) {
		this.caGrade = caGrade;
	}
	
	

}
