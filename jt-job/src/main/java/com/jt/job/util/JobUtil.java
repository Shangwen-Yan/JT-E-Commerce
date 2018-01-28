package com.jt.job.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.job.pojo.Job;

public class JobUtil {
	private static final Logger log = Logger.getLogger(JobUtil.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/*
	 * 获取industry行业(一级分类)名称
	 * 0 销售/客服 
		1产品/技术
		2生产/制造
		3 咨询/教育
		4 金融
		5 房地产
		6 人力
	 * */
	public static List<String> getIndustry(String url) throws IOException{
		List<String> industryList = new ArrayList();
		Document document=Jsoup.connect(url).get();
		Elements eles= document.select(".title");
		for(Element ele:eles){
			String html=ele.html();
			if(html.contains("em")){
				String industry=html.split("<")[0];
				industryList.add(industry);
				log.info(industry);
			}	
		}
		return industryList;
	}
	
	/*
	 * 获取一级分类下的所有三级分类id和名称
	 * 
	 * */
	public static List<String[]> getIdAndName(String url,String dataType) throws IOException{
		List<String[]> idAndNames = new ArrayList();
		Document document=Jsoup.connect(url).get();
		String select="li[data-type=\""+dataType+"\"] .hn-detail-list li a";
		Elements eles= document.select(select);
		for(Element ele:eles){
			String[] idAndName=new String[2];
			idAndName[0]=ele.attr("id");
			idAndName[1]=ele.html();
			idAndNames.add(idAndName);
			log.info(idAndName[0]+idAndName[1]);
		}
		return idAndNames;
		
	}
	
	
	
	/**
	 * 
	 * 根据datatype获取所有三级链接
	 * @throws IOException 
	 * 
	 */
	public static List<String> getPositionUrls(String url,String dataType) throws IOException{
		List<String> positionUrls = new ArrayList();
		List<String[]> idAndNames = getIdAndName(url,dataType);
		for(String[] idAndName:idAndNames){
			String positionUrl="https://so.dajie.com/job/search?positionFunction="+idAndName[0]+"&positionName="+idAndName[1];
			positionUrls.add(positionUrl);
			log.info(positionUrl);
		}
		return positionUrls;
		
		
	}
	
	//抓取某个分类下的总页数
	public static Integer getCatPages(String url){
		try{
			return Integer.valueOf(Jsoup.connect(url).get().select("#J_topPage .fp-text i").get(0).text());
		}catch(Exception e){
			//发消息
			log.error(e.getMessage());
			return 0;
		}
	}
	
	//获取某个分类下的所有分页链接
	public static List<String> getAllPageUrl(String url){
		List<String> pageUrls = new ArrayList<String>();
		
		Integer pages = getCatPages(url);
		for(int i=1;i<=pages;i++){
			pageUrls.add(url+"&page="+i);
			log.info(url+"&page="+i);
		}
		return pageUrls;
	}
	
	//获取某个三级分类列表页面的所有商品链接
	public static List<String> getItemUrl(String pageUrl){
		List<String> itemUrls = new ArrayList<String>();
		try {
			Elements els = Jsoup.connect(pageUrl).get().select(".gl-i-wrap").select(".j-sku-item .p-img a");
			for(Element ele : els){
				String itemUrl = "http:"+ele.attr("href");
				log.info(itemUrl);
				itemUrls.add(itemUrl);
			}
			return itemUrls;
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	//获取商品的标题
	public static String getTitle(String itemUrl){
		String title = null;
		try{
			title = Jsoup.connect(itemUrl).get().select(".itemInfo-wrap .sku-name").get(0).text();
		}catch(Exception e){
			log.error(e.getMessage());
		}
		try{
			if(StringUtils.isEmpty(title)){
				title = Jsoup.connect(itemUrl).get().select("#itemInfo #name h1").get(0).text();
			}
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return title;
	}
	
	//获取商品的卖点
	public static String getSellPoint(String itemId){
		String url = "http://ad.3.cn/ads/mgets?skuids=AD_"+itemId;
		try {
			String jsonData = Jsoup.connect(url).ignoreContentType(true).execute().body();
			String sellPoint = MAPPER.readTree(jsonData).get(0).get("ad").asText();
			return sellPoint;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	//获取商品的价格
	public static Integer getPrice(String itemId){
		String url = "http://p.3.cn/prices/mgets?skuIds=J_"+itemId;
		try {
			String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
			String price = MAPPER.readTree(json).get(0).get("p").asText().replaceFirst("\\.", "");
			return Integer.valueOf(price);
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	

	
	//获取商品详情
	public static String getDesc(String itemId){
		String url = "http://d.3.cn/desc/"+itemId;
		try {
			String jsonp = Jsoup.connect(url).ignoreContentType(true).execute().body();
			String json = jsonp.substring(jsonp.indexOf("(")+1, jsonp.lastIndexOf(")"));
			String desc = MAPPER.readTree(json).get("content").asText();
			return desc;
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getItemId(String itemUrl){
		return itemUrl.substring(itemUrl.lastIndexOf("/")+1,itemUrl.lastIndexOf("."));
	}

	//获取某个商品的介绍，id是当前的商品的id，itemId是网站上的商品的id
	public static Job getItemDesc(Long id, String itemId){
		Job job = new Job();
		/*itemDesc.setItemId(id);
		itemDesc.setItemDesc(getDesc(itemId));
		
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(itemDesc.getCreated());*/
		
		log.info(job);
		return job;
	}
	
	//获取商品的所有的链接
	/*public static List<String> getItemUrlAll() throws IOException{
		String url = "https://www.jd.com/allSort.aspx";
		List<String> itemUrls = new ArrayList();
		
		List<String> cats = getItemCatLevel3(url);
		for(String catUrl : cats){
			List<String> pages = getAllPageUrl(catUrl);
			for(String pageUrl : pages){
				//某页所有的商品
				List<String> items = getItemUrl(pageUrl);
				if(null!=items){
					itemUrls.addAll(items);
				}
			}
			break;
		}
		return itemUrls;
	}*/
	
	@Test
	public void run() throws Exception{
		String url = "https://job.dajie.com/"; 
		this.getIndustry(url);
		List<String[]> IdAndNames=this.getIdAndName(url,"0");
		List<String> positionUrls=this.getPositionUrls(url, "0");
	}	
}
