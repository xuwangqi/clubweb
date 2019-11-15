package cn.dogoo.club.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;


import cn.dogoo.club.pojo.User;
import cn.dogoo.common.service.HttpClientService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.tip.UriTip;
import cn.dogoo.common.util.CookieUtils;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.vo.HttpResult;
import cn.dogoo.common.vo.Page;
import cn.dogoo.common.vo.SysResult;

@Service
public class UserService {

	@Autowired
	private HttpClientService client;

	public int registUser(User user) throws Exception {
		String url = UriTip.SSO_URI+"/user/registeruser";
		Map<String, Object> map = MapUser(user);
		// 请求体中 userName=**&userPassowrd=**...
		HttpResult result = client.doPost(url, map);// 封装了响应体
		// 获取响应体内容
		String success = result.getBody();// 1成功 0失败
		return Integer.parseInt(success);
	}

	public String loginUser(String userId, String userPassword) throws Exception {
		String url = UriTip.SSO_URI+"//user/loginuser";
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		map.put("u", user.getUserId());
		map.put("p", user.getUserPassword());
		HttpResult result = client.doPost(url, map);
		String ticket = result.getBody();
		return ticket;
	}

	public int checkUserId(String userId) throws Exception {
		String url = UriTip.SSO_URI+"//user/checkuserId/" + userId;
		String exists = client.doGet(url);
		return Integer.parseInt(exists);
	}

	public int resetPassword(String userId, String oldpassword, String userPassword) throws Exception {
		String url = UriTip.SSO_URI+"//user/reset";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("u", userId);
		map.put("op", oldpassword);
		map.put("p", userPassword);
		HttpResult result = client.doPost(url, map);
		String success = result.getBody();
		return Integer.parseInt(success);
	}

	public Page QueryUserByPNR(int page, Integer rows) throws Exception {
		String url = UriTip.SSO_URI+"/user/page?page=" + page + "&rows=" + rows;
		String productsJson = client.doGet(url);
		JsonNode data = ObjectUtil.mapper.readTree(productsJson);
		List<User> list = null;
		if (data.isArray() && data.size() > 0) {
			list = ObjectUtil.mapper.readValue(data.traverse(),
					ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, User.class));
		}
		// 在调服务获取总数量
		url = UriTip.SSO_URI+"/user/page/total";
		String totals = client.doGet(url);
		int total = Integer.parseInt(totals);
		Integer totalPage = total % RedisTip.Admin_Page_Rows == 0 ? (total / RedisTip.Admin_Page_Rows) : ((total / RedisTip.Admin_Page_Rows) + 1);
		Page Pages = new Page();
		Pages.setCurrentPage(page);
		Pages.setProducts(list);
		Pages.setTotalPage(totalPage);
		return Pages;
	}

	public int UpdateUserByAdmin(Integer change, User user) throws Exception {
		String url = UriTip.SSO_URI+"/user/update/" + change;
		Map<String, Object> map=MapUser(user);
		System.out.println(user.toString());
		HttpResult result = client.doPost(url, map);// 封装了响应体
		String success = result.getBody();// 1成功 0失败
		return Integer.parseInt(success);
	}

	public int deleteUser(String ids) throws Exception {
		String url = UriTip.SSO_URI+"/user/delete";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		HttpResult reslut = client.doPost(url, map);
		String success = reslut.getBody();
		return Integer.parseInt(success);
	}

	public User getRedisPerson(HttpServletRequest request) throws Exception{
		String ticket=CookieUtils.getCookieValue(request, RedisTip.LoginRedis);
		String url = UriTip.SSO_URI+"/user/query/"+ticket;
		String json=client.doGet(url);
		SysResult result=ObjectUtil.mapper.readValue(json, SysResult.class);
		String userJson=(String) result.getData();
		User user=ObjectUtil.mapper.readValue(userJson, User.class);
		return user;
	}


	public Map<String,Object> MapUser(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userUid", user.getUserUid());
		map.put("userId", user.getUserId());
		if(user.getUserPassword()==null){
	    map.put("userPassword", "");
		}else{
		map.put("userPassword", user.getUserPassword());
		}
		map.put("userName", user.getUserName());
		map.put("userGender", user.getUserGender());
		map.put("userClass", user.getUserClass());
		map.put("userEmail", user.getUserEmail());
		map.put("userQQ", user.getUserQQ());
		map.put("userPhone", user.getUserPhone());
		map.put("userPhonemin", user.getUserPhonemin());
		map.put("userGrade", user.getUserGrade());
		if (user.getUserType() != null) {
			map.put("userType", user.getUserType());
		}
		return map;
	}

	public int UpdateUserScore(Integer score,String ids) throws Exception{
		String url = UriTip.SSO_URI+"/user/update/score";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("score", score);
		map.put("ids", ids);
		HttpResult reslut = client.doPost(url, map);
		String success = reslut.getBody();
		return Integer.parseInt(success);
	}
	
	
	public User detailUser(String userUid) throws Exception {
		String url = UriTip.SSO_URI+"/user/query/detail/" + userUid;
		String json = client.doGet(url);
		User user=ObjectUtil.mapper.readValue(json,User.class);
		return user;
	}
	
	
	
	
}
