package com.jt.manage.controller;

import java.awt.ItemSelectable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@Autowired ItemDescMapper itemDescMapper;
	//获得本类的日志文件
	private Logger log=Logger.getLogger(ItemController.class);
	

	
	@RequestMapping("/query")
	@ResponseBody    //将返回结果变成json格式
	public EasyUIResult queryItemList(@RequestParam(defaultValue="0") Integer page,@RequestParam(defaultValue="0") Integer rows){
		return itemService.queryItemList(page,rows);		
	}
	
	
	//保存新增
	@RequestMapping("/save")
	@ResponseBody    //将返回结果变成json格式
	public SysResult saveItem(Item item,String desc){
		try{
			itemService.saveItem(item,desc);
			return SysResult.oK();
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return null;	
	}
	
	//修改
	@RequestMapping("/update")
	@ResponseBody    //将返回结果变成json格式
	public SysResult updateItem(Item item,String desc){
		try{
			itemService.updateItem(item,desc);
			return SysResult.oK();
		}catch(Exception e){
			return SysResult.build(201, "商品修改时出错");
			}

	}
		
	//删除
	@RequestMapping("/delete")
	@ResponseBody    //将返回结果变成json格式
	public SysResult deleteItems(String[] ids){
		try{
			itemService.deleteItem(ids);
			return SysResult.oK();
		}catch(Exception e){
			log.error(e.getMessage());
			return SysResult.build(201, "商品删除时出错");
		}
	}
	
	//下架 status=2
		@RequestMapping("/instock")
		@ResponseBody    //将返回结果变成json格式
		public SysResult instockItems(String[] ids){
			try{
				itemService.updateItemStatus(2, ids);
				return SysResult.oK();
			}catch(Exception e){
				log.error(e.getMessage());
				return SysResult.build(201, "商品下架时出错");
			}
		}
		//上架 status=1
		@RequestMapping("/reshelf")
		@ResponseBody    //将返回结果变成json格式
		public SysResult reshelfItems(String[] ids){
			try{
				itemService.updateItemStatus(1, ids);
				return SysResult.oK();
			}catch(Exception e){
				log.error(e.getMessage());
				return SysResult.build(201, "商品下架时出错");
			}
		}
}
