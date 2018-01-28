package com.jt.order.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jt.common.vo.SysResult;
import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private final static ObjectMapper MAPPER=new ObjectMapper();
	
	@RequestMapping("/query/{orderId}")
	@ResponseBody
	public Order queryOrderById(@PathVariable String orderId){
		return orderService.queryOrderById(orderId);
	}
	
	//创建订单
	@RequestMapping("/create")
	@ResponseBody
	public String createOrder(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException{
		Order order=MAPPER.readValue(json, Order.class);
		String orderId=orderService.saveOrder(order);
		System.out.println("ordercontroller"+orderId);
		return orderId;
	}
}
