package cn.dogoo.club.manager.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dogoo.club.manager.mapper.ClubChooseMapper;
import cn.dogoo.club.manager.mapper.ClubMapper;
import cn.dogoo.club.pojo.ClubChoose;
import cn.dogoo.common.util.UUIDUtil;

@Service
public class ClubChooseService {

	@Autowired
	private ClubChooseMapper ccmapper;
	
	@Autowired
	private ClubMapper clubmapper;
	
	public String checkisclub(String userUid, String clubUid) {	
		return ccmapper.queryCountByUC(userUid,clubUid)+"";
	}

	@Transactional
	public int joinClub(String userUid, String clubUid) {
		ClubChoose cc=new ClubChoose();
		cc.setCuUid(UUIDUtil.getUUID());
		cc.setClubUid(clubUid);
		cc.setUserUid(userUid);
		cc.setCuTime(new Date());
		cc.setCuType(0);
		int success=ccmapper.insertClub(cc);
		if(success==1){
	    success=clubmapper.updateClubNowNumer(1,clubUid);
		}
		return success;
	}

	public List<ClubChoose> queryAllClubByuserId(String userId) {	
		return ccmapper.queryclubsByuserId(userId);
	}

	public List<ClubChoose> queryAllUserByclubUid(String clubUid,Integer page,Integer rows) {		
		int start = (page - 1) * rows;		
		List<ClubChoose> list=ccmapper.queryusersByclubuid(clubUid, start, rows);
		return list;
	}

	public int queryClubUserCount(String clubUid) {
		return ccmapper.queryClubUserCount(clubUid);
	}

	@Transactional
	public int leftClub(String userUid, String clubUid) {
		int success=ccmapper.deleteClubChoose(clubUid,userUid);
		
		if(success==1){
			success=clubmapper.updateClubNowNumer(-1,clubUid);
		}
		return success;
	}

	public int updateClubUsertype(String userUid, String clubUid,Integer cuType){
		System.out.println(userUid+"*"+clubUid+"*"+cuType);
		return ccmapper.updatecuType(userUid, clubUid, cuType);
	}
	
	
	

}
