package com.shopify.logistics.logisticsinventorymanager.controller;

import com.shopify.logistics.logisticsinventorymanager.dto.CreateItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.DeleteItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.EditItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.ItemDTO;
import com.shopify.logistics.logisticsinventorymanager.entity.Item;
import com.shopify.logistics.logisticsinventorymanager.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@Api("Perform CRUD operations on inventory items")
public class ItemController {
    private static final Logger LOGGER = LogManager.getLogger(ItemController.class);

    @Autowired
    ItemService itemService;

    @GetMapping
    @ApiOperation("Get a list of all items")
    public ResponseEntity<List<Item>> findAll(){
        LOGGER.debug("Calling service to fetch all items from DB");
        return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItems());
    }

    @PutMapping("/edit")
    @ApiOperation("Edit an inventory item. Allows editing type, description, price using item Name")
    public ItemDTO editItem(@RequestBody EditItemDTO editItemDTO){
        LOGGER.debug("Calling service to edit " + editItemDTO.getName());
        return itemService.editItem(editItemDTO);
    }

    @PostMapping()
    @ApiOperation("Create Inventory Item")
    public String addItem(@RequestBody CreateItemDTO createItemDTO){
        LOGGER.debug("Calling service to create " + createItemDTO.getName());
        return itemService.addItem(createItemDTO);
    }

    @PostMapping("/delete")
    @ApiOperation("Delete Inventory Item")
    public String deleteItem(@RequestBody DeleteItemDTO deleteItemDTO){
        LOGGER.debug("Calling service to delete " + deleteItemDTO.getName());
        return itemService.deleteItem(deleteItemDTO);
    }

}
