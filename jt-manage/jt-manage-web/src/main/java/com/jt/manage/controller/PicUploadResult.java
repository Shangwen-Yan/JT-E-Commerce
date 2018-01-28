package com.jt.manage.controller;

public class PicUploadResult {
	private Integer error; //0无错 1出错
	private String width;  //宽度，必须为字符串（规定）
	private String height;
	private String url;//链接地址
	
	public Integer getError() {
		return error;
	}
	public void setError(Integer error) {
		this.error = error;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
