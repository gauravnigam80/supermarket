package uk.co.policyexpert.supermarket.offers;

import uk.co.policyexpert.supermarket.CartItem;

public class PercenatageDiscount implements Discount{

    private float discountPercent ;

    public float getDiscountPercent() {
        return discountPercent;
    }

    public PercenatageDiscount(float discountPercent) {
        super();
      	assert discountPercent <= 1;
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "Discount [ discountPercent=" + discountPercent + "]";
    }

	@Override
	public float applyDiscount(CartItem lineItem) {
		return lineItem.getLineItemTotalBeforeDiscount() - (lineItem.getLineItemTotalBeforeDiscount() * ( discountPercent));
	}

}
