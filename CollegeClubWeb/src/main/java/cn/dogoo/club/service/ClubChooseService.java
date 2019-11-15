package cn.dogoo.club.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.ClubChoose;
import cn.dogoo.common.service.HttpClientService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.tip.UriTip;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.vo.Page;

@Service
public class ClubChooseService {

	@Autowired
	private HttpClientService client;
	
	public String checkisClub(String userUid, String clubUid) throws Exception {
		String url=UriTip.Manage_URI+"/clubchoose/checkisclub?userUid="
				+userUid+"&clubUid="+clubUid;
		String reslut=client.doGet(url);
		return reslut;
	}

	public int joinClub(String userUid, String clubUid) throws Exception {
		String url=UriTip.Manage_URI+"/clubchoose/Join?userUid="
				+userUid+"&clubUid="+clubUid;
		String success=client.doGet(url);
		System.out.println(success);
		return Integer.parseInt(success);
	}

	public List<Club> Quermyclub(String userUid) throws Exception{
		String url = UriTip.Manage_URI+"/club/myclub/"+userUid;
		String clubJson = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(clubJson);
		List<Club> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, Club.class));
		}
		return list;
	}

	public Page QueryCCByclubUid(String clubUid, Integer page) throws Exception {
		String url = UriTip.Manage_URI+"/clubchoose/user/"+clubUid+"?page="+page;
		String clubJson = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(clubJson);
		List<ClubChoose> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, ClubChoose.class));
		}
			
		url = UriTip.Manage_URI+"/clubchoose/user/total/"+clubUid;
		String reslut=client.doGet(url);
		int total=Integer.parseInt(reslut);
		int totalPage = total % RedisTip.Page_Rows == 0 ? (total / RedisTip.Page_Rows) : ((total / RedisTip.Page_Rows) + 1);
		
		Page pages=new Page();
		pages.setProducts(list);
		pages.setCurrentPage(page);
		pages.setTotalPage(totalPage);
		
		return pages;
	}

	public int leftClub(String userUid, String clubUid) throws Exception {
		String url=UriTip.Manage_URI+"/clubchoose/left?userUid="
				+userUid+"&clubUid="+clubUid;
		String success=client.doGet(url);
		return Integer.parseInt(success);
	}

	public int updateType(String userUid, String clubUid, int type) throws Exception {
		String url=UriTip.Manage_URI+"/clubchoose/update/cutype?userUid="
				+userUid+"&clubUid="+clubUid+"&type="+type;
		String success=client.doGet(url);
		return Integer.parseInt(success);
		
	}
	
	
	public List<ClubChoose> getClubAllUser(String clubUid) throws Exception{
		String url = UriTip.Manage_URI+"/clubchoose/user/"+clubUid+"?page=-1";
		String clubJson = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(clubJson);
		List<ClubChoose> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, ClubChoose.class));
		}		
		return list;
	}


}
