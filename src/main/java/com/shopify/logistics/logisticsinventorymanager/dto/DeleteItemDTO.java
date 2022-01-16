package com.shopify.logistics.logisticsinventorymanager.dto;

import com.shopify.logistics.logisticsinventorymanager.entity.Item;

public class DeleteItemDTO {
    private String itemName;

    public DeleteItemDTO() {
    }

    public DeleteItemDTO(Item item) {
        this.itemName = item.getName();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
