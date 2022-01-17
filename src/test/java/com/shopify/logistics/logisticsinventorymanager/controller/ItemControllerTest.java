package com.shopify.logistics.logisticsinventorymanager.controller;

import com.shopify.logistics.logisticsinventorymanager.Constants;
import com.shopify.logistics.logisticsinventorymanager.dto.CreateItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.DeleteItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.EditItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.ItemDTO;
import com.shopify.logistics.logisticsinventorymanager.entity.Item;
import com.shopify.logistics.logisticsinventorymanager.service.ItemService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ItemControllerTest {

    @Mock
    ItemService itemService;

    @InjectMocks
    ItemController itemController;

    @BeforeEach
    public void init()
    {
        Mockito.when(itemService.addItem(Mockito.any())).thenReturn(Constants.ITEM_SUCCESS);
        Mockito.when(itemService.editItem(Mockito.any())).thenReturn(itemDTO());
        Mockito.when(itemService.getAllItems()).thenReturn(Arrays.asList(item()));
        Mockito.when(itemService.deleteItem(Mockito.any())).thenReturn(Constants.ITEM_DELETED_SUCCESS);
    }


    @Test
    public void testCreateItemWithRegularFlow()
    {
        CreateItemDTO createItemDTO = createItemDTO();
        String result = itemController.addItem(createItemDTO);
        Assert.assertEquals(Constants.ITEM_SUCCESS,result);
    }

    @Test
    public void testEditItemWithRegularFlow()
    {
        EditItemDTO editItemDTO = editItemDTO();
        ItemDTO result = itemController.editItem(editItemDTO);
        System.out.println(result.toString());
        Assert.assertEquals(itemDTO().toString(),result.toString());
    }

    @Test
    public void testGetAllItemsWithRegularFlow()
    {
        CreateItemDTO createItemDTO = createItemDTO();
        String result = itemController.addItem(createItemDTO);
        Assert.assertEquals(Constants.ITEM_SUCCESS,result);
        ResponseEntity<List<Item>> result1 = itemController.findAll();
        Assert.assertEquals(item().toString(),result1.getBody().get(0).toString());
    }

    @Test
    public void testDeleteItemWithRegularFlow()
    {
        CreateItemDTO createItemDTO = createItemDTO();
        String result = itemController.addItem(createItemDTO);
        Assert.assertEquals(Constants.ITEM_SUCCESS,result);
        result = itemController.deleteItem(deleteItemDTO());
        Assert.assertEquals(Constants.ITEM_DELETED_SUCCESS,result);
    }

    private CreateItemDTO createItemDTO()
    {
        CreateItemDTO createItemDTO = new CreateItemDTO();
        createItemDTO.setName("Test Name");
        createItemDTO.setDescription("Test Description");
        createItemDTO.setPrice(10.0);
        createItemDTO.setType("Test Type");
        return createItemDTO;
    }

    private ItemDTO itemDTO()
    {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Test Name");
        itemDTO.setDescription("Test Description");
        itemDTO.setPrice(10.0);
        itemDTO.setType("Test Type");
        itemDTO.setId(1l);
        return itemDTO;
    }

    private Item item()
    {
        Item item = new Item();
        item.setName("Test Name");
        item.setDescription("Test Description");
        item.setPrice(10.0);
        item.setType("Test Type");
        item.setId(1l);
        return item;
    }

    private EditItemDTO editItemDTO()
    {
        EditItemDTO editItemDTO = new EditItemDTO();
        editItemDTO.setName("Test Name");
        editItemDTO.setDescription("Test Description");
        editItemDTO.setPrice(10.0);
        editItemDTO.setType("Test Type");
        return editItemDTO;
    }

    private DeleteItemDTO deleteItemDTO()
    {
        DeleteItemDTO deleteItemDTO = new DeleteItemDTO();
        deleteItemDTO.setName("Test Name");
        return deleteItemDTO;
    }

}
