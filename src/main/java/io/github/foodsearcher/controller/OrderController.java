package io.github.foodsearcher.controller;

import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.github.foodsearcher.model.OrderInfo;
import io.github.foodsearcher.model.StatusMsg;
import io.github.foodsearcher.service.OrderInfoService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private OrderInfoService orderInfoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public StatusMsg createOrder(OrderInfo orderInfo) {
		try {
			orderInfoService.createOrderInfo(orderInfo);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(value = "/{orderId}",method = RequestMethod.GET)
	public StatusMsg getOrder(@PathVariable("orderId") Long id) {
		OrderInfo result = new OrderInfo();
		try {
			result = orderInfoService.findByID(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) result);
	}
	
	@RequestMapping(value = "/{orderId}",method = RequestMethod.DELETE)
	public StatusMsg deleteOrder(@PathVariable("orderId") Long id) {
		try {
			orderInfoService.delete(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public StatusMsg updateOrder(@RequestParam("id") Long id,
								 @RequestParam("status") int status) {
		try {
			orderInfoService.updataOrderInfo(id, status);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOk();
	}
	
	@RequestMapping(value="/store/{storeId}",method = RequestMethod.GET)
	public StatusMsg getStoreOrder(@PathVariable("storeId") Long id) {
		List<OrderInfo> result;
		try {
			result = orderInfoService.findByStoreID(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) result);
	}
	
	@RequestMapping(value="/user/{userId}",method = RequestMethod.GET)
	public StatusMsg getUserOrder(@PathVariable("userId") Long id) {
		List<OrderInfo> result;
		try {
			result = orderInfoService.findByUserID(id);
		} catch (Exception exp) {
			return StatusMsg.returnError();
		}
		return StatusMsg.returnOkWithObj((Object) result);
	}
	
}
