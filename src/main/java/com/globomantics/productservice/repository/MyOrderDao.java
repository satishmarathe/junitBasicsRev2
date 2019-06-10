package com.globomantics.productservice.repository;

import java.util.List;

import com.globomantics.productservice.common.MyDataAccessException;
import com.globomantics.productservice.model.MyOrder;

public interface MyOrderDao {

	MyOrder findById(long id) throws MyDataAccessException;
	int insert(MyOrder order) throws MyDataAccessException;
	
	List<MyOrder> findOrdersByCustomer(long customerId) throws MyDataAccessException;
}