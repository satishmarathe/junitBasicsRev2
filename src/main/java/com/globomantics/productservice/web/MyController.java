package com.globomantics.productservice.web;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.globomantics.productservice.model.MyOrder;
import com.globomantics.productservice.model.MyOrderItem;
import com.globomantics.productservice.model.MyOrderSummary;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class MyController {

	private static final Logger logger = LogManager.getLogger(MyController.class);

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

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Integer id) {
		logger.log(Level.INFO, "inside here ");
		try {
			return ResponseEntity
					.ok()
					.eTag(Integer.toString(777))
					.location(new URI("/product/" + 777))
					.body(null);
		} catch (URISyntaxException e ) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
