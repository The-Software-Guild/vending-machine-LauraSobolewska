package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    BigDecimal addToBalance(BigDecimal amount) throws VendingMachinePersistenceException;

    BigDecimal getBalance();

    void updateItemQuantity(Item item) throws VendingMachinePersistenceException;

    List<Item> getItems() throws VendingMachinePersistenceException;

    String buyItem(String itemId) throws VendingMachinePersistenceException, ItemNotAvailableException, VendingMachineNSFException;
}