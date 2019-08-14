package uk.co.policyexpert.supermarket;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import uk.co.policyexpert.supermarket.offers.Offer;

public class Basket {

	private List<CartItem> cartItemList = new ArrayList<>();

	private BigDecimal totalPriceBeforeDiscount = new BigDecimal(0.0f,MathContext.DECIMAL64);

	private BigDecimal totalPriceAfterDiscount = new BigDecimal(0.0f,MathContext.DECIMAL64);

	private BigDecimal totalDiscount = new BigDecimal(0.0f,MathContext.DECIMAL64);

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
			BigDecimal qty = cartItem.reduceOne();
			if(qty.floatValue() == 0) {
				cartItemList.remove(cartItem);
			}
			isLineItemReduced = true;
			refreshTotal();	
		}
		return isLineItemReduced  ;
	}
	
	private void refreshTotal() {
		totalPriceBeforeDiscount = new BigDecimal(0.0f,MathContext.DECIMAL64);
		cartItemList.forEach(item -> totalPriceBeforeDiscount = totalPriceBeforeDiscount.add(item.getLineItemTotalBeforeDiscount()));
		totalDiscount = discountStrategy.calculateDiscount(this.offerMap, this.cartItemList);
		totalPriceAfterDiscount = totalPriceBeforeDiscount.subtract(totalDiscount);
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
	public BigDecimal getTotalPriceBeforeDiscount() {
		return totalPriceBeforeDiscount;
	}

	public BigDecimal getTotalPriceAfterDiscount() {
		return totalPriceAfterDiscount;
	}

	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}

	public Optional<CartItem> findCartItemByProductCode(String productCode){
		return this.getCartItems().stream().filter(item -> item.getProduct().getProductCode().equals(productCode)).findFirst();
	}
}
