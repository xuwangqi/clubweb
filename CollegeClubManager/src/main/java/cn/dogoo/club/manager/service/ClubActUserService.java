package cn.dogoo.club.manager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dogoo.club.manager.mapper.ClubActMapper;
import cn.dogoo.club.manager.mapper.ClubActUserMapper;
import cn.dogoo.club.pojo.ClubActUser;
import cn.dogoo.club.pojo.ClubActivity;
import cn.dogoo.club.pojo.User;
import cn.dogoo.common.util.UUIDUtil;

@Service
public class ClubActUserService {

	@Autowired
	private ClubActUserMapper actuserMapper;
	@Autowired
	private ClubActMapper actMapper;
	
	public int queryUserInAct(String caUid, String userUid) {
		return actuserMapper.queryUserInAct(caUid,userUid);
	}

	public int insertInClub(String caUid, User user) {
		ClubActUser actuser=new ClubActUser();
		actuser.setCauUid(UUIDUtil.getUUID());
		actuser.setCaUid(caUid);
		actuser.setUserUid(user.getUserUid());
		actuser.setUserName(user.getUserName());
		actuser.setUserId(user.getUserId());
		actuser.setCauTime(new Date());
		return actuserMapper.inertClubAct(actuser);
	}

	public int deleteInClub(String caUid, String userUid) {
		return actuserMapper.deleteClubAct(caUid,userUid);
	}

	
	public int updateClubActUser(ClubActUser clubuser) {
		return actuserMapper.updateUserInAct(clubuser);
	}

	public int updateClubActUserScore(String ids,String caUid, Integer score) {
		String[] idss=ids.split(",");
		int size=actuserMapper.updateUserInActScore(score,caUid,idss);
 		return size==idss.length?1:0;
	}

	@Transactional
	public int updateClubActUserPF(String userUid, String caUid, Integer number, String msg) {
		int success=actuserMapper.updateUserPF(userUid, caUid, number, msg);
		if(success==1){
			ClubActivity act=actMapper.queryClubActDetail(caUid);
			if(act.getCaScore()==0){
				actMapper.updateClubActScore(caUid, number);
			}else{
				actMapper.updateClubActScore(caUid, (int)((act.getCaScore()+number)/2));
			}
		}
		return success;
	}

	public ClubActUser queryActUser(String userUid, String caUid) {
		return actuserMapper.querActUser(userUid, caUid);
	}

	
}
