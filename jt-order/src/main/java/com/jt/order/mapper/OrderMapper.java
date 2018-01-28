package com.jt.order.mapper;


import java.util.Date;

import com.jt.common.mapper.SysMapper;
import com.jt.order.pojo.Order;

public interface OrderMapper extends SysMapper<Order>{
	public void createOrder(Order order);
	public Order queryOrderById(String orderId);
	public void updatePaymentStatus(Date date);
}
