package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Change;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private final VendingMachineDao ITEM_DAO;
    private final VendingMachineAuditDao AUDIT_DAO;
    private BigDecimal userBalance;

    public VendingMachineServiceLayerImpl(VendingMachineDao ITEM_DAO, VendingMachineAuditDao AUDIT_DAO) {
        this.ITEM_DAO = ITEM_DAO;
        this.AUDIT_DAO = AUDIT_DAO;
        userBalance = new BigDecimal ("0.00").setScale(2, RoundingMode.HALF_UP);
    }

    public String buyItem(String itemID) throws VendingMachinePersistenceException, ItemNotAvailableException, VendingMachineNSFException {
        Item item = ITEM_DAO.getItem(itemID);
        String change = null;
        if (item == null) {
            throw new ItemNotAvailableException("Error: item not found");
        }
        if (item.getQuantity() == 0) {
            throw new ItemNotAvailableException("Error: item not available");
        } else {

            updateItemQuantity(item);
            if (item.getQuantity() < 3) {
                AUDIT_DAO.writeAuditEntry("Low stock");
            }
            return change;
        }
    }

    @Override
    public BigDecimal addToBalance(BigDecimal amount) throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public BigDecimal getBalance() {
        return null;
    }

    @Override
    public void updateItemQuantity(Item item) throws VendingMachinePersistenceException {

    }

    /*@Override
    public String buyItemFromVendingMachine(String itemId) throws VendingMachinePersistenceException, ItemNotAvailableException, VendingMachineNSFException {
        return null;
    }*/

    /*@Override
        public void updateItemQuantity (Item item) throws VendingMachinePersistenceException {
            item.setQuantity(item.getQuantity() - 1);
            ITEM_DAO.updateItem(item);
        }*/

    @Override
    public List<Item> getItems() throws VendingMachinePersistenceException {
        return null;
    }

    /*@Override
        public List<Item> getAllItems () throws VendingMachinePersistenceException {
            return ITEM_DAO.getItems();
        }*/

        /*@Override
        public BigDecimal addToUserBalance(BigDecimal addMoney) throws VendingMachinePersistenceException {
            userBalance = userBalance.add(addMoney);
            AUDIT_DAO.writeAuditEntry("Money added to your balance:" + addMoney);
            return userBalance;
        }*/

        /*@Override
        public BigDecimal getUserBalance()
        {
            return userBalance;
        }*/
    }
