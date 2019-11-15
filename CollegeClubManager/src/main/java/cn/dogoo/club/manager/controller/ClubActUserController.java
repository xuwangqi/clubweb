package cn.dogoo.club.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cn.dogoo.club.manager.service.ClubActService;
import cn.dogoo.club.manager.service.ClubActUserService;
import cn.dogoo.club.pojo.ClubActUser;
import cn.dogoo.club.pojo.ClubActivity;
import cn.dogoo.club.pojo.User;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.util.ObjectUtil;

@RestController
public class ClubActUserController {

	@Autowired
	private ClubActUserService actuserService;

	@Autowired
	private ClubActService actService;

	@RequestMapping("/clubact/user/check")
	public String checkUserInAct(@RequestParam("caUid") String caUid, @RequestParam("userUid") String userUid) {
		int success = actuserService.queryUserInAct(caUid, userUid);
		return success + "";

	}

	@RequestMapping("/clubact/user/join")
	public String JoinUserInAct(@RequestParam("caUid") String caUid, @RequestParam("user") String user)
			throws Exception {
		User users = ObjectUtil.mapper.readValue(user, User.class);
		int success = actuserService.insertInClub(caUid, users);
		return success + "";

	}

	@RequestMapping("/clubact/user/left")
	public String LeftUserInAct(@RequestParam("caUid") String caUid, @RequestParam("userUid") String userUid) {
		int success = actuserService.deleteInClub(caUid, userUid);
		return success + "";

	}

	@RequestMapping("/clubact/my")
	public List<ClubActivity> queryMyClubActByClub(String userUid, Integer type, Integer page) {
		List<ClubActivity> list = actService.queryMyClubActByClub(userUid, type, page, RedisTip.Page_Rows);
		return list;
	}

	@RequestMapping("/clubact/my/total")
	public String queryMyClubActCountByClub(String userUid, Integer type) {
		int count = actService.queryMyClubActCountByClub(userUid, type);
		return count + "";
	}

	@RequestMapping("/clubact/user/update")
	public String updateClubActUser(@RequestParam("user") String user) throws Exception {
		ClubActUser clubuser = ObjectUtil.mapper.readValue(user, ClubActUser.class);
		int success = actuserService.updateClubActUser(clubuser);
		return success + "";
	}

	@RequestMapping("/clubact/user/update/score")
	public String updateClubActUserScore(@RequestParam("ids") String ids, @RequestParam("score") Integer score,
			@RequestParam("caUid") String caUid) throws Exception {
		int success = actuserService.updateClubActUserScore(ids, caUid, score);
		return success + "";
	}

	@RequestMapping("/clubact/user/pf")
	public String updateClubActUserPF(@RequestParam("userUid") String userUid, @RequestParam("caUid") String caUid,
			@RequestParam("number") Integer number, @RequestParam("msg") String msg) throws Exception {
		int success = actuserService.updateClubActUserPF(userUid, caUid, number,msg);
		return success + "";
	}
	
	@RequestMapping("/clubact/user/query")
	public String queryClubactUser(@RequestParam("userUid") String userUid,
			@RequestParam("caUid") String caUid) throws Exception{
		ClubActUser au=actuserService.queryActUser(userUid,caUid);
		String json=ObjectUtil.mapper.writeValueAsString(au);
		return json;		
	}
	
	@RequestMapping("/clubact/user/query/pf")
	public List<ClubActUser> queryMyClubActUserByClub(String caUid,Integer page) {
		List<ClubActUser> list = actService.queryMyClubActUserByClub(caUid,page,RedisTip.Page_Rows);
		return list;
	}
	
	@RequestMapping("/clubact/user/query/pf/total")
	public String queryMyClubActUserByClub(String caUid) {
		int size = actService.queryMyClubActUserByClubSize(caUid);
		return size+"";
	}
}
