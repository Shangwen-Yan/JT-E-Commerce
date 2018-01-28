package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class IndexController {
	//通用转向页面
	@RequestMapping("/{pageName}")
	public String home(@PathVariable String pageName){
		return pageName;
	}
}
