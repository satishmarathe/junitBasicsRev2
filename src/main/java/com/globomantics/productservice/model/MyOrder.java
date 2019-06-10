package com.globomantics.productservice.model;

import java.util.List;

public class MyOrder {
	private String orderNumber;
	
	List <MyOrderItem> myOrderItemList;

	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<MyOrderItem> getMyOrderItemList() {
		return myOrderItemList;
	}
	public void setMyOrderItemList(List<MyOrderItem> myOrderItemList) {
		this.myOrderItemList = myOrderItemList;
	}
}
