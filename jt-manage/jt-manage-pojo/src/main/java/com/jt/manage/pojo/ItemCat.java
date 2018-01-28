package com.jt.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name="tb_item_cat")	//类和数据库表的映射
public class ItemCat extends BasePojo{
	@Id	//主键，修改，删除，新增时需要主键标识
	@GeneratedValue(strategy=GenerationType.IDENTITY)	//自增策略
	private Long id;
	
	@Column(name="parent_id")	//当属性名和字段名不一致时，需要写映射，但是配置全局mybatis驼峰规则后，也不需要写
	private Long parentId;		//不是按对象来配置
	private String name;
	private Integer status;
	private Integer sortOrder;
	private Boolean isParent;
	
	//为easyUi的tree的格式准备数据：
	//text为显示的内容
	public String getText(){
		return this.name;
	}
	
	public String getState(){
		//return "open";
		return this.isParent?"closed":"open";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
}
