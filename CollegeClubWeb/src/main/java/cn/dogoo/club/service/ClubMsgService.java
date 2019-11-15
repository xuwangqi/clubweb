package cn.dogoo.club.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import cn.dogoo.club.pojo.Club;
import cn.dogoo.club.pojo.ClubMsg;
import cn.dogoo.common.service.HttpClientService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.tip.UriTip;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.vo.Page;

@Service
public class ClubMsgService {

	@Autowired
	private HttpClientService client;
	
	public Page queryClubMsg(String clubUid, Integer currentPage) throws Exception {
		String url = UriTip.Manage_URI+"/clubmsg/query?clubUid="+clubUid+"&page=" + currentPage ;
		String productsJson = client.doGet(url);
		// 将json字符串转化成对象'
		JsonNode data = ObjectUtil.mapper.readTree(productsJson);
		List<ClubMsg> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, ClubMsg.class));
		}
		// 在调服务获取总数量
		url = UriTip.Manage_URI+"/clubmsg/query/total?clubUid="+clubUid;
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

	public int insertMsg(ClubMsg clubmsg) throws Exception {
		String msg=ObjectUtil.mapper.writeValueAsString(clubmsg);
		String url = UriTip.Manage_URI+"/clubmsg/add?";	
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("msg", msg);
		String js = client.doGet(url,map);
		return Integer.parseInt(js);
	}

}
