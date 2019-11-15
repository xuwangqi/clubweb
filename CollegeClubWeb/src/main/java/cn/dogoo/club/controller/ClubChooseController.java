package cn.dogoo.club.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.User;
import cn.dogoo.club.service.ClubChooseService;
import cn.dogoo.club.service.ClubService;
import cn.dogoo.club.service.UserService;
import cn.dogoo.common.vo.Page;
import cn.dogoo.common.vo.SysResult;

@Controller
public class ClubChooseController {

	@Autowired
	private ClubChooseService ccservice;

	@Autowired
	private UserService userService;

	@Autowired
	private ClubService clubservice;

	@RequestMapping("/clubchoose/join/{clubUid}")
	@ResponseBody
	public SysResult joinClub(@PathVariable String clubUid, HttpServletRequest request, HttpServletResponse response) {
		SysResult reslut = new SysResult();
		try {
			User user = userService.getRedisPerson(request);
			int success = ccservice.joinClub(user.getUserUid(), clubUid);
			if (success == 1) {
				reslut.setStatus(200);
				reslut.setMsg("恭喜,加入社团成功");
				return reslut;
			} else {
				reslut.setStatus(201);
				reslut.setMsg("遗憾,加入社团失败,请重新再试");
				return reslut;
			}
		} catch (Exception e) {
			reslut.setStatus(500);
			reslut.setMsg("系统错误,请重新再试");
			return reslut;
		}
	}

	@RequestMapping("/club/myclub")
	public String domyclub(Model model, HttpServletRequest request) {
		try {
			User user = userService.getRedisPerson(request);
			List<Club> club = ccservice.Quermyclub(user.getUserUid());
			if (!club.isEmpty()) {
				model.addAttribute("club", club);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myclub";

	}

	@RequestMapping("/club/left/{clubUid}")
	@ResponseBody
	public SysResult LeftClub(@PathVariable String clubUid, HttpServletRequest request, HttpServletResponse response) {
		SysResult reslut = new SysResult();
		try {
			User user = userService.getRedisPerson(request);
			int success = ccservice.leftClub(user.getUserUid(), clubUid);
			if (success == 1) {
				reslut.setStatus(200);
				return reslut;
			} else {
				reslut.setStatus(500);
				return reslut;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reslut;
	}

	@RequestMapping("/club/Toleft")
	@ResponseBody
	public SysResult ToLeftClub(String userUid, String clubUid) {
		SysResult reslut = new SysResult();
		try {
			int success = ccservice.leftClub(userUid, clubUid);
			if (success == 1) {
				reslut.setStatus(200);
				return reslut;
			} else {
				reslut.setStatus(500);
				return reslut;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reslut;
	}

	// 页面redis缓存

	@RequestMapping("/club/user/{clubUid}")
	public String clubchooseDescUser(@PathVariable String clubUid, Integer page, Model model,
			HttpServletRequest request) {
		try {
			Page pages = ccservice.QueryCCByclubUid(clubUid, page);
			User user = userService.getRedisPerson(request);
			Club club = clubservice.queryClubDetail(clubUid);
			if (user == null)
				return "index";
            if(CheckAdminClub(club,user)){
            	model.addAttribute("clubAdmin", "1");
            }			
			model.addAttribute("page", pages);
			model.addAttribute("club", club);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "club/ClubMain";

	}

	@RequestMapping("/club/act/{clubUid}")
	public String showClubAct(@PathVariable String clubUid, Integer type, Model model,
			HttpServletRequest request) {
		try {
			Club club = clubservice.queryClubDetail(clubUid);
			User user = userService.getRedisPerson(request);						
			if (user == null)
				return "index";
            if(CheckAdminClub(club,user)){
            	model.addAttribute("clubAdmin", "1");
            }
			model.addAttribute("club", club);
		} catch (Exception e) {

		}

		return "club/ClubAct";
	}

	//判断是否是社长
	public Boolean CheckAdminClub(Club club, User user) throws Exception {
		if (user.getUserUid().equals(club.getUserId())) {
			return true;
		} else {
			return false;
		}
	}

}
