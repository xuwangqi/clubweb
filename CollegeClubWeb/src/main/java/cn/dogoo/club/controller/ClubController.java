package cn.dogoo.club.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
public class ClubController {

	@Autowired
	private ClubService clubservice;

	@Autowired
	private UserService userService;

	@Autowired
	private ClubChooseService ccservice;

	@RequestMapping("club/page")
	public String doClubPage(Integer currentPage, Model model) {
		try {
			Page page = clubservice.queryClubByPage(currentPage,0);
			model.addAttribute("page", page);
			return "allClub";
		} catch (Exception e) {
			return "allClub";
		}

	}

	@RequestMapping("/club/detail/{uid}")
	public String dodetailClub(@PathVariable String uid, Model model, HttpServletRequest request) {
		try {
			Club club = clubservice.queryClubDetail(uid);
			model.addAttribute("club", club);
			User user = userService.getRedisPerson(request);
			if (user == null) {
				return "detailClub";
			}
			String count = ccservice.checkisClub(user.getUserUid(), uid);
			int success = Integer.parseInt(count);
			if (success == 1) {
				model.addAttribute("loginsuit", 1);
				model.addAttribute("clubbelong", 1);
				return "detailClub";
			} else {
				model.addAttribute("loginsuit", 1);
				return "detailClub";
			}

		} catch (Exception e) {
			return "detailClub";
		}

	}

	@RequestMapping("/club/apply")
	public String doapplyClub(HttpServletRequest request) {
		try {
			User user = userService.getRedisPerson(request);
			if (user != null) {
				return "applyClub";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";

	}

	@RequestMapping("/club/apply/submit")
	@ResponseBody
	public SysResult doapplyClubsubmit(Club club, HttpServletRequest request) {
		SysResult reslut = new SysResult();
		try {
			User user = userService.getRedisPerson(request);
			if (user != null) {
				club.setUserId(user.getUserUid());
				club.setUserName(user.getUserName());
				club.setClubType(-1);
                club.setClubTime(new Date());
				int success = clubservice.addClub(club);
				if (success == 1) {
					reslut.setStatus(200);
					reslut.setMsg("申请社团成功");
					return reslut;
				} else {
					reslut.setStatus(500);
					reslut.setMsg("申请社团失败");
					return reslut;
				}
			} else {
				reslut.setStatus(500);
				reslut.setMsg("申请失败");
				return reslut;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reslut;

	}

	@RequestMapping("/club/edit")
	public String doeditClub(String clubUid,Model model,HttpServletRequest request) {
		try {
			Club club=clubservice.queryClubDetail(clubUid);
			if(club!=null){
				User user=userService.getRedisPerson(request);				
				if(user.getUserType()==1 || user.getUserUid().equals(club.getUserId())){
					model.addAttribute("club", club);
					return "admin/EditClub";
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";

	}
	
	
	
	@RequestMapping("/club/update/submit")
	@ResponseBody
	public SysResult updateClub(Club club) {
		SysResult result = new SysResult();
		try {
			club.setClubTime(new Date());
			int success = clubservice.UpdateClub(club);
			if (success == 1) {
				result.setStatus(200);
				result.setMsg("更新成功");
				return result;
			} else {
				result.setStatus(500);
				result.setMsg("更新失败");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
