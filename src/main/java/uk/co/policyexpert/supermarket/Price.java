package uk.co.policyexpert.supermarket;

import java.math.BigDecimal;
import java.math.MathContext;

public class Price {

	private final BigDecimal unitPrice;

	/**
	 * @param unitPrice
	 */
	public Price(Float unitPrice) {
		this.unitPrice = new BigDecimal(unitPrice, MathContext.DECIMAL128);
	}

	/**
	 * @param unitPrice
	 */
	public Price(BigDecimal unitPrice) {
		super();
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	
}
