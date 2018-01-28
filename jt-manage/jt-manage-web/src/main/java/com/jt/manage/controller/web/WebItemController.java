package com.jt.manage.controller.web;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
public class WebItemController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private RedisService redisService;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	//根据商品id获取当前的商品
	@RequestMapping("/items/{itemId}")
	@ResponseBody	//httpclient请求返回值都是json串
	public Item getItemById(@PathVariable Long itemId) throws JsonParseException, JsonMappingException, IOException{
		String ITEM_KEY = "ITEM_"+itemId;
		String jsonData = redisService.get(ITEM_KEY);
		Item item = null;
		if(StringUtils.isNotEmpty(jsonData)){
			item = MAPPER.readValue(jsonData, Item.class);
		}else{
			item = itemService.queryById(itemId);
			redisService.set(ITEM_KEY, MAPPER.writeValueAsString(item), 60*60*24*7);	//7天，编译器算好结果class
		}
		return item;
	}
	
	//根据商品id获取当前的商品描述
	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public ItemDesc getItemDescByItemId(@PathVariable Long itemId){
		return itemService.queryDescById(itemId);
	}
}
