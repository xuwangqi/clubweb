package cn.dogoo.club.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.ClubMsg;
import cn.dogoo.club.pojo.User;
import cn.dogoo.club.service.ClubMsgService;
import cn.dogoo.club.service.ClubService;
import cn.dogoo.club.service.UserService;
import cn.dogoo.common.vo.Page;
import cn.dogoo.common.vo.SysResult;

@Controller
public class ClubMsgController {

	@Autowired
	private ClubMsgService msgService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClubService clubService;
	
	@RequestMapping("/club/msg")
	public String queryClubMsg(String clubUid,Integer page,Model model){
		try{
			Page pages=msgService.queryClubMsg(clubUid,page);
			model.addAttribute("page", pages);
			Club club=clubService.queryClubDetail(clubUid);
			model.addAttribute("club", club);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "club/ClubMsg";
	}
	
	
	@RequestMapping("/club/msg/add")
	@ResponseBody
	public SysResult addMsg(String clubUid,String title,String body
			,HttpServletRequest request){
		SysResult reslut = new SysResult();
		try{
			User user=userService.getRedisPerson(request);
			ClubMsg clubmsg=new ClubMsg();
			clubmsg.setClubUid(clubUid);
			clubmsg.setCmTitle(title);
			clubmsg.setCmBody(body);
			clubmsg.setUserUid(user.getUserUid());
			clubmsg.setUserName(user.getUserName());
			int success=msgService.insertMsg(clubmsg);
			if(success==1){
				reslut.setStatus(200);
				return reslut;
			}
			else{
				reslut.setStatus(500);
				return reslut;
			}
		}catch(Exception e){
			e.printStackTrace();
		    reslut.setStatus(202);
			return reslut;
		}
	}
	
}
