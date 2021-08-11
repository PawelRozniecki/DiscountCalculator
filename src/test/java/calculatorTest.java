import com.pawelrozniecki.discountcalculator.Calculator;
import com.pawelrozniecki.discountcalculator.Product;
import com.pawelrozniecki.discountcalculator.builders.ProductBuilder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class calculatorTest {

    Calculator calculator;

    @Test
    public void testSumOnWholeNumbers() {
        List<Product> productList = new ArrayList<>();
        productList.add(new ProductBuilder().setName("SamsungTV").setPrice(new BigDecimal("1500")).build());
        productList.add(new ProductBuilder().setName("Playstation5").setPrice(new BigDecimal("2300")).build());
        productList.add(new ProductBuilder().setName("Coffee machine").setPrice(new BigDecimal("800")).build());

        calculator = new Calculator();

        BigDecimal sum = new BigDecimal("4600");
        assertEquals(sum.stripTrailingZeros(), calculator.sum(productList).stripTrailingZeros());
    }

    @Test
    public void testSumOnDecimals() {
        List<Product> productList = new ArrayList<>();
        productList.add(new ProductBuilder().setName("SamsungTV").setPrice(new BigDecimal("1500.50")).build());
        productList.add(new ProductBuilder().setName("Playstation5").setPrice(new BigDecimal("2300.30")).build());
        productList.add(new ProductBuilder().setName("Coffee machine").setPrice(new BigDecimal("800.29")).build());

        calculator = new Calculator();

        BigDecimal sum = new BigDecimal("4601.09");
        assertEquals(sum.stripTrailingZeros(), calculator.sum(productList).stripTrailingZeros());
    }

    @Test
    public void testDiscountPrice() {
        List<Product> productList = new ArrayList<>();
        calculator = new Calculator();

        productList.add(new ProductBuilder().setName("SamsungTV").setPrice(new BigDecimal("1500")).build());
        productList.add(new ProductBuilder().setName("Playstation5").setPrice(new BigDecimal("500")).build());
        BigDecimal discount = new BigDecimal("100");


        BigDecimal prod1 = new BigDecimal("75.00");
        BigDecimal prod2 = new BigDecimal("25.00");


        assertEquals(prod1, calculator.calculateDiscount(productList, discount).get(0).getPrice());
        assertEquals(prod2, calculator.calculateDiscount(productList, discount).get(1).getPrice());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMultiplyZero() {

        List<Product> productList = new ArrayList<>();
        calculator = new Calculator();

        productList.add(new ProductBuilder().setName("SamsungTV").setPrice(new BigDecimal("0")).build());
        productList.add(new ProductBuilder().setName("Playstation5").setPrice(new BigDecimal("0")).build());
        BigDecimal discount = new BigDecimal("10");

        System.out.println(calculator.calculateDiscount(productList, discount).get(0).getPrice());


    }

}
