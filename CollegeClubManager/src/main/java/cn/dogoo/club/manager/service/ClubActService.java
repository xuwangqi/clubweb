package cn.dogoo.club.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dogoo.club.manager.mapper.ClubActMapper;
import cn.dogoo.club.manager.mapper.ClubActUserMapper;
import cn.dogoo.club.pojo.ClubActUser;
import cn.dogoo.club.pojo.ClubActivity;
import cn.dogoo.common.util.UUIDUtil;

@Service
public class ClubActService {

	@Autowired
	private ClubActMapper actmapper;
	
	@Autowired
	private ClubActUserMapper actusermapper;
	
	public int addClubAct(ClubActivity clubact) {
		clubact.setCaUid(UUIDUtil.getUUID());
		return actmapper.insertClubAct(clubact);
	}

	public List<ClubActivity> queryClubActByClub(String clubUid, Integer type, Integer page,int rows) {
		int start = (page - 1) * rows;
		List<ClubActivity> list=actmapper.queryClubActByClub(clubUid, type, start, rows);	
		return list;
	}

	public int queryClubActCountByClub(String clubUid, Integer type) {
		return actmapper.queryClubActCountByClub(clubUid, type);
	}

	public ClubActivity queryClubActDetail(String caUid) {
		ClubActivity clubact=actmapper.queryClubActDetail(caUid);
		int clubactNumber=actmapper.queryCountClubActUser(caUid);
		actmapper.updateClubActNowNumber(clubactNumber, caUid);	
		List<ClubActUser> users=actusermapper.queryUserBycaUid(clubact.getCaUid());
		clubact.setClubuser(users);	
		clubact.setCaNownumber(clubactNumber);
		return clubact;
	}

	public List<ClubActivity> queryClubAct(Integer type, Integer page, Integer rows) {
		int start = (page - 1) * rows;
		List<ClubActivity> list=actmapper.queryClubAct(type, start, rows);	
		return list;
	}

	public int queryClubActCount(Integer type) {
		return actmapper.queryClubActCount(type);
	}

	public String deleteClubAct(String ids) {
		String[] idss=ids.split(",");
		int size=actmapper.deleteClubAct(idss);
		return size==idss.length?"1":"0";
	}

	public String updateClubActPass(Integer type, String ids) {
		String[] idss=ids.split(",");
		int size=actmapper.updateClubActPass(type,idss);
		return size==idss.length?"1":"0";
	}

	public int updateClubAct(ClubActivity clubact) {
		int res=actmapper.updateClubAct(clubact);
		return res;
	}

	public List<ClubActivity> queryMyClubActByClub(String clubUid, String userUid, Integer type, Integer page,
			Integer rows) {
		int start = (page - 1) * rows;
	    List<ClubActUser> user = actusermapper.queryMyAct(userUid); 
	    List<ClubActivity> list=actmapper.queryMyAct(user,clubUid,type,start,rows);
		return list;
	}

	public int queryMyClubActCountByClub(String clubUid, String userUid, Integer type) {
		 List<ClubActUser> user = actusermapper.queryMyAct(userUid); 
		 int size=actmapper.queryMyActCount(user,clubUid,type);
		 return size;
	}

	public List<ClubActivity> queryMyClubActByClub(String userUid, Integer type, Integer page, Integer rows) {
		int start = (page - 1) * rows;
	    List<ClubActUser> user = actusermapper.queryMyAct(userUid); 
	    List<ClubActivity> list=actmapper.queryMyAllAct(user,type,start,rows);
		return list;
	}

	public int queryMyClubActCountByClub(String userUid, Integer type) {
		 List<ClubActUser> user = actusermapper.queryMyAct(userUid); 
		 int size=actmapper.queryMyAllActCount(user,type);
		 return size;
	}

	public List<ClubActUser> queryMyClubActUserByClub(String caUid, Integer page,int rows) {
		int start = (page - 1) * rows;
		List<ClubActUser> list = actusermapper.queryUserBycaUidByLimit(caUid,start,rows);			 
		 return list;
	}

	public int queryMyClubActUserByClubSize(String caUid) {
		return actusermapper.queryUserBycaUidTotal(caUid);
	}


}
