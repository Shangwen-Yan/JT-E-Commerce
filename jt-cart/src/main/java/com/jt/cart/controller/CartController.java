package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	//查询
	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult queryMyCart(@PathVariable Long userId){
		List<Cart> cartList=cartService.getMyCart(userId);
		
		return SysResult.oK(cartList);
		
	}
	
	//新增商品到购物车
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveCart(Cart cart){
		Integer codeStatus = cartService.saveCart(cart);
		if(codeStatus==200){
			return SysResult.oK();
		}else if(codeStatus==202){
			return SysResult.build(202, "商品已存在，数量增加");
		}else{
			return SysResult.build(201, "新增错误");
		}
	}

	//更新商品数量
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(@PathVariable Long userId,@PathVariable Long itemId,@PathVariable Integer num){
		Cart params=new Cart();
		params.setUserId(userId);
		params.setItemId(itemId);
		params.setNum(num);
		cartService.updateCartNum(params);
		return SysResult.oK();
	}
	
	//删除某商品
	@RequestMapping("/delete/{userId}/{itemId}")
	@ResponseBody
	public SysResult deleteCart(@PathVariable Long userId,@PathVariable Long itemId){
		Cart params=new Cart();
		params.setUserId(userId);
		params.setItemId(itemId);
		cartService.deleteByWhere(params);
		return SysResult.oK();
	}
}
