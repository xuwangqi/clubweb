package cn.dogoo.club.manager.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import cn.dogoo.club.manager.service.ClubMsgService;
import cn.dogoo.club.pojo.ClubMsg;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.util.ObjectUtil;

@RestController
public class ClubMsgController {

	@Autowired
	private ClubMsgService msgservice;
	
	@RequestMapping("/clubmsg/query")
	public List<ClubMsg> QueryMsg(String clubUid,Integer page){
		List<ClubMsg> list=msgservice.queryMsg(clubUid,page,RedisTip.Page_Rows);
	    return list;
	}
	
	
	@RequestMapping("/clubmsg/query/total")
    public String QueryMsgTotal(String clubUid){
		int count=msgservice.queryMsgTotal(clubUid);
		return count+"";
	}
	
	@RequestMapping("/clubmsg/add")
	public String InsertMsg(@RequestParam("msg") String msg) throws Exception{
		ClubMsg clubmsg = ObjectUtil.mapper.readValue(msg, ClubMsg.class);
		int res=msgservice.insertMsg(clubmsg);
		return res+"";
	}
}
