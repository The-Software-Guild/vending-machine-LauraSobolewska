package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private final Map<String, Item> ITEMS = new HashMap<>();
    public static String INVENTORY_FILE;
    public static final String DELIMITER = "::";
    private enum columns {
        itemID,
        itemName,
        price,
        quantity
    }

    public VendingMachineDaoFileImpl(){
        INVENTORY_FILE = "inventoryFile.txt";
    }

    public VendingMachineDaoFileImpl(String inventoryFile)
    {
        INVENTORY_FILE = "inventoryFile.txt";
    }

    @Override
    public Item addItem(String itemID, Item item)
            throws VendingMachinePersistenceException {
        loadInventory();
        Item newItem = ITEMS.put(item.getItemID(), item);
        writeInventory();
        return newItem;
    }

    @Override
    public List<Item> getItems() throws VendingMachinePersistenceException {
        loadInventory();
        return (List<Item>) ITEMS.values();
    }

    @Override
    public Item getItem(String itemID)
            throws VendingMachinePersistenceException {
        loadInventory();
        return ITEMS.get(itemID);
    }

    @Override
    public void updateItem(Item updatedItem) throws VendingMachinePersistenceException {
        loadInventory();
        ITEMS.put(updatedItem.getItemID(), updatedItem);
        writeInventory();
    }

    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException
        {
            loadInventory();
            ITEMS.put(item.getItemID(), item);
            writeInventory();
            return item;
        }

        private Item unmarshallItem (String itemString){

            String[] itemTokens = itemString.split(DELIMITER);

            String itemID = itemTokens[0];

            Item itemInInventory = new Item(itemID);
            itemInInventory.setItemName(itemTokens[1]);
            itemInInventory.setPrice(new BigDecimal(itemTokens[columns.valueOf("PRICE").ordinal()]));
            itemInInventory.setQuantity(Integer.parseInt(itemTokens[3]));
            return itemInInventory;
        }

    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Error: could not load item data.", e);
        }
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            Item currentItem = unmarshallItem(currentLine);
            ITEMS.put(currentItem.getItemID(), currentItem);
        }
    }
    private String marshallItem(Item aItem){
       return String.format("%s",
               aItem.getItemID(),
               DELIMITER,
               aItem.getItemName(),
               DELIMITER,
               aItem.getQuantity(),
               DELIMITER,
               aItem.getPrice());}


    private void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Error: could not save item data.", e);
        }

        String itemString;
        List<Item> itemList = this.getItems();
        for (Item currentItem : itemList) {
            itemString = marshallItem(currentItem);
            out.println(itemString);
        }
        out.flush();
        out.close();
    }
}
