package com.eightb.smartfridge.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class FoodItem {
    @Id
    private String id;

    private String name;
    private int quantity;
    private Date createdDate;
    private int imageId;
    private int ownerId;

    public FoodItem() {}

    public FoodItem(String name, int quantity, int imageId) {
        this.name = name;
        this.quantity = quantity;
        this.createdDate = new Date();
        this.imageId = imageId;
        this.ownerId = 0; //todo: update this value based on owner of item
    }

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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return String.format("FoodItem[name=%s]",
                this.name);
    }

}
