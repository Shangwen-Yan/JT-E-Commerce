package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.service.ItemCatService;
import com.jt.manage.vo.ItemCatResult;

@Controller
public class WebItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	//为前台提供服务，
	@RequestMapping("/web/itemcat/all")
	@ResponseBody
	public ItemCatResult getItemCatResult(){
		return itemCatService.getItemCatResult();
	}
}
