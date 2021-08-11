package com.pawelrozniecki.discountcalculator.controllers;


import com.pawelrozniecki.discountcalculator.Calculator;
import com.pawelrozniecki.discountcalculator.Product;
import com.pawelrozniecki.discountcalculator.builders.ProductBuilder;
import com.pawelrozniecki.discountcalculator.interfaces.IProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Controller implements IProduct {
    private final TableColumn<Product, String> prodNameCol = new TableColumn<>("Product name");
    private final TableColumn<Product, String> prodPriceCol = new TableColumn<>("Product price");
    private final Integer MAX_ITEMS = 5;
    private final List<Product> productList = new ArrayList<>();
    private final ObservableList<Product> data = FXCollections.observableArrayList();
    private final Calculator calculator = new Calculator();

    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField discountPrice;
    @FXML
    private TableView<Product> tableView;
    @FXML
    private ListView<String> finalPriceList;

    // Sets up the table
    @FXML
    private void initialize() {
        tableView.getColumns().addAll(prodNameCol, prodPriceCol);
        tableView.setPlaceholder(new Label("No products were added yet"));
        prodNameCol.setPrefWidth(tableView.getPrefWidth() * 0.5);
        prodPriceCol.setPrefWidth(tableView.getPrefWidth() * 0.5);
        prodNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));

    }

    public void addProduct() {
        tableView.setItems(data);

        try {
            BigDecimal price = new BigDecimal(productPrice.textProperty().getValue());
            String name = productName.textProperty().getValue();

            // Allow maximum of 5 items
            if (productList.size() < MAX_ITEMS && price.compareTo(BigDecimal.ZERO) > 0 && !name.trim().isEmpty()) {
                Product prod = new ProductBuilder().setName(name).setPrice(price).build();
                productList.add(prod);
                data.add(prod);
            } else {
                Alert dialog = new Alert(Alert.AlertType.WARNING, "Make sure to fill every text field. Remember you cannot enter  more than 5 products as well as negative values or 0  for product price", ButtonType.OK);
                dialog.show();
            }
        } catch (NumberFormatException e) {
            System.out.println("Only numeric values accepted for price field");
        }
    }

    @Override
    public void calculateDiscount() {

        finalPriceList.getItems().clear();

        // Gets the  discount value from the TextField
        try {
            BigDecimal rabat = new BigDecimal(discountPrice.textProperty().getValue());


            // Add final discount prices to a ListView
            calculator.calculateDiscount(productList, rabat)
                    .forEach(product
                            -> finalPriceList
                            .getItems()
                            .add("Rabat Do " + product.getName() + " " + product.getPrice()));

        } catch (NumberFormatException e) {
            System.out.println("You need to enter a discount price");
        }
    }

    // Clears TableView, ListView and productList
    public void clear() {
        tableView.getItems().clear();
        productList.clear();
        finalPriceList.getItems().clear();
    }


}
