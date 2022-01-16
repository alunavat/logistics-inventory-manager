package com.shopify.logistics.logisticsinventorymanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long inventoryId;

    String itemName;
    int itemQuantity;
}
