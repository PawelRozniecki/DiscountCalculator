package com.pawelrozniecki.discountcalculator.builders;


import com.pawelrozniecki.discountcalculator.Product;

import java.math.BigDecimal;

public class ProductBuilder {


    private String name;
    private BigDecimal price;


    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


    /**
     * @return a new Product object
     */
    public Product build() {
        return new Product(name, price);
    }
}
