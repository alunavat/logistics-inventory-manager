package com.shopify.logistics.logisticsinventorymanager.service.impl;

import com.shopify.logistics.logisticsinventorymanager.Constants;
import com.shopify.logistics.logisticsinventorymanager.dto.CreateItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.DeleteItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.EditItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.ItemDTO;
import com.shopify.logistics.logisticsinventorymanager.entity.Item;
import com.shopify.logistics.logisticsinventorymanager.repository.ItemRepository;
import com.shopify.logistics.logisticsinventorymanager.service.ItemService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger LOGGER = LogManager.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemRepository itemRepository;

    // this method enables the functionality to add an item.
    // This utilises the created repository to make a new entry for the inventory item.
    @Override
    public String addItem(CreateItemDTO createItemDTO) {
        try{
            LOGGER.debug("Checking if the item exists in DB for " + createItemDTO.getName());
            Item item = itemRepository.findByName(createItemDTO.getName());
            if(!ObjectUtils.isEmpty(item))
            {
                LOGGER.debug("Item name already exists. Return error message");
                return Constants.ITEM_FAILURE_EXISTS;
            }
            item = new Item();
            item.setName(createItemDTO.getName());
            item.setType(createItemDTO.getType());
            item.setPrice(createItemDTO.getPrice());
            item.setDescription(createItemDTO.getDescription());
            LOGGER.debug("Saving new item in DB " + createItemDTO.toString());
            itemRepository.saveAndFlush(item);
            return Constants.ITEM_SUCCESS;
        }
        catch (Exception e)
        {
            LOGGER.debug(e.getMessage());
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
            LOGGER.debug("Checking if item exists in DB for " + editItemDTO.getName());
            Item item = itemRepository.findByName(editItemDTO.getName());
            if(ObjectUtils.isEmpty(item))
            {
                LOGGER.debug("Item not found thus exit");
                return null;
            }
            item.setDescription(editItemDTO.getDescription());
            item.setType(editItemDTO.getType());
            item.setPrice(editItemDTO.getPrice());
            LOGGER.debug("Updating item in DB " + editItemDTO.toString());
            itemRepository.saveAndFlush(item);

            // get item back from DB to ensure the correct data has been updated for the item name.
            LOGGER.debug("Fetching updated item from DB for " + editItemDTO.getName());
            item = itemRepository.findByName(editItemDTO.getName());
            ItemDTO itemDTO = new ItemDTO(item.getId(),item.getName(),item.getType(),item.getDescription(),item.getPrice());
            return itemDTO;
        }
        catch (Exception e)
        {
            LOGGER.debug(e.getMessage());
            return null;
        }
    }

    // this method enables deleting an inventory item based on item name.
    @Override
    public String deleteItem(DeleteItemDTO deleteItemDTO) {
        try{
            LOGGER.debug("Checking if item exists in DB for " + deleteItemDTO.getName());
            Item item = itemRepository.findByName(deleteItemDTO.getName());
            if(ObjectUtils.isEmpty(item))
            {
                LOGGER.debug("Item not found thus exit");
                return Constants.ITEM_DELETED_FAILURE;
            }
            LOGGER.debug("Delete item from DB " + deleteItemDTO.toString());
            itemRepository.delete(item);
            return Constants.ITEM_DELETED_SUCCESS;
        }
        catch (Exception e)
        {
            LOGGER.debug(e.getMessage());
            return e.getMessage();
        }
    }

    // this method fetches a list of all inventory items.
    // we still need to implement a mapper to convert the entity into DTO before sending.
    @Override
    public List<Item> getAllItems() {
        LOGGER.debug("Get all items from DB");
        return itemRepository.findAll();
    }
}
