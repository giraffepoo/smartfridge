package com.eightb.smartfridge.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class FoodItem {
    @Id
    private String id;

    private String name;
    private int quantity;
    private Date createdDate;

    public FoodItem() {}

    public FoodItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.createdDate = new Date();
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return String.format("FoodItem[name=%s]",
                this.name);
    }

}
