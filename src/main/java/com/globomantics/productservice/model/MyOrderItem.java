package com.globomantics.productservice.model;

import java.math.BigDecimal;

public class MyOrderItem {
	private int quantity;
	private BigDecimal sellingPrice;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
}
