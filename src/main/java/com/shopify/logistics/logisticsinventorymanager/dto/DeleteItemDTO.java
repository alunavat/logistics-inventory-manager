package com.shopify.logistics.logisticsinventorymanager.dto;

import com.shopify.logistics.logisticsinventorymanager.entity.Item;

import java.io.Serializable;

public class DeleteItemDTO implements Serializable {
    private String name;

    // Added for serializable. Do not delete!
    public DeleteItemDTO() {
    }

    public DeleteItemDTO(Item item) {
        this.name = item.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
