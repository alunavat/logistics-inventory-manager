package com.shopify.logistics.logisticsinventorymanager.repository;

import com.shopify.logistics.logisticsinventorymanager.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
