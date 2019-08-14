package uk.co.policyexpert.supermarket.offers;

import java.math.BigDecimal;

import uk.co.policyexpert.supermarket.Product;

public class Offer {

	private final Product product;
	private final DiscountType discountType;
	private final BigDecimal quantity;
	
	/**
	 * @param product
	 * @param quantity
	 * @param discountType
	 */
	public Offer(Product product, BigDecimal quantity, DiscountType discountType) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.discountType = discountType;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	/**
	 * @return the discount
	 */
	public DiscountType getDiscountType() {
		return discountType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Offer [product=" + product + ", quantity=" + quantity + ", discountType=" + discountType + "]";
	}
	
	
	
}
