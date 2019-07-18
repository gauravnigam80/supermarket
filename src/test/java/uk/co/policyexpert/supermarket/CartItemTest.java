package uk.co.policyexpert.supermarket;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CartItemTest {

	private CartItem lineItem = null;
	
	@Before
	public void setUp() throws Exception {
		lineItem = new CartItem(new Product("BEAN_CAN_001", ProductType.BARCODED,new Price(10.0f)));
	}

	@Test
	public void testGetLineItemTotalBeforeDiscount() {
		assertTrue(10.0f == lineItem.getLineItemTotalBeforeDiscount());
	}

}
