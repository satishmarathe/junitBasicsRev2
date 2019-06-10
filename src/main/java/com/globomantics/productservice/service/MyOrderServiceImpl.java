package com.globomantics.productservice.service;

import java.util.LinkedList;
import java.util.List;

import com.globomantics.productservice.common.MyDataAccessException;
import com.globomantics.productservice.common.MyServiceException;
import com.globomantics.productservice.model.MyOrder;
import com.globomantics.productservice.model.MyOrderSummary;
import com.globomantics.productservice.repository.MyOrderDao;
import com.globomantics.productservice.transformer.MyOrderToOrderSummaryTransformer;

public class MyOrderServiceImpl implements MyOrderService {

	private MyOrderDao orderDao = null;
	private MyOrderToOrderSummaryTransformer transformer = null;
	
	public void setOrderDao(final MyOrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public void setTransformer(final MyOrderToOrderSummaryTransformer transformer) {
		this.transformer = transformer;
	}
	
	@Override
	public List<MyOrderSummary> getOrderSummary(long customerId)
			throws MyServiceException {
		
		// Goal - interact with the dao to gather entities and 
		// create summary domain objects
		System.out.println("<<< MyOrderServiceImpl getOrderSummary start >>>");
		List<MyOrderSummary> resultList = new LinkedList();
		
		try {
			List<MyOrder> orderEntityList = this.orderDao.findOrdersByCustomer(customerId);
			
			for (MyOrder currentOrderEntity : orderEntityList) {
				
				MyOrderSummary orderSummary = this.transformer.transform(currentOrderEntity);
				resultList.add(orderSummary);
			}
			
		} catch (MyDataAccessException e) {
			System.out.println(" in MyDataAccessException ");
			// You should log the error
			throw new MyServiceException("Data access error occurred", e);
		}catch (Exception e) {
			System.out.println(" in Exception ");
			throw new MyServiceException("System error occurred", e);
		}
		System.out.println("<<< MyOrderServiceImpl getOrderSummary end >>>");
		return resultList;
	}
}
