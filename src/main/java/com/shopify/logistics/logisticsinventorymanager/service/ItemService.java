package com.shopify.logistics.logisticsinventorymanager.service;

import com.shopify.logistics.logisticsinventorymanager.dto.CreateItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.DeleteItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.EditItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.ItemDTO;
import com.shopify.logistics.logisticsinventorymanager.entity.Item;

import java.util.List;

public interface ItemService {
    String addItem(CreateItemDTO itemDTO);
    ItemDTO editItem(EditItemDTO editItemDTO);
    String deleteItem(DeleteItemDTO deleteItemDTO);
    List<Item> getAllItems();
}
