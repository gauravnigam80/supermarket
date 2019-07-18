# Supermarket

Implementation of Basket for Supermarket.

This project requires:
1. Java 8
2. Junit 4.7

## Problem Description

You are to deliver code that prices a supermarket shopping basket. Some items available are based on price per item, some items are based on weight. There are also group discounts to be aware of:

Three cans of beans for the price of two.
Two cans of Coke, for £1.

Use the following example of a receipt to know what data needs to be captured. There is no requirement to actually format or print a receipt.

Beans                0.50
Beans                0.50
Beans                0.50
Coke                 0.70
Coke                 0.70
Oranges
0.200 kg @  £1.99/kg 0.40
                    -----
Sub-total            3.30

Savings
Beans 3 for 2       -0.50
Coke 2 for £1       -0.40
                    -----
Total savings       -0.90
-------------------------
Total to Pay         2.40

Ref : https://policyexpert.github.io/coding-assessment/ and http://codekata.com/kata/kata01-supermarket-pricing/

## Implementation Details:

Most of the computation logic is in Basket.java. Ideally, it should be in the Services. However, due to time constraint, I am not doing it now.
Other important classes are - DiscountType.java, Offer.java, ProductType.java, DiscountStrategy.java 

# uk.co.policyexpert.supermarket.DiscountStrategy.java
# 
## Test Implementation:
Unit Test class is provided at 
1. test/us.co.sky.policyexpert.supermarket.BasketTest
2. test/us.co.sky.policyexpert.supermarket.CartItemTest

## Running the Individual Test
The Super Market implementation can be tested by running the Individual Test as JUnit.

## Running the All Test
Use Maven to run all the test - clean test
