package uk.co.policyexpert.supermarket;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Map;

import uk.co.policyexpert.supermarket.offers.Offer;

public class DiscountStrategy {

	public BigDecimal calculateDiscount(final Map<String, Offer> offerMap, final List<CartItem> set) {

		BigDecimal totalDiscount = new BigDecimal(0.0d,MathContext.DECIMAL64);

		//apply discount if the offer matches with line item
		for(CartItem item : set) {
			Offer offer = offerMap.get(item.getProduct().getProductCode());
			if(offer != null 
					&& item.getProduct().getProductCode().equals(offer.getProduct().getProductCode())
					&& item.getQuantity().compareTo(offer.getQuantity())>=0) {
						//This is the logic to handle the case when cart item quantity is more than offer 
						// eg. when cart item are 3 and offer is 2 For 1 
						BigDecimal mod = item.getQuantity().remainder(offer.getQuantity());
						BigDecimal quantityOnWhichOfferIsApplied = mod.floatValue() == 0 ? item.getQuantity() : item.getQuantity().subtract(mod);
						totalDiscount =  totalDiscount.add(offer.getDiscountType().getDiscount().applyDiscount(new CartItem(item.getProduct(), quantityOnWhichOfferIsApplied)));
			}
		}
		return totalDiscount;
	}
}
