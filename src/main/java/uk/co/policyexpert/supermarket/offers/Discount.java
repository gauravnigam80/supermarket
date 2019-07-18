package uk.co.policyexpert.supermarket.offers;

import uk.co.policyexpert.supermarket.CartItem;

public interface Discount {
	float applyDiscount(CartItem lineItem);
}
