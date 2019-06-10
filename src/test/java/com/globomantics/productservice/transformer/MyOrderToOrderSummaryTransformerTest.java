package com.globomantics.productservice.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.globomantics.productservice.model.MyOrder;
import com.globomantics.productservice.model.MyOrderItem;
import com.globomantics.productservice.model.MyOrderSummary;

public class MyOrderToOrderSummaryTransformerTest {
	
	MyOrderToOrderSummaryTransformer target;
	@BeforeEach
	public void setup() {
		target = new MyOrderToOrderSummaryTransformer();
	}
	
	/**
	 * testing scenario where we expect an IllegalArgumentException when the param MyOrder is null
	 */
	@Test
	public void test_transform_throwsIllegalArgumentException() {		
		
		assertThrows(IllegalArgumentException.class, () -> {
			target.transform(null);
		});
	}
	
	/**
	 * this is a positive test where we are testing order quantity and order price for a single item 
	 */
	@Test
	public void test_transformSingleOrderItems() {		
		MyOrder myOrderFixture = new MyOrder();
		myOrderFixture.setOrderNumber("xyz");
		
		MyOrderItem myOrderItemFixture1 = new MyOrderItem();
		myOrderItemFixture1.setQuantity(3);
		myOrderItemFixture1.setSellingPrice(new BigDecimal("13.6"));
		
		List <MyOrderItem> myOrderItemListFixture = new ArrayList <MyOrderItem>();
		myOrderItemListFixture.add(myOrderItemFixture1);
		
		myOrderFixture.setMyOrderItemList(myOrderItemListFixture);
		
		/** execution **/
		MyOrderSummary  myOrderSummaryResult = target.transform(myOrderFixture);
		
		assertNotNull(myOrderSummaryResult);
		
		assertEquals(3, myOrderSummaryResult.getItemCount());
		
		assertEquals("40.8", myOrderSummaryResult.getTotalAmount().toString());
		
		
	}
	
	/**
	 * this is a positive test where we are testing order quantity and order price for multiple items 
	 */
	@Test
	public void test_transformMultipleOrderItems() {		
		MyOrder myOrderFixture = new MyOrder();
		myOrderFixture.setOrderNumber("xyz");
		
		MyOrderItem myOrderItemFixture1 = new MyOrderItem();
		myOrderItemFixture1.setQuantity(3);
		myOrderItemFixture1.setSellingPrice(new BigDecimal("13.6"));
		
		MyOrderItem myOrderItemFixture2 = new MyOrderItem();
		myOrderItemFixture2.setQuantity(1);
		myOrderItemFixture2.setSellingPrice(new BigDecimal("11.0"));
		
		MyOrderItem myOrderItemFixture3 = new MyOrderItem();
		myOrderItemFixture3.setQuantity(2);
		myOrderItemFixture3.setSellingPrice(new BigDecimal("2.83"));
		
		List <MyOrderItem> myOrderItemListFixture = new ArrayList <MyOrderItem>();
		myOrderItemListFixture.add(myOrderItemFixture1);
		myOrderItemListFixture.add(myOrderItemFixture2);
		myOrderItemListFixture.add(myOrderItemFixture3);
		
		myOrderFixture.setMyOrderItemList(myOrderItemListFixture);
		
		/** execution **/
		MyOrderSummary  myOrderSummaryResult = target.transform(myOrderFixture);
		
		assertNotNull(myOrderSummaryResult);
		
		assertEquals(6, myOrderSummaryResult.getItemCount());
		
		assertEquals("57.46", myOrderSummaryResult.getTotalAmount().toString());
		
		
	}
	
	
}
