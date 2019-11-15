package cn.dogoo.club.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.User;
import cn.dogoo.club.service.ClubActService;
import cn.dogoo.club.service.ClubChooseService;
import cn.dogoo.club.service.ClubService;
import cn.dogoo.club.service.UserService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.vo.Page;
import cn.dogoo.common.vo.SysResult;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private ClubService clubService;
	@Autowired
	private ClubChooseService ccservice;
	@Autowired 
	private ClubActService actService;

	@RequestMapping("/page/admin/usermanage")
	public String goPathAdmin(int page, HttpServletRequest request, Model model) {
		try {
			User user = userService.getRedisPerson(request);
			Page pages = userService.QueryUserByPNR(page, RedisTip.Admin_Page_Rows);
			if (user.getUserType() >= 1) {
				model.addAttribute("page", pages);
				return "admin/allUserManage";
			} else {
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	@RequestMapping("/page/admin/clubmanage")
	public String goPathAdminClub(int page, HttpServletRequest request, Model model) {
		try {
			User user = userService.getRedisPerson(request);
			Page pages = clubService.queryClubByPage(page, 0);
			if (user.getUserType() >= 1) {
				model.addAttribute("page", pages);
				return "admin/allClubManage";
			} else {
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	@RequestMapping("/user/delete")
	@ResponseBody
	public SysResult deleteUser(String ids) {
		SysResult reslut = new SysResult();
		try {
			int success = userService.deleteUser(ids);
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

	@RequestMapping("/club/delete")
	@ResponseBody
	public SysResult deleteClub(String ids) {
		SysResult reslut = new SysResult();
		try {
			int success = clubService.deleteClub(ids);
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
		return null;

	}

	@RequestMapping("/club/clubpass")
	public String goPathAdminPassClub(int page, HttpServletRequest request, Model model) {
		try {
			User user = userService.getRedisPerson(request);
			Page pages = clubService.queryClubByPage(page, -1);
			if (user.getUserType() >= 1) {
				model.addAttribute("page", pages);
				return "admin/allClubPass";
			} else {
				return "index";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}

	/**
	 * 通过社团的话需要执行以下方法 方法一:修改社团状态 方法二:使申请者加入社团 方法三:修改申请者的权限为社长
	 */
	@RequestMapping("/club/clubpass/submit")
	@ResponseBody
	public SysResult goPathAdminPassSubmitClub(Integer type, String ids) {
		SysResult reslut = new SysResult();
		try {
			int success = clubService.updatePassclub(type, ids);
			//根据ids查出Club
			if(success==1){
				String[] idss=ids.split(",");
				for(int i=0;i<idss.length;i++){
			      Club club=clubService.queryClubDetail(idss[i]);
			      if(Integer.parseInt(ccservice.checkisClub(club.getUserId(), club.getClubUid()))==0){
			      success = ccservice.joinClub(club.getUserId(), club.getClubUid());
			      }
			      success= ccservice.updateType(club.getUserId(), club.getClubUid(), 1);
				}
			}
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

	
	@RequestMapping("/club/clubunpass/submit")
	@ResponseBody
	public SysResult goPathAdminUpPassSubmitClub(Integer type, String ids) {
		SysResult reslut = new SysResult();
		try {
			int success = clubService.updatePassclub(type, ids);
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
	
	
	@RequestMapping("/page/admin/clubact")
	public String QueryAllclubactByType(Integer type,Integer page,Model model,
			HttpServletRequest request){
		try{
			User user=userService.getRedisPerson(request);
			if(user == null || user.getUserType()!=1){
				return "index";
			}
            model.addAttribute("type", type);
			Page pages=actService.queryClubAct(type,page); 
			model.addAttribute("page", pages);
			if(type==-1){
				model.addAttribute("title", "社团活动申请管理");
				return "admin/allClubActPassManage";
			}else if(type == 0){
				model.addAttribute("title", "可参与社团活动管理");
				return "admin/allClubActManage";
			}else if(type==1){
				model.addAttribute("title", "过期的社团活动申请管理");
				return "admin/allClubActManage";
			}
			
		}catch(Exception e){
			
		}
		return "index";
	}
	
	
	@RequestMapping("/clubact/delete")
	@ResponseBody
	public SysResult deleteClubAct(String ids){
		SysResult reslut=new SysResult();
		try{
			int success=actService.deleteClubAct(ids);
			if(success==1){
				reslut.setStatus(200);
				return reslut;
			}else{
				reslut.setStatus(500);
				return reslut;
			}
		}catch(Exception e){
			reslut.setStatus(202);
			return reslut;
		}

	}
	
	
	@RequestMapping("/clubact/updatepass")
	@ResponseBody
	public SysResult updateClubActPass(Integer type,String ids){
		SysResult reslut=new SysResult();
		try{
			int success=actService.updateClubActPass(type,ids);
			if(success==1){
				reslut.setStatus(200);
				return reslut;
			}else{
				reslut.setStatus(500);
				return reslut;
			}
		}catch(Exception e){
			reslut.setStatus(202);
			return reslut;
		}

	}
	
}
