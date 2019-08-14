package uk.co.policyexpert.supermarket.offers;

import java.math.BigDecimal;

public enum DiscountType {

	TWO_FOR_ONE(new PercenatageDiscount(new BigDecimal(0.50f))),
	THREE_FOR_ONE(new PercenatageDiscount(new BigDecimal(0.33f)));
	
	private final Discount discount;
	
	public Discount getDiscount() {
        return discount;
    }

    private DiscountType(Discount discount) {
        this.discount = discount;
    }
    
}
