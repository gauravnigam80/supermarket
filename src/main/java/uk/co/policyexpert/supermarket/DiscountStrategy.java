package uk.co.policyexpert.supermarket;

import java.util.List;
import java.util.Map;

import uk.co.policyexpert.supermarket.offers.Offer;

public class DiscountStrategy {

	public Float calculateDiscount(final Map<String, Offer> offerMap, final List<CartItem> set) {

		float totalDiscount = 0.0f;

		//apply discount if the offer matches with line item
		for(CartItem item : set) {
			Offer offer = offerMap.get(item.getProduct().getProductCode());
			if(offer != null 
					&& item.getProduct().getProductCode().equals(offer.getProduct().getProductCode())
					&& item.getQuantity() >= offer.getQuantity()) {
						//This is the logic to handle the case when cart item quantity is more than offer 
						// eg. when cart item are 3 and offer is 2 For 1 
						int mod = item.getQuantity() % offer.getQuantity();
						int quantityOnWhichOfferIsApplied = mod == 0 ? item.getQuantity() : item.getQuantity() - mod;
						totalDiscount += offer.getDiscountType().getDiscount().applyDiscount(new CartItem(item.getProduct(), quantityOnWhichOfferIsApplied));
			}
		}
		return totalDiscount;
	}
}
