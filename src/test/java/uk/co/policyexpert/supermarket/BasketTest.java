package uk.co.policyexpert.supermarket;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.policyexpert.supermarket.offers.DiscountType;
import uk.co.policyexpert.supermarket.offers.Offer;

public class BasketTest {

	private Basket basket;
	private DiscountStrategy discountStrategy;
	private Map<String,Offer> map;
	
	private Price price_5;
	private Price price_10;
	private Price price_15;
	private Price price_20;
	private Product bean_can; 
	private Product coke_bottle;
	private Product coke_can ;
	private Product orange;
	private Product apple ;
	private Product banana ;
	
	
	@Before
	public void setup() {
		
		price_5 =  new Price(5.0f);
		price_10 =  new Price(10.0f);
		price_15 =  new Price(15.0f);
		price_20 =  new Price(20.0f);
		
		bean_can = new Product(ProductCodeConstants.BEAN_CAN,ProductType.BARCODED,price_10);
		coke_bottle = new Product(ProductCodeConstants.COKE_BOTTLE, ProductType.BARCODED,price_15);
		coke_can = new Product(ProductCodeConstants.COKE_CAN, ProductType.BARCODED,price_5);
		orange = new Product(ProductCodeConstants.ORANGE, ProductType.LOOSE,price_10);
		apple = new Product(ProductCodeConstants.APPLE, ProductType.LOOSE,price_15);
		banana = new Product(ProductCodeConstants.BANANA, ProductType.LOOSE,price_20);
		
		discountStrategy = new DiscountStrategy();
		map = new HashMap<>();
		basket = new Basket(map,discountStrategy);
	}
	
	@After
	public void cleanUp() {
		map.clear();
	}

	@Test
	public void testAddSingleBarCodedLineItem() {
		
		CartItem barCodedItem = new CartItem(bean_can);
		basket.addCartItem(barCodedItem);
		
		assertTrue(1 == basket.getTotalLineItem());
		assertTrue(new BigDecimal(10.0f,MathContext.DECIMAL64).compareTo(basket.getTotalPriceBeforeDiscount()) == 0);
		assertTrue(new BigDecimal(10.0f,MathContext.DECIMAL64).compareTo(basket.getTotalPriceAfterDiscount()) == 0);
		assertTrue(new BigDecimal(0.0f,MathContext.DECIMAL64).compareTo(basket.getTotalDiscount()) == 0);
	}
	
