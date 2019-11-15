package cn.dogoo.club.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import cn.dogoo.club.pojo.ClubActUser;
import cn.dogoo.club.pojo.ClubActivity;
import cn.dogoo.club.pojo.User;
import cn.dogoo.common.service.HttpClientService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.tip.UriTip;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.vo.Page;

@Service
public class ClubActivityUserService {
    
	@Autowired
	private HttpClientService client;
	
	public int checkUserInActivty(String caUid,String userUid) throws Exception{
		String url=UriTip.Manage_URI+"/clubact/user/check";	
		return ClientCommon(url,caUid,userUid);
	}
	
	
	public int JoinActivty(String caUid,User user) throws Exception{
		String url=UriTip.Manage_URI+"/clubact/user/join";
		String users=ObjectUtil.mapper.writeValueAsString(user);
		return ClientCommonByUser(url,caUid,users);
	}
	
	
	public int LetfActivty(String caUid,String userUid) throws Exception{
		String url=UriTip.Manage_URI+"/clubact/user/left";
		return ClientCommon(url,caUid,userUid);
	}
	
	
	private int ClientCommonByUser(String url,String caUid,String user) throws Exception{
		Map<String,Object> map=new HashMap<>();
		map.put("caUid", caUid);
		map.put("user", user);
		String success=client.doGet(url, map);
		return Integer.parseInt(success);
	}
	
	
	private int ClientCommon(String url,String caUid,String userUid) throws Exception{
		Map<String,Object> map=new HashMap<>();
		map.put("caUid", caUid);
		map.put("userUid", userUid);
		String success=client.doGet(url, map);
		return Integer.parseInt(success);
	}


	public Page queryMyClub(String userUid, Integer type, Integer currentPage) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/my?userUid=" + userUid + "&type=" + type
				+ "&page=" + currentPage;
		String josndata = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(josndata);
		List<ClubActivity> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, ClubActivity.class));
		}

		url = UriTip.Manage_URI + "/clubact/my/total?userUid=" + userUid + "&type=" + type;
		String totals = client.doGet(url);
		int total = Integer.parseInt(totals);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setProducts(list);
		// 正好整除,返回除数,不整除返回除数+1
		int totalPage = total % RedisTip.Page_Rows == 0 ? (total / RedisTip.Page_Rows)
				: ((total / RedisTip.Page_Rows) + 1);
		page.setTotalPage(totalPage);

		return page;
	}


	public int updateUserActByScore(Integer caGrade, String caUid, String ids) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/user/update/score";
		Map<String, Object> map=new HashMap<>();
		map.put("ids", ids);
		map.put("caUid", caUid);
		map.put("score", caGrade);
		String reslut=client.doGet(url, map);	
		return Integer.parseInt(reslut);
	}


	public int updateClubScore(String userUid, String caUid, Integer number, String msg) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/user/pf";
		Map<String, Object> map=new HashMap<>();
		map.put("userUid", userUid);
		map.put("caUid", caUid);
		map.put("number", number);
		map.put("msg", msg);
		String reslut=client.doGet(url, map);	
		return Integer.parseInt(reslut);
	}


	public ClubActUser queryClubActUser(String userUid, String caUid) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/user/query";
		Map<String, Object> map=new HashMap<>();
		map.put("userUid", userUid);
		map.put("caUid", caUid);
		String reslut=client.doGet(url, map);	
		return ObjectUtil.mapper.readValue(reslut, ClubActUser.class);
	}


	public Page QueryUserPF(String caUid, Integer pages) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/user/query/pf?caUid="+caUid
				+"&page="+pages;
		String josndata = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(josndata);
		List<ClubActUser> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, ClubActUser.class));
		}
		
		url=UriTip.Manage_URI + "/clubact/user/query/pf/total?caUid="+caUid;
		String json = client.doGet(url);
		int total=Integer.parseInt(json);
		
		Page page=new Page();
		int totalPage = total % RedisTip.Page_Rows == 0 ? (total / RedisTip.Page_Rows)
				: ((total / RedisTip.Page_Rows) + 1);
		page.setTotalPage(totalPage);
		page.setCurrentPage(pages);
		page.setProducts(list);
		
		return page;
	}
	
}
