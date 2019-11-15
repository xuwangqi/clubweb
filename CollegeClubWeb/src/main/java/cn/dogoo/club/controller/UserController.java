package cn.dogoo.club.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.dogoo.club.pojo.User;
import cn.dogoo.club.service.UserService;
import cn.dogoo.common.service.RedisService;
import cn.dogoo.common.tip.RedisTip;
import cn.dogoo.common.util.CookieUtils;
import cn.dogoo.common.util.MD5Util;
import cn.dogoo.common.util.ObjectUtil;
import cn.dogoo.common.vo.SysResult;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * registUser:注册用户
	 * 
	 * @param user
	 * @return SysResult
	 */
	@RequestMapping("/user/registuser")
	@ResponseBody
	public SysResult registUser(User user) {
		SysResult reslut = new SysResult();
		try {
			int success = userService.registUser(user);

			if (success == 1) {
				reslut.setStatus(1);
				reslut.setMsg("用户:学号为" + user.getUserId() + "注册成功");
				return reslut;
			} else {
				reslut.setStatus(-1);
				reslut.setMsg("用户注册失败");
				return reslut;
			}
		} catch (Exception e) {
			reslut.setStatus(500);
			reslut.setMsg("服务器异常");
			return reslut;
		}
	}


	@RequestMapping("/user/loginuser")
	@ResponseBody
	public SysResult loginUser(String userId, String userPassword, HttpServletRequest request,
			HttpServletResponse response) {
		SysResult result = new SysResult();
		String ticket;
		try {
			ticket = userService.loginUser(userId, userPassword);
			if (StringUtils.isNotEmpty(ticket)) {
				CookieUtils.setCookie(request, response, RedisTip.LoginRedis, ticket);
				// 登录成功 转向首页
				result.setStatus(1);
				result.setMsg("登录成功");
				return result;
			} else {
				result.setStatus(0);
				result.setMsg("用户名或者密码错误,请重试");
				return result;
			}
		} catch (Exception e) {
			result.setStatus(500);
			result.setMsg("服务器异常");
			return result;
		}

	}

	@RequestMapping("/user/checkuserId")
	@ResponseBody
	public SysResult checkUserId(String userId) {
		SysResult result = new SysResult();
		try {
			int success = userService.checkUserId(userId);
			result.setStatus(success);
			return result;
		} catch (Exception e) {
			result.setStatus(500);
			return result;
		}
	}

	@Autowired
	private RedisService redis;

	@RequestMapping("user/logout")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse res) {

		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (null == cookies) {
		} else {
			int i = 1;
			for (Cookie cookie : cookies) {

				System.out.println("第" + i + "个name:" + cookie.getName() + ",value:" + cookie.getValue());
				i++;
				if (cookie.getName().equals(RedisTip.LoginRedis)) {
					redis.del(cookie.getValue());
					System.out.println("清除redis");
				}
				cookie.setValue(null);
				cookie.setMaxAge(0);// 立即销毁cookie
				cookie.setPath("/");
				System.out.println("被删除的cookie名字为:" + cookie.getName());
				res.addCookie(cookie);
			}

		}
		/*
		 * Cookie cookie=new Cookie("autoLogin",null); cookie.setMaxAge(0);
		 * cookie.setPath("/page"); cookie.setValue(null);
		 * res.addCookie(cookie);
		 */
		return "ok";
	}

	@RequestMapping("/user/reset")
	@ResponseBody
	public SysResult resetPassword(String userId, String oldpassword, String userPassword, HttpServletRequest request) {
		SysResult reslut = new SysResult();
		String tiket = CookieUtils.getCookieValue(request, RedisTip.LoginRedis);
		String json = redis.get(tiket);
		try {
			User user = ObjectUtil.mapper.readValue(json, User.class);
			if (!user.getUserPassword().equals(MD5Util.md5(oldpassword))) {
				reslut.setStatus(-1);
				reslut.setMsg("旧密码输入错误");
				return reslut;
			}

			int success = userService.resetPassword(userId, oldpassword, userPassword);
			if (success == 1) {
				reslut.setStatus(1);
				reslut.setMsg("密码修改成功");
				return reslut;
			} else {
				reslut.setStatus(0);
				reslut.setMsg("密码修改失败");
				return reslut;
			}

		} catch (Exception e) {
			reslut.setStatus(500);
			reslut.setMsg("系统错误");
			return reslut;
		}

	}

	@RequestMapping("/user/adminaddUser")
	@ResponseBody
	public SysResult AdminaddUser(User user) {
		SysResult result = new SysResult();
		try {
			int success = userService.registUser(user);
			if (success == 1) {
				result.setStatus(200);
				return result;
			} else {
				result.setStatus(500);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(201);
			return result;
		}

	}


	

	@RequestMapping("/user/update")
	@ResponseBody
	public SysResult updateUser(User user,HttpServletRequest request) {
		SysResult result = new SysResult();
		int change=0;
		try {
			if(StringUtils.isNotEmpty(user.getUserPassword())){
				change=1;
			}else{
				change=0;
			}
			int success = userService.UpdateUserByAdmin(change, user);
			if (success == 1) {			
				result.setStatus(200);	
				result.setMsg("更新成功");
				return result;
			} else {
				result.setStatus(500);
				result.setMsg("更新失败");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/user/detail/{userUid}")
	public String detailUser(@PathVariable String userUid,Model model,HttpServletRequest request){
        try{
        	User user=userService.detailUser(userUid);
        	model.addAttribute("user", user);
        	User usernow=userService.getRedisPerson(request);
        	if(usernow.getUserUid().equals(user.getUserUid())){
        		model.addAttribute("editin", 1);
        	}
        	return "detailUser";
        }catch(Exception e){
        	e.printStackTrace();
        }
		return "index";	
	}

	
	@RequestMapping("/user/edit/{userUid}")
	public String editUser(@PathVariable String userUid,Model model,HttpServletRequest request){
		try{
			User user=userService.detailUser(userUid);
			model.addAttribute("user", user);
			User usernow=userService.getRedisPerson(request);
			if(usernow.getUserType()==1){
				model.addAttribute("adminin", 1);
			}	
			return "EditUser";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "EditUser";
		
	}
	
		
}
