package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	//查询商品分类表的所有数据，返回json串
	@RequestMapping("all")     //all前面可以不加斜杠，会自动补上
	@ResponseBody		//把java对象转成json串，jackson
	public List<ItemCat> queryAll(){
		List<ItemCat> itemCatList = itemCatService.queryAll();
		return itemCatList;
	}
	
	
	//查询商品分类所有数据
	@RequestMapping("/list")     //all前面可以不加斜杠，会自动补上
	@ResponseBody
	public List<ItemCat> list(@RequestParam(defaultValue="0")Long id){
		//id第一次调用为null
		List<ItemCat> itemCatList=itemCatService.list(id);
		return itemCatList;
	}
}
