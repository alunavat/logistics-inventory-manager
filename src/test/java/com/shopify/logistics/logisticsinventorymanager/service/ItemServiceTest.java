package com.shopify.logistics.logisticsinventorymanager.service;

import com.shopify.logistics.logisticsinventorymanager.Constants;
import com.shopify.logistics.logisticsinventorymanager.dto.CreateItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.DeleteItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.EditItemDTO;
import com.shopify.logistics.logisticsinventorymanager.dto.ItemDTO;
import com.shopify.logistics.logisticsinventorymanager.entity.Item;
import com.shopify.logistics.logisticsinventorymanager.repository.ItemRepository;
import com.shopify.logistics.logisticsinventorymanager.service.impl.ItemServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class ItemServiceTest {
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemServiceImpl itemServiceImpl;

    @BeforeEach
    public void init()
    {
        Mockito.when(itemRepository.findByName(createItemDTO().getName())).thenReturn(item());
        Mockito.when(itemRepository.findAll()).thenReturn(Arrays.asList(item()));
    }

    @Test
    public void testAddItemWithRegularFlow()
    {
        Mockito.when(itemRepository.findByName(createItemDTO().getName())).thenReturn(null);
        CreateItemDTO createItemDTO = createItemDTO();
        String result = itemServiceImpl.addItem(createItemDTO);
        Assert.assertEquals(Constants.ITEM_SUCCESS,result);
    }

    @Test
    public void testAddItemWithErrorFlow()
    {
        CreateItemDTO createItemDTO = createItemDTO();
        String result = itemServiceImpl.addItem(createItemDTO);
        Assert.assertEquals(Constants.ITEM_FAILURE_EXISTS,result);
    }

    @Test
    public void testAEditItemWithRegularFlow()
    {
        EditItemDTO editItemDTO = editItemDTO();
        ItemDTO itemDTO = itemServiceImpl.editItem(editItemDTO);
        Assert.assertEquals(itemDTO().toString(),itemDTO.toString());
    }

    @Test
    public void testAEditItemWithErrorFlow()
    {
        Mockito.when(itemRepository.findByName(createItemDTO().getName())).thenReturn(null);
        EditItemDTO editItemDTO = editItemDTO();
        ItemDTO itemDTO = itemServiceImpl.editItem(editItemDTO);
        Assert.assertTrue(ObjectUtils.isEmpty(itemDTO));
    }

    @Test
    public void testDeleteItemWithErrorFlow()
    {
        Mockito.when(itemRepository.findByName(createItemDTO().getName())).thenReturn(null);
        DeleteItemDTO deleteItemDTO = deleteItemDTO();
        String result = itemServiceImpl.deleteItem(deleteItemDTO);
        Assert.assertEquals(Constants.ITEM_DELETED_FAILURE,result);
    }

    @Test
    public void testDeleteItemWithRegularFlow()
    {
        DeleteItemDTO deleteItemDTO = deleteItemDTO();
        String result = itemServiceImpl.deleteItem(deleteItemDTO);
        Assert.assertEquals(Constants.ITEM_DELETED_SUCCESS,result);
    }

    @Test
    public void testGetAllItemsWithRegularFlow()
    {
        List<Item> result = itemServiceImpl.getAllItems();
        Assert.assertEquals(Arrays.asList(item()).get(0).toString(),result.get(0).toString());
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
