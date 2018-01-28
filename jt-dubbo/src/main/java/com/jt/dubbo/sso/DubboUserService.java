package com.jt.dubbo.sso;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//Dubbox支持RESTFul结构

@Path("user")
public interface DubboUserService {
	//http://sso.jt.com/user/check/{param}/{type}
	@GET
	@Path("check")
	public Boolean check(
			@QueryParam(value="param") String param, 
			@QueryParam(value="type") Integer type);
	
	//RESTFul不支持对象，只能使用一个一个属性
	//http://sso.jt.com/user/register
	@POST
	@Path("register")
	public String register(
			@QueryParam(value="username") String username,
			@QueryParam(value="password") String password,
			@QueryParam(value="phone") String phone,
			@QueryParam(value="email") String email);
	
	//http://sso.jt.com/user/login
	@POST
	@Path("login")
	public String login(
			@QueryParam(value="u") String username, 
			@QueryParam(value="p") String password);
	
	//http://sso.jt.com/user/query/{ticket}
	//@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("query")
	public String queryByTicket(@QueryParam(value="ticket") String ticket);
}
