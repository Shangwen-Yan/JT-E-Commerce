package com.jt.sso.dubbo;

import org.springframework.beans.factory.annotation.Autowired;

import com.jt.dubbo.sso.DubboUserService;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

//把业务的service封装
public class DubboUserServiceImpl implements DubboUserService{
	@Autowired
	private UserService userService;

	public Boolean check(String param, Integer type) {
		return userService.check(param, type);
	}

	public String register(String username, String password, String phone, String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		return userService.register(user);
	}

	public String login(String username, String password) {
		return userService.login(username, password);
	}

	public String queryByTicket(String ticket) {
		return userService.queryByTicket(ticket);
	}

}
