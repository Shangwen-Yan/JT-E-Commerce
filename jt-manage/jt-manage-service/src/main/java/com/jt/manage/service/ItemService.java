package com.jt.manage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.pojo.ItemDesc;

@Service
public class ItemService extends BaseService<Item>{
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private ItemDescMapper itemDescMapper; 
	
	
	//查询所有商品列表
	public EasyUIResult queryItemList(Integer page,Integer rows){
		//PageHelper类，分页时只对它下面的第一条查询sql语句生效（mybatis注解）
		PageHelper.startPage(page, rows);	//表示拦截的开始，两个参数为第几页和每页显示的条数
		List<Item> itemList = itemMapper.queryItemList();
		
		
		//List<Item> itemList1 = itemMapper.queryItemList();
		PageInfo<Item> pageInfo = new PageInfo(itemList);
		return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
	}
	
	//保存新增
	public void saveItem(Item item,String desc){
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insertSelective(item);
		
		//mybatis+mysql配合回写item的id！！！
		//新增商品描述:不能和上面拆成两个方法：事物回滚
		ItemDesc itemDesc=new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);
	}
	
	//商品修改
	public void updateItem(Item item, String desc){
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(item.getUpdated());
		
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}
	
	//商品下架、上架
	public void updateItemStatus(Integer status,String[] statusVals){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("status", status);
		map.put("statusVals", statusVals);
		itemMapper.updateStatus(map);
	}

	public void deleteItem(String[] ids) {
		//先删除子表，商品详情
		itemDescMapper.deleteByIDS(ids);
		itemMapper.deleteByIDS(ids);
		
	}
	
	//根据商品id获取商品描述
	public ItemDesc queryDescById(Long itemId) {
		return itemDescMapper.selectByPrimaryKey(itemId);
	}	


}
