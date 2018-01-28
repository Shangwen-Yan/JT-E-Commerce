package com.jt.manage.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

//代表商品分类菜单整体结构
public class ItemCatResult {
	@JsonProperty(value="data")
	private List<ItemCatData> itemCatDateList;

	public List<ItemCatData> getItemCatDateList() {
		return itemCatDateList;
	}

	public void setItemCatDateList(List<ItemCatData> itemCatDateList) {
		this.itemCatDateList = itemCatDateList;
	}
}
