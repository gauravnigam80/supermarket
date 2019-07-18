package uk.co.policyexpert.supermarket;

public class Product {

	private String productCode;

	private ProductType productType;
	
	private Price price;
	
	
	/**
	 * @param productCode
	 * @param productType
	 * @param price
	 */
	public Product(String productCode, ProductType productType, Price price) {
		super();
		this.productCode = productCode;
		this.productType = productType;
		this.price = price;
	}
	
	/**
	 * @return the price
	 */
	public Price getPrice() {
		return price;
	}


	public String getProductCode() {
		return productCode;
	}
	

	/**
	 * @return the productType
	 */
	public ProductType getProductType() {
		return productType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
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
		Product other = (Product) obj;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (productType != other.productType)
			return false;
		return true;
	}


	
}
