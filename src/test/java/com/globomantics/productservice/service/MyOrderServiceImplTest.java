package com.globomantics.productservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;

import com.globomantics.productservice.common.MyDataAccessException;
import com.globomantics.productservice.common.MyServiceException;
import com.globomantics.productservice.model.MyOrder;
import com.globomantics.productservice.model.MyOrderSummary;
import com.globomantics.productservice.repository.MyOrderDao;
import com.globomantics.productservice.transformer.MyOrderToOrderSummaryTransformer;
import com.globomantics.productservice.web.MyController;

public class MyOrderServiceImplTest {

	private static final Long CUSTOMER_ID = 1L;

	/** calling this as 'target' so that we know the 'class under test' **/
	private MyOrderServiceImpl target = null;
	
	/** we need to be mocking the DAO here - MyOrderDao is the class we want to mock **/
	MyOrderDao mockOrderDao = Mockito.mock(MyOrderDao.class);

	@BeforeEach
	public void setup() {
		/** initialise the class under test **/
		/**TODO - using spring the new operator should not be required **/
		target = new MyOrderServiceImpl();
		
		/** now inject the mocked DAO into the service class **/
		target.setOrderDao(mockOrderDao);
		
		/** we will not mock the transformer - ideally it should be mocked but for this test we will not **/
		MyOrderToOrderSummaryTransformer myTransformer = new MyOrderToOrderSummaryTransformer();
		target.setTransformer(myTransformer);		
	}
	
	/**
	 * This test method is simulating a MyServiceException in the service class by ensuring the mocked DAO throws a DataAccessException.
	 * This is an abnormal condition that we dont expect to occur but lets say the Database is down  
	 * We want to ensure that code gracefully handles this scenario
	 * In this case the Service class wraps the DataAccessException to a MyServiceException and throws it
	 * So we test the service class against this exception 
	 * @throws Exception
	 */
	@Test
	public void test_getOrderSummary_throws_MyServiceException_when_Dao_throws_DataAccessException() throws  Exception{
		/** specify what needs to be mocked **/
		Mockito.mock(MyOrderDao.class);
		
		/** specify what should the DAO do and what is the condition **/
		Mockito.when(mockOrderDao.findOrdersByCustomer(CUSTOMER_ID))
		.thenThrow(MyDataAccessException.class);
		
		/** execute **/
		/** here ordinarily the service method will return a List but in this scenario it is going to throw an exception 
		 *  which is why we will not check for the return value at all
		 **/
		assertThrows(MyServiceException.class, () -> {
			/** we will be making the actual execution call here **/
			target.getOrderSummary(CUSTOMER_ID);
		});
	}
	
	/**
	 * This test method is simulating a NPE in the service class by ensuring the mocked DAO returns a Null object.
	 * This is an abnormal condition that we dont expect to occur so this is an edge condition but we do want to test it 
	 * We want to ensure that code gracefully handles this scenario
	 * @throws Exception
	 */
	@Test
	public void test_getOrderSummary_throws_MyServiceException() throws Exception {			
		/** we expect an ex ception to be thrown - an abnormal condition  **/
		//assertThrows(MyServiceException.class, "System error occurred");
		assertThrows(MyServiceException.class,()-> {
			/** what should the mocked Dao method return ? - lets create that object and provide to Mocking framework **/
			//List<MyOrder> mockDaoResponse1 =  new ArrayList<MyOrder>();
			List<MyOrder> mockDaoResponse1 =  null;
			
			/** set up the Rule of when to Mock under what circumstance **/
			Mockito.when(mockOrderDao.findOrdersByCustomer(CUSTOMER_ID))
			.thenReturn(mockDaoResponse1);
			
			/** now execute **/
			/**Execution **/
			List <MyOrderSummary> result = target.getOrderSummary(CUSTOMER_ID);
		});
	}
	
	/**
	 * This test method is testing the +ve scenario when everything works well
	 * This is just a start and can be expanded further.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_getOrderSummary_success() throws Exception {
		/** setup **/
		MyOrderServiceImpl target = new MyOrderServiceImpl() ;

		/** we need to be mocking the DAO here - MyOrderDao is the class we want to mock **/
		MyOrderDao mockOrderDao = Mockito.mock(MyOrderDao.class);

		/** now inject the mocked DAO into the service class **/
		target.setOrderDao(mockOrderDao);

		//MyOrderEntityToOrderSummaryTransformer myTransformer = Mockito.mock(MyOrderEntityToOrderSummaryTransformer.class);
		MyOrderToOrderSummaryTransformer myTransformer = new MyOrderToOrderSummaryTransformer();

		target.setTransformer(myTransformer);

		List<MyOrder> mockDaoResponse =  new ArrayList<MyOrder>();
		MyOrder myOrder1 = new MyOrder();
		MyOrder myOrder2 = new MyOrder();
		
		mockDaoResponse.add(myOrder1);
		mockDaoResponse.add(myOrder2);


		Mockito.when(mockOrderDao.findOrdersByCustomer(CUSTOMER_ID))
		.thenReturn(mockDaoResponse);


		/**Execution **/
		List <MyOrderSummary> result = target.getOrderSummary(CUSTOMER_ID);

		/**verification **/
		assertNotNull(result);
		assertEquals(2, result.size());
	}
}
