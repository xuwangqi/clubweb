package cn.dogoo.club.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.ClubActUser;
import cn.dogoo.club.pojo.ClubActivity;
import cn.dogoo.club.pojo.User;
import cn.dogoo.club.service.ClubActService;
import cn.dogoo.club.service.ClubActivityUserService;
import cn.dogoo.club.service.ClubService;
import cn.dogoo.club.service.UserService;
import cn.dogoo.common.vo.Page;
import cn.dogoo.common.vo.SysResult;

@Controller
public class ClubActivityController {

	@Autowired
	private UserService userService;

	@Autowired
	private ClubService clubService;

	@Autowired
	private ClubActService actService;
	
	@Autowired
	private ClubActivityUserService clubuserService;
	

	@RequestMapping("/club/act/apply")
	public String applyClubAct(String clubUid, Model model, HttpServletRequest request) {
		try {
			Club club = clubService.queryClubDetail(clubUid);
			User user = userService.getRedisPerson(request);
			if (user.getUserUid().equals(club.getUserId())) {
				model.addAttribute("club", club);
				return "club/applyClubAct";
			}
		} catch (Exception e) {
			return "index";
		}

		return "index";
	}

	@RequestMapping("/club/act/apply/submit")
	@ResponseBody
	public SysResult applyClubActSubmit(ClubActivity clubAct) {
		SysResult result = new SysResult();
		try {
			int success = actService.applyClubAct(clubAct);
			if (success == 1) {
				result.setStatus(200);
				result.setMsg("活动申请成功,等待管理员");
				return result;
			} else {
				result.setStatus(500);
				result.setMsg("系统异常");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(201);
			result.setMsg("系统异常");
			return result;
		}
	}

	@RequestMapping("/club/act/detail")
	public String detailClubAct(String caUid, Model model, HttpServletRequest request) {
		try {
			ClubActivity clubact = actService.queryClubActDetail(caUid);
			Club club=clubService.queryClubDetail(clubact.getClubUid());
			User user = userService.getRedisPerson(request);
			if (user != null) {
				model.addAttribute("clubact", clubact);
				//判断活动是否结束
				if(clubact.getCaType()==0){
					model.addAttribute("canjoin", "1");
				}
				//查询用户是否已经加入活动
				int res=clubuserService.checkUserInActivty(caUid, user.getUserUid());
				if(res==1){
					model.addAttribute("joined","1");
					ClubActUser clubuser=clubuserService.queryClubActUser(user.getUserUid(),caUid);
					if(clubuser!=null){
					model.addAttribute("clubuser", clubuser);
					}
				}
                //查询是否是社团管理员
				if(CheckAdminClub(club, user)){
					model.addAttribute("adminLogin", "1");
				}
				//查询用户在该活动的报名信息
				
				return "club/detailClubAct";
			}
		} catch (Exception e) {
               e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/club/act/query/{clubUid}")
	public String QueryClubAct(@PathVariable String clubUid, Integer type, Integer page, Model model,
			HttpServletRequest request) {
		try {
			Club club = clubService.queryClubDetail(clubUid);
			User user = userService.getRedisPerson(request);
			model.addAttribute("club", club);
			model.addAttribute("type", type);
			if (user == null)
				return "index";
			if (CheckAdminClub(club, user)) {
				System.out.println("admin confirm");
				model.addAttribute("clubAdmin", "1");
			}
			Page pages = actService.queryClubByclubUid(clubUid, type, page);
			if (type == -1) {
				model.addAttribute("title", "申请的");
			} else if (type == 0) {
				model.addAttribute("title", "可参加的");
			} else if (type == 1) {
				model.addAttribute("title", "过期的");
			}
			model.addAttribute("page", pages);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "club/ClubAct";
	}
	
	
	@RequestMapping("/club/act/query/myact/{clubUid}")
	public String QueryMyClubAct(@PathVariable String clubUid, Integer type, Integer page, Model model,
			HttpServletRequest request) {
		try {
			Club club = clubService.queryClubDetail(clubUid);
			User user = userService.getRedisPerson(request);
			model.addAttribute("club", club);
			model.addAttribute("type", type);
			if (user == null)
				return "index";
			if (CheckAdminClub(club, user)) {
				model.addAttribute("clubAdmin", "1");
			}
			Page pages = actService.queryMyClubByclubUid(user.getUserUid(),clubUid, type, page);
			if (type == -1) {
				model.addAttribute("title", "未申请");
			} else if (type == 0) {
				model.addAttribute("title", "待参加的");
			} else if (type == 1) {
				model.addAttribute("title", "已结束的");
			}else  if (type == 2) {
				model.addAttribute("title", "待结算的");
			}
			model.addAttribute("page", pages);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "club/MyClubAct";
	}
	
	

	@RequestMapping("/club/act/edit")
	public String editClubAct(String caUid, Model model, HttpServletRequest request) {
		try {
			ClubActivity clubact = actService.queryClubActDetail(caUid);
			User user = userService.getRedisPerson(request);
			Club club = clubService.queryClubDetail(clubact.getClubUid());
			if (CheckAdminClub(club, user) || user.getUserType() == 1) {
				model.addAttribute("clubact", clubact);
				return "club/EditClubAct";
			} else {
				return "index";
			}
		} catch (Exception e) {

		}
		return "index";
	}

	@RequestMapping("/club/act/edit/submit")
	@ResponseBody
	public SysResult editClubActSubmit(ClubActivity clubact) {
		SysResult reslut = new SysResult();
		try {
			int success = actService.updateClubAct(clubact);
			if (success == 1) {
				reslut.setStatus(200);
				reslut.setMsg("社团活动更新成功");
				return reslut;
			} else {
				reslut.setStatus(200);
				reslut.setMsg("社团活动更新失败");
				return reslut;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reslut;
	}

	@RequestMapping("/club/act/commit/score")
	public String ClubActScore(String caUid,Model model,
			HttpServletRequest request){
		try {
			User user=userService.getRedisPerson(request);
			ClubActivity act=actService.queryClubActDetail(caUid);
			Club club=clubService.queryClubDetail(act.getClubUid());
			if(!CheckAdminClub(club,user)){
				return "index";
			}
			model.addAttribute("clubact", act);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "club/ClubActScore";
		
		
	}
	
	
	@RequestMapping("/club/act/score/upload")
	@ResponseBody
	public SysResult ClubActUpload(String act,String ids){
		SysResult result=new SysResult();
		int success=0;
		try{
			ClubActivity clubact=actService.queryClubActDetail(act);
			Club club=clubService.queryClubDetail(clubact.getClubUid());
			success=clubuserService.updateUserActByScore(clubact.getCaGrade(),clubact.getCaUid(),ids);
			success=userService.UpdateUserScore(clubact.getCaGrade(), ids);
			success=userService.UpdateUserScore(clubact.getCaGrade(), club.getUserId());
	        success=actService.updateClubActPass(1, act);			
			if(success==1){
				result.setStatus(200);
			}else{
				result.setStatus(201);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			result.setStatus(500);
		}
		return result;
	}
	
	
	
	
	public Boolean CheckAdminClub(Club club, User user) throws Exception {
		if (user.getUserUid().equals(club.getUserId())) {
			return true;
		} else {
			return false;
		}
	}
}
