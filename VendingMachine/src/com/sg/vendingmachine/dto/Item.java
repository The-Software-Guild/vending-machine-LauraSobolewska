package com.sg.vendingmachine.dto;
import java.math.BigDecimal;

public class Item {
    private final String itemID;
    private String itemName;
    private BigDecimal price;
    private int quantity;

    public Item(String itemID) {
        this.itemID = itemID;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }



    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }


    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice (BigDecimal price)
    {
        this.price = price;
    }
}
