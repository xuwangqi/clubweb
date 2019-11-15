package cn.dogoo.club.manager.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cn.dogoo.club.manager.service.ClubActService;
import cn.dogoo.club.pojo.ClubActivity;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.util.ObjectUtil;

@RestController
public class ClubActController {

	@Autowired
	private ClubActService actService;
	
	@RequestMapping("/clubact/apply")
	public String addClubAct(@RequestParam(value = "clubAct") String strclubAct) throws Exception{
		ClubActivity clubact=ObjectUtil.mapper.readValue(strclubAct, ClubActivity.class);
		int reslut=actService.addClubAct(clubact);
		return reslut+"";
	}
	
	
	@RequestMapping("/clubact/edit")
	public String updateClubAct(@RequestParam(value = "clubAct") String strclubAct) throws Exception{
		ClubActivity clubact=ObjectUtil.mapper.readValue(strclubAct, ClubActivity.class);
		int reslut=actService.updateClubAct(clubact);
		return reslut+"";
	}
	
	@RequestMapping("/clubact/query/{clubUid}")
	public List<ClubActivity> queryClubActByClub(@PathVariable String clubUid,Integer type,Integer page){
		List<ClubActivity> list=actService.queryClubActByClub(clubUid,type,page,RedisTip.Page_Rows);
		return list;
	}
	
	
	@RequestMapping("/clubact/query/total/{clubUid}")
	public String queryClubActCountByClub(@PathVariable String clubUid,Integer type){
		int count=actService.queryClubActCountByClub(clubUid,type);
		return count+"";
	}
	
	@RequestMapping("/clubact/query/myact/{clubUid}")
	public List<ClubActivity> queryMyClubActByClub(@PathVariable String clubUid,String userUid,Integer type,Integer page){
		List<ClubActivity> list=actService.queryMyClubActByClub(clubUid,userUid,type,page,RedisTip.Page_Rows);
		return list;
	}
	
	
	@RequestMapping("/clubact/query/myact/total/{clubUid}")
	public String queryMyClubActCountByClub(@PathVariable String clubUid,String userUid,Integer type){
		int count=actService.queryMyClubActCountByClub(clubUid,userUid,type);
		return count+"";
	}
	
	
	
	
	@RequestMapping("/clubact/query")
	public List<ClubActivity> queryClubAct(Integer type,Integer page){
		List<ClubActivity> list=actService.queryClubAct(type,page,RedisTip.Page_Rows);
		return list;
	}
	
	@RequestMapping("/clubact/query/total")
	public String queryClubActCount(Integer type){
		int count=actService.queryClubActCount(type);
		return count+"";
	}
	
	
	@RequestMapping("/clubact/detail/query")
	public String queryClubActDetail(String caUid) throws Exception{
		ClubActivity clubact=actService.queryClubActDetail(caUid);
		String json=ObjectUtil.mapper.writeValueAsString(clubact);
		return json;
	}
	
	@RequestMapping("/clubact/delete")
	public String deleteClubAct(@RequestParam("ids") String ids){
		return actService.deleteClubAct(ids);
	}
	
	@RequestMapping("/clubact/updatepass")
	public String deleteClubAct(@RequestParam("type") Integer type,@RequestParam("ids") String ids){
		return actService.updateClubActPass(type,ids);
	}
	
}
