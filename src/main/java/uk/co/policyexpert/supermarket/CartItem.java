package uk.co.policyexpert.supermarket;

import java.math.BigDecimal;

public class CartItem {

	private final Product product;

	private BigDecimal quantity;
	
	private BigDecimal weight ;

	
	/**
	 * @param product
	 */
	public CartItem(Product product, BigDecimal quantity, BigDecimal weight) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.weight = weight;
	}

	public CartItem(Product product, BigDecimal quantity) {
		this(product,quantity,null);
	}
	/**
	 * @param product
	 */
	public CartItem(Product product) {
		this(product, new BigDecimal(1),null); 
	}

	/**
	 * @param product
	 * @param weight
	 */
	/*public CartItem(Product product, BigDecimal weight) {
		this(product); 
	}*/


	public BigDecimal getLineItemTotalBeforeDiscount() {
		switch(product.getProductType()) {
		case LOOSE : 
			return this.product.getPrice().getUnitPrice().multiply(this.weight);
		default : 	
			return this.product.getPrice().getUnitPrice().multiply(this.quantity) ;
		}
	}


	/**
	 * @return the product
	 */
	public final Product getProduct() {
		return product;
	}


	/**
	 * @return the quantity
	 */
	public BigDecimal addOne() {
		return quantity = quantity.add(BigDecimal.ONE);
	}

	/**
	 * @return the quantity
	 */
	public BigDecimal reduceOne() {
		return quantity = quantity.subtract(BigDecimal.ONE);
	}
	
	/**
	 * @return the quantity
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	
}
