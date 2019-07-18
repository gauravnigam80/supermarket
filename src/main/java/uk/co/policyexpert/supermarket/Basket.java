package uk.co.policyexpert.supermarket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.policyexpert.supermarket.offers.Offer;

public class Basket {

	private List<CartItem> cartItemList = new ArrayList<>();

	private Float totalPriceBeforeDiscount = 0.0f;

	private Float totalPriceAfterDiscount = 0.0f;

	private Float totalDiscount = 0.0f;

	/* Key Value Pair of product code and Offer. */
	Map<String, Offer> offerMap = new HashMap<>();

	private DiscountStrategy discountStrategy;

	/**
	 * @param offerMap
	 * @param discountStrategy
	 */
	public Basket(Map<String, Offer> offerMap, DiscountStrategy discountStrategy) {
		super();
		this.offerMap = offerMap;
		this.discountStrategy = discountStrategy;
	}

	public void addCartItem(CartItem cartItem) {
		int idxPos = cartItemList.indexOf(cartItem);
		if( idxPos != -1) {
			cartItem = cartItemList.get(idxPos);
			cartItem.addOne();
		}else {
			cartItemList.add(cartItem);
		}
		refreshTotal();
	}

	public int getTotalLineItem() {
		return cartItemList.size();
	}

	public Boolean removeCartItem(CartItem cartItem) {
		Boolean isLineItemRemoved = cartItemList.remove(cartItem);
		refreshTotal();
		return isLineItemRemoved;
	}

	/**
	 * if cart item already in the basket then remove the item when there is only one quantity else reduce it by one.
	 * if cart item not  
	 **/
	public Boolean decreaseByOne(CartItem cartItem) {
		Boolean isLineItemReduced = false;
		int idxPos = cartItemList.indexOf(cartItem);
		if( idxPos != -1) {
			cartItem = cartItemList.get(idxPos);
			int qty = cartItem.reduceOne();
			if(qty == 0) {
				cartItemList.remove(cartItem);
			}
			isLineItemReduced = true;
			refreshTotal();	
		}
		return isLineItemReduced  ;
	}
	
	private void refreshTotal() {
		totalPriceBeforeDiscount = 0.0f;
		cartItemList.forEach(item -> totalPriceBeforeDiscount += item.getLineItemTotalBeforeDiscount());
		totalDiscount = discountStrategy.calculateDiscount(this.offerMap, this.cartItemList);
		totalPriceAfterDiscount = totalPriceBeforeDiscount - totalDiscount;
	}

	/**
	 * @return the lineItemList
	 */
	public List<CartItem> getCartItems() {
		return Collections.unmodifiableList(cartItemList);
	}

	/**
	 * @return the totalPriceBeforeDiscount
	 */
	public Float getTotalPriceBeforeDiscount() {
		return totalPriceBeforeDiscount;
	}

	public Float getTotalPriceAfterDiscount() {
		return totalPriceAfterDiscount;
	}

	public Float getTotalDiscount() {
		return totalDiscount;
	}

}
