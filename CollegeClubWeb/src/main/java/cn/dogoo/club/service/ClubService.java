package cn.dogoo.club.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.JsonNode;

import cn.dogoo.club.pojo.Club;
import cn.dogoo.common.service.HttpClientService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.tip.UriTip;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.vo.HttpResult;
import cn.dogoo.common.vo.Page;

@Service
public class ClubService{

	@Autowired
	private HttpClientService client;

	
	public Page queryClubByPage(Integer currentPage,Integer clubType) throws Exception {
		String url = UriTip.Manage_URI+"/club/page?page=" + currentPage+"&clubType="+clubType ;
		String productsJson = client.doGet(url);
		// 将json字符串转化成对象'
		JsonNode data = ObjectUtil.mapper.readTree(productsJson);
		List<Club> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, Club.class));
		}
		// 在调服务获取总数量
		url = UriTip.Manage_URI+"/club/page/total?clubType="+clubType;
		String totals = client.doGet(url);
		int total = Integer.parseInt(totals);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setProducts(list);
		// 正好整除,返回除数,不整除返回除数+1
		int totalPage = total % RedisTip.Page_Rows == 0 ? (total / RedisTip.Page_Rows) : ((total / RedisTip.Page_Rows) + 1);
		page.setTotalPage(totalPage);
		return page;

	}

	
	public Club queryClubDetail(String uid) throws Exception {
		String url = UriTip.Manage_URI+"/club/detail/"+uid;
		String clubJson = client.doGet(url);
		Club club=ObjectUtil.mapper.readValue(clubJson, Club.class);
		return club;
	}

	
	public int addClub(Club club) throws Exception {
		String url =UriTip.Manage_URI+"/club/admin/addclub";
		Map<String, Object> map=MapClub(club);
		HttpResult reslut=client.doPost(url, map);
		String success=reslut.getBody();
		return Integer.parseInt(success);				
	}

	
	public int UpdateClub(Club club) throws Exception {
		String url = UriTip.Manage_URI+"/club/admin/update";
		Map<String, Object> map=MapClub(club);
		HttpResult reslut=client.doPost(url, map);
		String success=reslut.getBody();
		return Integer.parseInt(success);
	}

	
	public int deleteClub(String ids) throws Exception {
		String url=UriTip.Manage_URI+"/club/admin/delete";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		HttpResult reslut=client.doPost(url,map);
		String success=reslut.getBody();
		return Integer.parseInt(success);
	}
	
	//99%空指针问题
	public Map<String,Object> MapClub(Club club){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("clubUid", club.getClubUid());
		map.put("clubId", club.getClubId());
		map.put("clubName", club.getClubName());
		map.put("clubSay",club.getClubSay());
		if(club.getClubNownum()!=null){
		map.put("clubNownum", club.getClubNownum());
		}
		map.put("clubTotalnum", club.getClubTotalnum()); 
		map.put("userId", club.getUserId()); 
		map.put("userName", club.getUserName()); 
		map.put("clubLogourl",club.getClubLogourl()); 
		map.put("clubDesc", club.getClubDesc()); 
		map.put("clubTime", club.getClubTime()); 
		if(club.getClubType()!=null){
		map.put("clubType", club.getClubType());
		}
		return map;
	}


	public int updatePassclub(Integer type, String ids) throws Exception {
		String url=UriTip.Manage_URI+"/club/admin/updatepass";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("type", type);
		map.put("ids", ids);
		HttpResult reslut=client.doPost(url,map);
		String success=reslut.getBody();
		return Integer.parseInt(success);
	}

	

}
