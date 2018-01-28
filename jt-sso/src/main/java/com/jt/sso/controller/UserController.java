package com.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	
	//检查
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public SysResult check(@PathVariable String param,@PathVariable Integer type){
		Boolean b = userService.check(param, type);
		return SysResult.oK(b);
	}
	
	//注册
	@RequestMapping("/register")
	@ResponseBody
	public SysResult register(User user){
		String username = userService.register(user);
		return SysResult.oK(username);
	}
	
	//登录
	@RequestMapping("/login")
	@ResponseBody
	public SysResult login(String u, String p){
		String ticket =userService.login(u, p);
		return SysResult.oK(ticket);
	}
	
	//根据ticket查询redis中的值
	@RequestMapping("/query/{ticket}")
	@ResponseBody
	public SysResult queryByTicket(@PathVariable String ticket){
		String userJson = redisService.get(ticket);
		return SysResult.oK(userJson);
	}
}
