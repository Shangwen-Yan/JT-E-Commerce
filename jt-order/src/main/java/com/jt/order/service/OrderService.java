package com.jt.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.BaseService;
import com.jt.order.mapper.OrderMapper;
import com.jt.order.pojo.Order;

@Service
public class OrderService extends BaseService<Order>{
	@Autowired
	private OrderMapper orderMapper;
	
	public String saveOrder(Order order){
		String orderId=order.getUserId()+""+System.currentTimeMillis();
		order.setOrderId(orderId);
		orderMapper.createOrder(order);
		return orderId;
	}
	
	public Order queryOrderById(String orderId){
		return orderMapper.queryOrderById(orderId);
	}
}
