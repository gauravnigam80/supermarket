package uk.co.policyexpert.supermarket.offers;

public enum DiscountType {

	TWO_FOR_ONE(new PercenatageDiscount(0.50f)),
	THREE_FOR_ONE(new PercenatageDiscount(0.33f));
	
	private final Discount discount;
	
	public Discount getDiscount() {
        return discount;
    }

    private DiscountType(Discount discount) {
        this.discount = discount;
    }
    
}
