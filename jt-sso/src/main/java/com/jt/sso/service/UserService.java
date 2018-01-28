package com.jt.sso.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.common.service.RedisService;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

@Service
public class UserService extends BaseService<User>{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	//检查
	public Boolean check(String param, Integer type){
		Map<String,String> map = new HashMap<String,String>();
		if(1==type){
			map.put("colname", "username");
		}else if(2==type){
			map.put("colname", "phone");
		}else{
			map.put("colname", "email");
		}
		map.put("val", param);
		
		int count = userMapper.check(map);
		return count==0?false:true;
	}
	
	//注册
	public String register(User user){
		if(StringUtils.isEmpty(user.getEmail())){
			user.setEmail(user.getPhone());
		}
		String password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(password);
		
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		
		userMapper.insertSelective(user);
		return user.getUsername();
	}
	
	//登录
	public String login(String username, String password){
		String ticket = null;
		//根据username来查询
		User user = new User();
		user.setUsername(username);
		
		User curUser = super.queryByWhere(user);
		if(null != curUser){	//User对象存在
			//按密码比较
			String newPassword = DigestUtils.md5Hex(password);
			if(newPassword.equals(curUser.getPassword())){
				//生成ticket
				ticket = DigestUtils.md5Hex("TICKET_" + curUser.getUsername() + System.currentTimeMillis());
				//写Redis缓存
				try {
					redisService.set(ticket, MAPPER.writeValueAsString(curUser), 60*60*24);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ticket;
	}

	public String queryByTicket(String ticket) {
		return redisService.get(ticket);
	}
}
