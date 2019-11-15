package cn.dogoo.club.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dogoo.club.manager.service.ClubChooseService;
import cn.dogoo.club.manager.service.ClubService;
import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.ClubChoose;
import cn.dogoo.common.tip.RedisTip;

@RestController
public class ClubChooseController {

	@Autowired
	private ClubChooseService ccservice;
	@Autowired
	private ClubService clubService;
	
	@RequestMapping("/clubchoose/checkisclub")
	public String checkisclub(String userUid,String clubUid){		
		return ccservice.checkisclub(userUid,clubUid)+"";		
	}
	
	@RequestMapping("/clubchoose/Join")
	public String joinClub(String userUid,String clubUid){	
		String reslut=ccservice.joinClub(userUid,clubUid)+"";
		try{
			clubService.updateClubNownumber(clubUid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return reslut;	
	}
	
	@RequestMapping("/clubchoose/left")
	public String leftClub(String userUid,String clubUid){	
		String reslut=ccservice.leftClub(userUid,clubUid)+"";
		try{
			clubService.updateClubNownumber(clubUid);
		}catch(Exception e){
			e.printStackTrace();
		}
		 return reslut;	
	}
	
	
	@RequestMapping("/club/myclub/{userId}")
	public List<Club> querymyClub(@PathVariable String userId){
		List<ClubChoose> clubcs=ccservice.queryAllClubByuserId(userId);
		List<Club> clubs=clubService.queryclubByClubid(clubcs);
		return clubs;		
	}
	
	
	@RequestMapping("/clubchoose/user/{clubUid}")
	public List<ClubChoose> queryusers(@PathVariable String clubUid,Integer page){
		Integer rows=RedisTip.Page_Rows;
		List<ClubChoose> clubcs=ccservice.queryAllUserByclubUid(clubUid,page,rows);
		return clubcs;
	}
	
	@RequestMapping("/clubchoose/user/total/{clubUid}")
	public String queryusers(@PathVariable String clubUid){
		int clubcscount=ccservice.queryClubUserCount(clubUid);
		return clubcscount+"";
	}
	
	@RequestMapping("/clubchoose/update/cutype")
	public String updateClubUserType(String userUid,String clubUid,Integer type){
		int success=ccservice.updateClubUsertype(userUid, clubUid, type);
		System.out.println(success);
		return success+"";
	}
	
}


