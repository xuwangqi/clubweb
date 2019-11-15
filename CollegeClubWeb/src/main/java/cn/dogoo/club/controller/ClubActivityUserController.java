package cn.dogoo.club.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dogoo.club.pojo.ClubActivity;
import cn.dogoo.club.pojo.User;
import cn.dogoo.club.service.ClubActService;
import cn.dogoo.club.service.ClubActivityUserService;
import cn.dogoo.club.service.UserService;
import cn.dogoo.common.vo.Page;
import cn.dogoo.common.vo.SysResult;

@Controller
public class ClubActivityUserController {

	@Autowired
	private ClubActivityUserService actuserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClubActService actService;
	
	@RequestMapping("/club/act/join")
	@ResponseBody
	public SysResult JoinClub(String caUid,HttpServletRequest request){
		SysResult reslut=new SysResult();
		try{
			User user=userService.getRedisPerson(request);
			int success=actuserService.JoinActivty(caUid,user);
			if(success==1){
				reslut.setStatus(200);
				return reslut;
			}else{
				reslut.setStatus(201);
				return reslut;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reslut;
	}
	
	
	
	@RequestMapping("/club/act/left")
	@ResponseBody
	public SysResult LeftClub(String caUid,HttpServletRequest request){
		SysResult reslut=new SysResult();
		try{
			User user=userService.getRedisPerson(request);
			int success=actuserService.LetfActivty(caUid, user.getUserUid());
			if(success==1){
				reslut.setStatus(200);
				return reslut;
			}else{
				reslut.setStatus(201);
				return reslut;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reslut;
	}
	
	
	@RequestMapping("/clubact/my")
	public String QueryMyClubAct(Integer type,Integer page,Model model
			,HttpServletRequest request){
		try {
			User user = userService.getRedisPerson(request);
			model.addAttribute("type", type);
			if (user == null)
				return "index";
			Page pages = actuserService.queryMyClub(user.getUserUid(), type, page);
			model.addAttribute("page", pages);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "MyClubAct";	
	}
	
	@RequestMapping("/club/act/pf")
	@ResponseBody
	public SysResult PFClubActivity(String caUid,Integer number,
			String msg,HttpServletRequest request){
		SysResult reslut=new SysResult();
		try{
			User user = userService.getRedisPerson(request);
			int success=actuserService.updateClubScore(user.getUserUid(),caUid,number,msg);
			if(success==1){
				reslut.setStatus(200);
				return reslut;
			}else{
				reslut.setStatus(500);
				return reslut;
			}
		}catch(Exception e){
			e.printStackTrace();
			reslut.setStatus(201);
			return reslut;
		}	
	}
	
	@RequestMapping("/club/act/query/user/pf")
	public String QueryUserPF(String caUid,Integer page,Model model){
		try{
			ClubActivity clubActivity=actService.queryClubActDetail(caUid);
			Page pages=actuserService.QueryUserPF(caUid,page);
			model.addAttribute("clubact", clubActivity);
			model.addAttribute("page", pages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "club/ClubActPF";
	}
	
}
