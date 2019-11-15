package cn.dogoo.club.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dogoo.club.manager.mapper.ClubChooseMapper;
import cn.dogoo.club.manager.mapper.ClubMapper;
import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.ClubChoose;
import cn.dogoo.common.util.UUIDUtil;

@Service
@Transactional
public class ClubService {

	@Autowired
	private ClubMapper clubmapper;
	@Autowired
	private ClubChooseMapper ccMapper;

	public List<Club> queryclubByPage(Integer page, Integer rows,Integer clubType) {
		int start = (page - 1) * rows;
		List<Club> products = clubmapper.queryList(start, rows,clubType);
		return products;
	}

	public String queryCount(Integer clubType) {
		return clubmapper.queryCount(clubType) + "";
	}

	public Club queryDetailClub(String uid) {

		return clubmapper.queryClubByUid(uid);
	}

	public int addClub(Club club) {
		club.setClubUid(UUIDUtil.getUUID());
		club.setClubNownum(0);
		return clubmapper.insertClub(club);
	}

	public int updateClub(Club club) {
		return clubmapper.updateClub(club);
	}

	@Transactional
	public int deleteClub(String ids) {
		String[] idss = ids.split(",");
		int success = clubmapper.deleteClub(idss);
		if (success == idss.length) {
			success=ccMapper.deleteClubs(idss);
			if(success>0){
			    return 1;
			}else{
				return 0;
			}
		} else {
			return 0;
		}
	}

	public List<Club> queryclubByClubid(List<ClubChoose> clubcs) {
		return clubmapper.queryclubByClubid(clubcs);
	}

	public int updatepass(String type, String ids) {	
		String[] idss = ids.split(",");
		int success = clubmapper.updatepass(type,idss);
		if (success == idss.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	//维护模块,防止各种原因造成人数不对等问题,降低性能提高可靠性
	public void updateClubNownumber(String clubUid){
	    int totalnumber=ccMapper.queryclubUsercount(clubUid);
	    Club club=clubmapper.queryClubByUid(clubUid);
	    if(totalnumber!=club.getClubNownum()){
	    	int type=totalnumber-club.getClubNownum();
	    	clubmapper.updateClubNowNumer(type, clubUid);
	    }				
	}
	
}
