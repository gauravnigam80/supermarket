package uk.co.policyexpert.supermarket.offers;

import java.math.BigDecimal;

import uk.co.policyexpert.supermarket.CartItem;

public interface Discount {
	BigDecimal applyDiscount(CartItem lineItem);
}
