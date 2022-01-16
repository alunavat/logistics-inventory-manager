package com.shopify.logistics.logisticsinventorymanager.dto;

import com.shopify.logistics.logisticsinventorymanager.entity.Item;

public class EditItemDTO {
    private String name;
    private String type;
    private String description;
    private double price;

    public EditItemDTO() {
    }

    public EditItemDTO(Item item) {
        this.name = item.getName();
        this.type = item.getType();
        this.description = item.getDescription();
        this.price = item.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
