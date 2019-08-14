package uk.co.policyexpert.supermarket.offers;

import java.math.BigDecimal;

import uk.co.policyexpert.supermarket.CartItem;

public class PercenatageDiscount implements Discount{

    private BigDecimal discountPercent ;

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public PercenatageDiscount(BigDecimal discountPercent) {
        super();
      	assert discountPercent.floatValue() <= 1;
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "Discount [ discountPercent=" + discountPercent + "]";
    }

	@Override
	public BigDecimal applyDiscount(CartItem lineItem) {
		return lineItem.getLineItemTotalBeforeDiscount().subtract(lineItem.getLineItemTotalBeforeDiscount().multiply(discountPercent));
	}

}
