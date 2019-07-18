package uk.co.policyexpert.supermarket;

public class CartItem {

	private final Product product;

	private Integer quantity;
	
	private Float weight ;

	
	/**
	 * @param product
	 */
	public CartItem(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	/**
	 * @param product
	 */
	public CartItem(Product product) {
		this(product, 1); 
	}

	/**
	 * @param product
	 * @param weight
	 */
	public CartItem(Product product, Float weight) {
		this(product); 
		this.weight = weight;
	}


	public Float getLineItemTotalBeforeDiscount() {
		switch(product.getProductType()) {
		case LOOSE : 
			return this.weight * product.getPrice().getUnitPrice();
		default : 	
			return quantity * this.product.getPrice().getUnitPrice() ;
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
	public Integer addOne() {
		return quantity = quantity + 1;
	}

	/**
	 * @return the quantity
	 */
	public Integer reduceOne() {
		return quantity = quantity - 1;
	}
	
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
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
