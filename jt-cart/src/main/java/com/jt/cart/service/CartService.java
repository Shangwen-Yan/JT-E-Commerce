package com.jt.cart.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.service.BaseService;

@Service
public class CartService extends BaseService<Cart>{
	@Autowired
	private CartMapper cartMapper;
	
	//我的购物车
	public List<Cart> getMyCart(Long userId){
		Cart param=new Cart();
		param.setUserId(userId);
		return cartMapper.select(param);
	}
	
	//保存商品到购物车
	public Integer saveCart(Cart cart){
		//判断此用户的此商品是否存在
		Cart params = new Cart();	//保证where条件的正确，因为通用Mapper是按字段是否为null来拼接where条件
		params.setUserId(cart.getUserId());
		params.setItemId(cart.getItemId());
		
		//Integer count = cartMapper.selectCount(param);
		Cart curCart = super.queryByWhere(params);
		if(null==curCart){
			//商品不存在
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insertSelective(cart);
			return 200;
		}else{
			//商品已存在，修改就数量+新页面数量
			Integer oldNum=curCart.getNum();
			cart.setNum(oldNum + cart.getNum());
			cart.setUpdated(new Date());
			updateCartNum(cart);
			return 202;
		}
	}
	
	//更新数量
	public void updateCartNum(Cart cart){
		//cart.setUpdated(new Date());
		cartMapper.updateNum(cart);
	}
}
