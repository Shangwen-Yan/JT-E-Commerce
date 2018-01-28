package com.jt.manage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisSentinelService;
import com.jt.common.service.RedisService;

import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.vo.ItemCatData;
import com.jt.manage.vo.ItemCatResult;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatService extends BaseService<ItemCat>{
	@Autowired
	private ItemCatMapper itemCatMapper;
	/*@Autowired
	private RedisSentinelService redisCluster;*/
	@Autowired
	private JedisCluster jedisCluster;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOG = Logger.getLogger(ItemCatService.class);
	
	//获取tree的json串
	public List<ItemCat> list(Long parentId){
		//判断缓存中是否有数据
		String ITEM_CAT_KEY = "ITEM_CAT_"+parentId;
		String jsonData = jedisCluster.get(ITEM_CAT_KEY);
		if(StringUtils.isNotEmpty(jsonData)){
			//代表从缓存中查询到数据，把json字符串转成java对象
			try {
				JsonNode jsonNode = MAPPER.readTree(jsonData);
				if (jsonNode.isArray() && jsonNode.size() > 0) {
					List<ItemCat> obj = MAPPER.readValue(jsonNode.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, ItemCat.class));
					return obj;
				}
			} catch (Exception e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
			return null;
		}else{	//缓存不抛异常，如果没有数据就去请求数据库，继续执行业务
			//通用Mapper坑：判断实体字段如果是非null就拼接到where条件中
			ItemCat params = new ItemCat();
			params.setStatus(1);	//1正常，2删除
			params.setParentId(parentId);
			
			List<ItemCat> itemCatList = itemCatMapper.select(params);
			
			//写缓存，把java对象转成json串
			try {
				String val = MAPPER.writeValueAsString(itemCatList);
				jedisCluster.set(ITEM_CAT_KEY, val);	//写缓存
			} catch (JsonProcessingException e) {
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
			
			return itemCatList;
		}
	}
	
	
	//形成商品分类菜单json结构
	public ItemCatResult getItemCatResult(){
		//获取所有的数据
		List<ItemCat> cats = super.queryAll();
		//1.获取当前节点下的所有的子节点（k=parentId,v=list)
		Map<Long, List<ItemCat>> map = new HashMap<Long, List<ItemCat>>();
		
		for(ItemCat itemCat : cats){
			Long parentId = itemCat.getParentId();
			if(!map.containsKey(parentId)){		//map中还没有parentId
				map.put(parentId, new ArrayList<ItemCat>());	//当没有时创建空List
			}
			map.get(parentId).add(itemCat);
		}
		
		//2.构建ItemCatResult，只能拼接
		ItemCatResult result = new ItemCatResult();
		
		//构建一级菜单
		String url = "";
		List<ItemCatData> list1 = new ArrayList<ItemCatData>();	//保存一级菜单
		for(ItemCat ic1 : map.get(0L)){		//EasyUI.tree 根节点父id=0
			ItemCatData icd1 = new ItemCatData();	//一级菜单
			url = "/products/"+ic1.getId()+".html";
			icd1.setUrl(url);
			icd1.setName("<a href=\""+url+"\">"+ic1.getName()+"</a>");
			
			//构建二级菜单
			List<ItemCatData> list2 = new ArrayList<ItemCatData>();	//保存二级菜单
			for(ItemCat ic2 : map.get(ic1.getId())){	//当前的父节点id
				ItemCatData icd2 = new ItemCatData();	//二级菜单
				url = "/products/"+ic2.getId()+".html";
				icd2.setUrl(url);
				icd2.setName(ic2.getName());
				
				//构建三级菜单
				List<String> list3 = new ArrayList<String>();	//保存三级菜单
				for(ItemCat ic3 : map.get(ic2.getId())){
					url = "/products/"+ic3.getId()+".html";
					list3.add(url + "|" +ic3.getName());
				}
				
				icd2.setItems(list3);
				list2.add(icd2);
			}
			
			icd1.setItems(list2);  //当前节点下的二级菜单
			list1.add(icd1);
			
			//只要前14个菜单
			if(list1.size()>=14){
				break;
			}
		}
		
		result.setItemCatDateList(list1);
		return result;
	}
}

	

