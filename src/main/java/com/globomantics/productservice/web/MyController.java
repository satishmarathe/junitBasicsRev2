package com.globomantics.productservice.web;

import java.math.BigDecimal;
import java.util.List;

import com.globomantics.productservice.model.MyOrder;
import com.globomantics.productservice.model.MyOrderItem;
import com.globomantics.productservice.model.MyOrderSummary;

public class MyController {
	
	public MyOrderSummary transform( MyOrder myOrder) {
		if(null == myOrder) {
			throw new IllegalArgumentException("myOrder should not be null");
		}
		MyOrderSummary myOrderSummaryResult = new MyOrderSummary();
		
		myOrderSummaryResult.setOrderNumber(myOrder.getOrderNumber());
		
		/** get the individual items **/
		List <MyOrderItem> orderItemList = myOrder.getMyOrderItemList();
		
		int totalItemCount = 0;
		BigDecimal totalItemPrice = new BigDecimal(0);
		if(null != orderItemList && orderItemList.size() > 0) {
			for(int z=0;z<orderItemList.size();z++) {
				MyOrderItem myOrderItem = orderItemList.get(z);
				totalItemCount = totalItemCount + myOrderItem.getQuantity();
				System.out.println("myOrderItem.getSellingPrice() = " + myOrderItem.getSellingPrice());
				System.out.println("myOrderItem.getQuantity() = " + myOrderItem.getQuantity());
				totalItemPrice = totalItemPrice.add(myOrderItem.getSellingPrice().multiply(new BigDecimal(myOrderItem.getQuantity() )));
				
			}//for
		}
		myOrderSummaryResult.setItemCount(totalItemCount);
		myOrderSummaryResult.setTotalAmount(totalItemPrice);
		return myOrderSummaryResult;
	}
}
