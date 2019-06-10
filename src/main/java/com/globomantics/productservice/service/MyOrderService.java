package com.globomantics.productservice.service;

import java.util.List;

import com.globomantics.productservice.common.MyServiceException;
import com.globomantics.productservice.model.MyOrderSummary;

public interface MyOrderService {

	List<MyOrderSummary> getOrderSummary(long customerId) throws MyServiceException;
}
