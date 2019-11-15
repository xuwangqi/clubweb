package cn.dogoo.club.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.dogoo.club.manager.service.ClubService;
import cn.dogoo.club.pojo.Club;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.util.ObjectUtil;



@RestController
public class ClubController {

	@Autowired
	private ClubService clubservice;
	
	@RequestMapping("club/page")
	public List<Club> queryProductByPage(Integer page,Integer clubType){	
		List<Club> clubs=clubservice.queryclubByPage(page,RedisTip.Page_Rows,clubType);
		return clubs;
	}
	
		
	
	@RequestMapping("club/page/total")
	public String queryCount(Integer clubType){
		return clubservice.queryCount(clubType);
	}
	
	
	@RequestMapping("club/detail/{uid}")
	public String queryDetail(@PathVariable String uid){
		Club club=clubservice.queryDetailClub(uid);
		String json;
		try {
			json = ObjectUtil.mapper.writeValueAsString(club);
			return json;	
		} catch (JsonProcessingException e) {
			return "";
		}
		
	}
	
	@RequestMapping("club/admin/addclub")
    public String addClub(Club club){
		int success=clubservice.addClub(club);
		return success+"";
		
	}

	
	@RequestMapping("/club/admin/update")
	public String updateClub(Club club){
		int success=clubservice.updateClub(club);
		return success+"";
	}

	@RequestMapping("club/admin/delete")
	public String deleteClub(@RequestParam(value = "ids") String ids){
		int success=clubservice.deleteClub(ids);
		return success+"";
	}
	
	
	@RequestMapping("/club/admin/updatepass")
	public String updatePassClub(@RequestParam(value = "type") String type,@RequestParam(value = "ids") String ids){
		int success=clubservice.updatepass(type,ids);
		return success+"";
	}
	
}
