package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.pojo.ItemDesc;

@Controller
public class ItemDescController {
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	//查询商品描述信息 /query/item/desc/1474391964
	@RequestMapping("/item/query/item/desc/{itemId}")
	@ResponseBody
	public SysResult queryDesc(@PathVariable Long itemId){
		try{
			ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
			return SysResult.oK(itemDesc);
		}catch(Exception e){
			return SysResult.build(201, "查询商品描述出错!");
		}
	}
}