	@Test
	public void testAddMultipleQuantityOfSingleBarCodedProduct() {
		CartItem barCodedItem_1 = new CartItem(bean_can);
		CartItem barCodedItem_2 = new CartItem(bean_can);
		CartItem barCodedItem_3 = new CartItem(bean_can);

		basket.addCartItem(barCodedItem_1);
		basket.addCartItem(barCodedItem_2);
		basket.addCartItem(barCodedItem_3);
		
		assertTrue(1 == basket.getTotalLineItem());
		assertTrue(3 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(30.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(30.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());
	}
	
	@Test
	public void testAddMultipleQuantityofSingleBarCodedProductWithRemovingOneUnit() {
		CartItem barCodedItem_1 = new CartItem(bean_can);
		CartItem barCodedItem_2 = new CartItem(bean_can);
		CartItem barCodedItem_3 = new CartItem(bean_can);
		CartItem barCodedItem_4 = new CartItem(new Product(ProductCodeConstants.BEAN_CAN, ProductType.BARCODED,new Price(10.0f)));

		basket.addCartItem(barCodedItem_1);
		basket.addCartItem(barCodedItem_2);
		basket.addCartItem(barCodedItem_3);
		basket.addCartItem(barCodedItem_4);
		
		assertTrue(1 == basket.getTotalLineItem());
		assertTrue(4 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(40.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(40.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

		basket.decreaseByOne(barCodedItem_4);

		assertTrue(1 == basket.getTotalLineItem());
		assertTrue(3 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(30.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(30.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

	}

	@Test
	public void testAddMultipleQuantityofSingleBarCodedProductWithRemovingCartItem() {
		
		CartItem barCodedItem_1 = new CartItem(bean_can);
		CartItem barCodedItem_2 = new CartItem(bean_can);
		CartItem barCodedItem_3 = new CartItem(bean_can);
		CartItem barCodedItem_4 = new CartItem(bean_can);

		basket.addCartItem(barCodedItem_1);
		basket.addCartItem(barCodedItem_2);
		basket.addCartItem(barCodedItem_3);
		basket.addCartItem(barCodedItem_4);
		
		//checking product with right quantity are added 
		assertTrue(1 == basket.getTotalLineItem());
		assertTrue(4 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(40.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(40.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

		// remove the product altogether
		basket.removeCartItem(barCodedItem_4);

		assertTrue(0 == basket.getTotalLineItem());
		assertTrue(basket.getCartItems().isEmpty());
		assertTrue(0.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

	}

	@Test
	public void testAddMultipleQuantityofMultipleBarCodedProduct() {
		
		CartItem barCodedItem_1 = new CartItem(bean_can);
		CartItem barCodedItem_2 = new CartItem(bean_can);
		CartItem barCodedItem_3 = new CartItem(bean_can);
		CartItem barCodedItem_4 = new CartItem(bean_can);

		CartItem barCodedItem_5 = new CartItem(coke_bottle);
		CartItem barCodedItem_6 = new CartItem(coke_bottle);

		basket.addCartItem(barCodedItem_1);
		basket.addCartItem(barCodedItem_2);
		basket.addCartItem(barCodedItem_3);
		basket.addCartItem(barCodedItem_4);
		basket.addCartItem(barCodedItem_5);
		basket.addCartItem(barCodedItem_6);
		
		//checking product with right quantity are added 
		assertTrue(2 == basket.getTotalLineItem());
		assertTrue(4 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(2 == basket.getCartItems().get(1).getQuantity().intValue());
		assertTrue(70.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(70.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

	}

	@Test
	public void testAddMultipleQuantityofMultipleBarCodedProductWithReducingOneUnitEach() {
		
		CartItem barCodedItem_1 = new CartItem(bean_can);
		CartItem barCodedItem_2 = new CartItem(bean_can);
		CartItem barCodedItem_3 = new CartItem(bean_can);
		CartItem barCodedItem_4 = new CartItem(bean_can);

		CartItem barCodedItem_5 = new CartItem(coke_bottle);
		CartItem barCodedItem_6 = new CartItem(coke_bottle);

		basket.addCartItem(barCodedItem_1);
		basket.addCartItem(barCodedItem_2);
		basket.addCartItem(barCodedItem_3);
		basket.addCartItem(barCodedItem_4);
		basket.addCartItem(barCodedItem_5);
		basket.addCartItem(barCodedItem_6);
		
		//checking product with right quantity are added 
		assertTrue(2 == basket.getTotalLineItem());
		assertTrue(4 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(2 == basket.getCartItems().get(1).getQuantity().intValue());
		assertTrue(70.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(70.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

		//reducing the quantity for both products
		basket.decreaseByOne(barCodedItem_4);
		basket.decreaseByOne(barCodedItem_5);

		assertTrue(2 == basket.getTotalLineItem());
		assertTrue(3 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(1 == basket.getCartItems().get(1).getQuantity().intValue());
		assertTrue(45.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(45.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

	}
	
	@Test
	public void testAddMultipleQuantityofMultipleBarCodedProductWithRemovingOneCartItem() {
		
		CartItem barCodedItem_1 = new CartItem(bean_can);
		CartItem barCodedItem_2 = new CartItem(bean_can);
		CartItem barCodedItem_3 = new CartItem(bean_can);
		CartItem barCodedItem_4 = new CartItem(bean_can);

		
		CartItem barCodedItem_5 = new CartItem(coke_bottle);
		CartItem barCodedItem_6 = new CartItem(coke_bottle);

		basket.addCartItem(barCodedItem_1);
		basket.addCartItem(barCodedItem_2);
		basket.addCartItem(barCodedItem_3);
		basket.addCartItem(barCodedItem_4);
		basket.addCartItem(barCodedItem_5);
		basket.addCartItem(barCodedItem_6);
		
		//checking product with right quantity are added 
		assertTrue(2 == basket.getTotalLineItem());
		assertTrue(4 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(2 == basket.getCartItems().get(1).getQuantity().intValue());
		assertTrue(70.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(70.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

		//reducing the quantity for both products
		basket.decreaseByOne(barCodedItem_4);

		assertTrue(2 == basket.getTotalLineItem());
		assertTrue(3 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(2 == basket.getCartItems().get(1).getQuantity().intValue());
		assertTrue(60.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(60.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

	}

	@Test
	public void testAddSingleWeightedProduct() {
		CartItem weightedItem = new CartItem(orange,new BigDecimal(1), new BigDecimal(0.20f));
		
		basket.addCartItem(weightedItem);
		
		assertTrue(1 ==  basket.getTotalLineItem());
		assertTrue(2.0f ==  basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(2.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());
	}
	
	@Test
	public void testAddMultipleWeightedProduct() {
		CartItem looseItem_1 = new CartItem(orange, new BigDecimal(1), new BigDecimal(0.20f));
		CartItem looseItem_2 = new CartItem(apple, new BigDecimal(1),new BigDecimal(0.40f));

		basket.addCartItem(looseItem_1);
		basket.addCartItem(looseItem_2);
		
		assertTrue(2 ==  basket.getTotalLineItem());
		assertTrue(1 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(1 == basket.getCartItems().get(1).getQuantity().intValue());
		assertTrue(8.0f ==  basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(8.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());
	}
	
	@Test
	public void testAddMultipleBarCodedAndMultipleWeightedLineItems() {
		CartItem barCodedItem_1 = new CartItem(bean_can);
		CartItem barCodedItem_2 = new CartItem(coke_bottle);
		CartItem barCodedItem_3 = new CartItem(coke_can);
		CartItem weightedItem_1 = new CartItem(orange, new BigDecimal(1), new BigDecimal(0.4f));
		CartItem weightedItem_2 = new CartItem(apple, new BigDecimal(1), new BigDecimal(0.5f));
		CartItem weightedItem_3 = new CartItem(banana, new BigDecimal(1),new BigDecimal(2.5f));
		
		basket.addCartItem(barCodedItem_1);
		basket.addCartItem(barCodedItem_2);
		basket.addCartItem(barCodedItem_3);

		basket.addCartItem(weightedItem_1);
		basket.addCartItem(weightedItem_2);
		basket.addCartItem(weightedItem_3);
		
		assertTrue(6 == basket.getTotalLineItem());
		assertTrue(91.5f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(91.5f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());
	}
	
	@Test
	public void testApply2For1Offer() {
		BigDecimal quantity_2 = new BigDecimal(2);
		BigDecimal quantity_3 = new BigDecimal(3);
		
		map.put(ProductCodeConstants.COKE_CAN, new Offer(coke_can, quantity_2,DiscountType.TWO_FOR_ONE));
		map.put(ProductCodeConstants.BEAN_CAN, new Offer(bean_can,quantity_3,DiscountType.THREE_FOR_ONE));

		CartItem barCodedItem_1 = new CartItem(coke_can);
		CartItem barCodedItem_2 = new CartItem(coke_can);
		CartItem barCodedItem_3 = new CartItem(bean_can);
		CartItem barCodedItem_4 = new CartItem(bean_can);
		CartItem barCodedItem_5 = new CartItem(bean_can);
		CartItem barCodedItem_6 = new CartItem(bean_can);

		basket.addCartItem(barCodedItem_1);
		basket.addCartItem(barCodedItem_2);
		
		basket.addCartItem(barCodedItem_3);
		basket.addCartItem(barCodedItem_4);
		basket.addCartItem(barCodedItem_5);
		basket.addCartItem(barCodedItem_6);
		
		assertTrue(2 == basket.getTotalLineItem());
		assertTrue(2 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(4 == basket.getCartItems().get(1).getQuantity().intValue());
		assertTrue(50.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		//assertTrue(Math.abs(basket.getTotalPriceAfterDiscount() - 24.900002f) < 0.0001f);
		//assertTrue(Math.abs(basket.getTotalDiscount()- 25.099998) < 0.0001f);
		
	}
	
	@Test
	public void testRemoveSingleBarCodedProduct() {
		CartItem barCodedItem_1 = new CartItem(bean_can);

		basket.addCartItem(barCodedItem_1);
		
		assertTrue(1 == basket.getTotalLineItem());
		assertTrue(1 == basket.getCartItems().get(0).getQuantity().intValue());
		assertTrue(10.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(10.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

		basket.decreaseByOne(barCodedItem_1);
		
		assertTrue(0 == basket.getTotalLineItem());
		assertTrue(basket.getCartItems().isEmpty());
		assertTrue(0.0f == basket.getTotalPriceBeforeDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalPriceAfterDiscount().floatValue());
		assertTrue(0.0f == basket.getTotalDiscount().floatValue());

	}
}
