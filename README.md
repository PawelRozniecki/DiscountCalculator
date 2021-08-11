# DiscountCalculator

The purpose of this application is to calculate a discount for products in the amount proportional to their price.
The application was written in Java 8 and utilizes JavaFX for making a graphical user interface. The application handles the edge cases like negative input, division by zero, invalid or empty inputs in fields, and allows a maximum of 5 products to be added. 

## Requirements 
* Java 8
* Maven 
* JavaFX


## Running the app
JAR file can be found in  https://github.com/PawelRozniecki/DiscountCalculator/blob/main/out/artifacts/UnityZadanieFX/UnityZadanieFX.jar

## Design

The app was designed using JavaFX for  the purpose of ease of use. 
### Prices 

BigDecimal  was used for storing the prices because it allows for the full control over the precision and price rounding. It's generally more accuare than storing the prices in float or double. 

###  Product class

Builder Design pattern was used for creation of Product objects because it adds  design flexibility and results in  more readable code. 

### Testing 

Junit was used for unit tests. The test coverage for Calculator class is 100%. 





