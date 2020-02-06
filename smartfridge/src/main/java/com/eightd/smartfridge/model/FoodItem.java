package com.eightd.smartfridge.model;

import org.springframework.data.annotation.Id;

public class FoodItem {

    @Id
    private String id;

    private String name;
    private int quantity;

    public FoodItem() {}

    public FoodItem(String name) {
        this.name = name;
    }

    public FoodItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("FoodItem[name=%s]",
                this.name);
    }
}
