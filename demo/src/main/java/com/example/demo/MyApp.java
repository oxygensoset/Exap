package com.example.demo;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyApp extends Application {
    private ListView<String> itemsListView;
    private ObservableList<String> itemsList;

    @Override
    public void start(Stage primaryStage) throws Exception {
        itemsList = FXCollections.observableArrayList();
        itemsListView = new ListView<>(itemsList);

        TextField nameField = new TextField();
        nameField.setPromptText("Название");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Описание");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Количество");

        TextField priceField = new TextField();
        priceField.setPromptText("Цена");

        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String quantity = quantityField.getText();
            String price = priceField.getText();
            String newItem = String.format("%s - %s, Quantity: %s, Price: %s", name, description, quantity, price);
            if (!newItem.trim().isEmpty()) {
                itemsList.add(newItem);
                nameField.clear();
                descriptionField.clear();
                quantityField.clear();
                priceField.clear();
            }
        });
        Button removeButton = new Button("Удалить");
        removeButton.setOnAction(e -> {
            String selectedItem = itemsListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                itemsList.remove(selectedItem);
            }
        });
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.addRow(0, nameField, descriptionField);
        inputGrid.addRow(1, quantityField, priceField);
        HBox inputBox = new HBox(10, inputGrid, addButton);
        inputBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(10, itemsListView, inputBox, removeButton);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("My App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}