package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {

    Item addItem(String itemID, Item item)
            throws VendingMachinePersistenceException;

    List<Item> getItems()
            throws VendingMachinePersistenceException;

    Item getItem(String itemID)
            throws VendingMachinePersistenceException;

    void updateItem(Item updatedItem) throws VendingMachinePersistenceException, VendingMachinePersistenceException;

    Item addItem(Item item) throws VendingMachinePersistenceException;

}