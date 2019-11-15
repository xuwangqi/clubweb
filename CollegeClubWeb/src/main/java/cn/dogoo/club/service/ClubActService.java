package cn.dogoo.club.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import cn.dogoo.club.pojo.ClubActivity;
import cn.dogoo.common.service.HttpClientService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.tip.UriTip;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.vo.HttpResult;
import cn.dogoo.common.vo.Page;

@Service
public class ClubActService {

	@Autowired
	private HttpClientService client;

	public int applyClubAct(ClubActivity clubAct) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/apply";
		String json = ObjectUtil.mapper.writeValueAsString(clubAct);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubAct", json);
		HttpResult reslut = client.doPost(url, map);
		String success = reslut.getBody();
		return Integer.parseInt(success);
	}

	public int updateClubAct(ClubActivity clubAct) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/edit";
		String json = ObjectUtil.mapper.writeValueAsString(clubAct);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clubAct", json);
		HttpResult reslut = client.doPost(url, map);
		String success = reslut.getBody();
		return Integer.parseInt(success);
	}

	public ClubActivity queryClubActDetail(String caUid) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/detail/query?caUid=" + caUid;
		String json = client.doGet(url);
		ClubActivity clubact = ObjectUtil.mapper.readValue(json, ClubActivity.class);
		return clubact;
	}

	public Page queryClubByclubUid(String clubUid, Integer type, Integer currentPage) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/query/" + clubUid + "?type=" + type + "&page=" + currentPage;
		String josndata = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(josndata);
		List<ClubActivity> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, ClubActivity.class));
		}

		url = UriTip.Manage_URI + "/clubact/query/total/" + clubUid + "?type=" + type;
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

	public Page queryClubAct(Integer type, Integer currentPage) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/query?type=" + type + "&page=" + currentPage;
		String josndata = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(josndata);
		List<ClubActivity> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, ClubActivity.class));
		}

		url = UriTip.Manage_URI + "/clubact/query/total?type=" + type;
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

	public int deleteClubAct(String ids) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/delete";
		Map<String, Object> map = new HashMap<>();
		map.put("ids", ids);
		String success = client.doGet(url, map);
		return Integer.parseInt(success);
	}

	public int updateClubActPass(Integer type, String ids) throws Exception {
		String url = UriTip.Manage_URI + "/clubact/updatepass";
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("ids", ids);
		String success = client.doGet(url, map);
		return Integer.parseInt(success);
	}

	public Page queryMyClubByclubUid(String userUid, String clubUid, Integer type, Integer currentPage)
			throws Exception {
		String url = UriTip.Manage_URI + "/clubact/query/myact/" + clubUid + "?userUid=" + userUid + "&type=" + type
				+ "&page=" + currentPage;
		String josndata = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(josndata);
		List<ClubActivity> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, ClubActivity.class));
		}

		url = UriTip.Manage_URI + "/clubact/query/myact/total/" + clubUid + "?userUid=" + userUid + "&type=" + type;
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


}
