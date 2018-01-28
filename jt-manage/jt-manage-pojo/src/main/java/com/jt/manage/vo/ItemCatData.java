package com.jt.manage.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

//代表一条菜单结构
public class ItemCatData implements Serializable{
	@JsonProperty(value="u")
	private String url;
	@JsonProperty(value="n")
	private String name;
	@JsonProperty(value="i")
	private List<?> items;	//为了支持一级二级ItemCatData结构，支持三级String
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
	
}
