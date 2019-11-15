package cn.dogoo.club.sso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.dogoo.club.pojo.User;
import cn.dogoo.club.sso.mapper.UserMapper;
import cn.dogoo.common.service.RedisService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.util.MD5Util;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.util.UUIDUtil;
import cn.dogoo.common.vo.SysResult;

@RestController
@Transactional
public class UserController {

	@Autowired
	private RedisService redis;
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("user/checkuserId/{userId}")
	public String checkUserName(@PathVariable String userId) {
		String exists = userMapper.checkUserId(userId) + "";
		return exists;
	}

	@RequestMapping("user/registeruser")
	public String doReigster(User user, HttpServletResponse res, HttpServletRequest req) throws Exception {
		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
		user.setUserUid(UUIDUtil.getUUID());
		System.out.println(user.toString());
		if(user.getUserType()==null){
			user.setUserType(0);
		}
		int reslut = userMapper.insertUser(user);
		return reslut + "";
	}

	@RequestMapping("user/reset")
	public String doReset(@RequestParam(value = "u") String userId, @RequestParam(value = "op") String oldpassword,
			@RequestParam(value = "p") String userPassword, HttpServletResponse res, HttpServletRequest req)
			throws Exception {
		String op = MD5Util.md5(oldpassword);
		String np = MD5Util.md5(userPassword);
		int reslut = userMapper.updateuserpassword(userId, op, np);
		return reslut + "";
	}

	@RequestMapping("user/loginuser")
	public String doLogin(@RequestParam(value = "u") String userId, @RequestParam(value = "p") String userPassword)
			throws Exception {
		System.out.println("Login:" + userId + ":" + userPassword);
		String ticket = "";
		// 判断登录成功失败
		User _user = new User();
		_user.setUserId(userId);
		_user.setUserPassword(MD5Util.md5(userPassword));
		System.out.println("------" + _user + "-----" + _user.getUserId() + "==" + _user.getUserPassword());
		User user = userMapper.checkLogin(_user);
		if (user == null) {
			// 登录失败 提供的用户名密码不对 直接rerun ticket
			return ticket;
		} else {
			ticket = MD5Util.md5(RedisTip.LoginRedis + System.currentTimeMillis() + userId);
			String userJson = ObjectUtil.mapper.writeValueAsString(user);
			redis.set(ticket, userJson, 60 * 30);
			return ticket;
		}
	}

	@RequestMapping("user/query/{ticket}")
	public String queryTicket(@PathVariable String ticket, String callback) throws Exception {
		SysResult result = new SysResult();
		String userJson = redis.get(ticket);
		// redis续约
		if (userJson != null) {// 登录成功			
			Long left = redis.getTime(ticket);
			if (left <= 60 * 5) {
				int time = (int) (left + 60 * 5);
				redis.set(ticket, userJson, time);
			}
			result.setData(userJson);
			result.setMsg("ok");
			result.setStatus(200);
		}else{
			result.setData(userJson);
			result.setMsg("ok");
			result.setStatus(404);
		}
		
		String jsonData = ObjectUtil.mapper.writeValueAsString(result);
		if (callback == null) {
			return jsonData;
		} else {
			return callback + "(" + jsonData + ")";
		}
	}

	@RequestMapping("user/page")
	public List<User> queryUser(Integer page, Integer rows) {
		int start = (page - 1) * rows;
		List<User> products = userMapper.queryList(start, rows);
		return products;

	}

	@RequestMapping("/user/page/total")
	public String querytotal() {
		return userMapper.queryAll() + "";

	}

	@RequestMapping("user/update/{change}")
	public String userUpdate(@PathVariable Integer change, User user) {
		if (change == 0) {
			user.setUserPassword(null);
		} else if (change == 1) {
			user.setUserPassword(MD5Util.md5(user.getUserPassword()));
		}      
		return  userMapper.updateUser(user)+"";

	}
	
	@RequestMapping("user/delete")
	public String userDelete(@RequestParam(value = "ids") String ids){
		System.out.println(ids.toString());
		String[] idss=ids.split(",");
		int success=userMapper.DeleteUserByIds(idss);
		if(success==idss.length){
			return "1";
		}else{
			return "0";
		}
	}
	
	@RequestMapping("/user/query/detail/{userUid}")
	public String userQueryByuserUid(@PathVariable String userUid) throws Exception{
		User user=userMapper.queryUserByuserUid(userUid);
		String json=null;
		json = ObjectUtil.mapper.writeValueAsString(user);
		return json;				
	}
	
	
	@RequestMapping("/user/update/score")
	public String userUPdateScore(@RequestParam(value = "score") Integer score,
			@RequestParam(value = "ids") String ids){
		String[] idss=ids.split(",");
		int success=userMapper.UpdateUserScore(score,idss);
		if(success==idss.length){
			return "1";
		}else{
			return "0";
		}
	}
	
}
