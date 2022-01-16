package com.shopify.logistics.logisticsinventorymanager.service.impl;

import com.shopify.logistics.logisticsinventorymanager.dto.CreateItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.DeleteItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.EditItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.ItemDTO;
import com.shopify.logistics.logisticsinventorymanager.entity.Item;
import com.shopify.logistics.logisticsinventorymanager.repository.ItemRepository;
import com.shopify.logistics.logisticsinventorymanager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // this method enables the functionality to add an item.
    // This utilises the created repository to make a new entry for the inventory item.
    @Override
    public String addItem(CreateItemDTO createItemDTO) {
        try{
            Item item = new Item();
            item.setName(createItemDTO.getName());
            item.setType(createItemDTO.getType());
            item.setPrice(createItemDTO.getPrice());
            item.setDescription(item.getDescription());
            itemRepository.saveAndFlush(item);
            return "Inventory item added successfully";
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

    // this method enables editing an inventory item based on item name.
    // Attributes price, description, type can be edited.
    // Attributes ID and Name cannot be edited as of now.
    // this also returns the edited item back to the user and null when item could not be edited.
    @Override
    public ItemDTO editItem(EditItemDTO editItemDTO) {
        try{
            Item item = itemRepository.findByName(editItemDTO.getName());
            item.setDescription(editItemDTO.getDescription());
            item.setType(editItemDTO.getType());
            item.setPrice(editItemDTO.getPrice());
            itemRepository.saveAndFlush(item);

            // get item back from DB to ensure the correct data has been updated for the item name.
            item = itemRepository.findByName(editItemDTO.getName());
            ItemDTO itemDTO = new ItemDTO(item.getId(),item.getName(),item.getType(),item.getDescription(),item.getPrice());
            return itemDTO;
        }
        catch (Exception e)
        {
            System.out.println("Error encountered while getting item");
            return new ItemDTO();
        }
    }

    // this method enables deleting an inventory item based on item name.
    @Override
    public String deleteItem(DeleteItemDTO deleteItemDTO) {
        try{
            Item item = itemRepository.findByName(deleteItemDTO.getItemName());
            itemRepository.delete(item);
            return "Inventory item deleted successfully";
        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }

    // this method fetches a list of all inventory items.
    // we still need to implement a mapper to convert the entity into DTO before sending.
    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
