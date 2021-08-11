package com.pawelrozniecki.discountcalculator;

import com.pawelrozniecki.discountcalculator.builders.ProductBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    /**
     * Calculates the sum of product prices
     * @param productList - takes a product list
     * @return BigDecimal representing a sum of all product prices from productList
     */

    public BigDecimal sum(List<Product> productList) {
        return productList.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     *
     * Calculates discount for each product  based on 2 params
     * @param productList - takes a product list
     * @param discount - a discount price
     * @return List with calculated discount prices*
     *
     *  */
    public List<Product> calculateDiscount(List<Product> productList, BigDecimal discount) {

        List<Product> discountList = new ArrayList<>();

        // For each product in a list, add to a discount list,  Divide by the total sum and multiply by a discount price

        if (discount != null && discount.compareTo(BigDecimal.ZERO) >= 0) {

            try {
                productList.forEach(product ->
                        discountList.add(new ProductBuilder().setName(product.getName()).setPrice(product.getPrice().
                                setScale(2, RoundingMode.HALF_UP).divide(sum(productList), RoundingMode.HALF_UP).multiply(discount)).build()));
            } catch (ArithmeticException e) {
                System.out.println("not possible to divide by zero");

            } catch (NumberFormatException e) {
                System.out.println("Not a number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Cannot calculate without any products");
            }
        }
        // Displays  discounted values in console
        discountList.forEach(product -> System.out.println("Rabat Do " + product.getName() + " " + product.getPrice()));

        return discountList;
    }


}
