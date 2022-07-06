package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {

    public UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int getUserSelection() {
        io.print("Main Menu");
        io.print("1. Show items");
        io.print("3. Buy item");
        io.print("4. View balance");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public String getUserMoney()
    {
        return io.readString("Please enter the amount of money you will insert.");
    }

    public void printBalance(BigDecimal balance)
    {
        io.print("Your balance is $" + balance);
    }

    public void displayItems(List<Item> itemList) {
        for (Item currentItem : itemList) {
            String itemDetails = String.format("\n[%s]Name-%s - Price:$%s", currentItem.getItemID(), currentItem.getQuantity(), currentItem.getItemName(), currentItem.getPrice() );
            io.print(itemDetails);
        }
        System.out.println("placeholder");
    }

    public String getItemChoice() {
        return io.readString("Please enter the number for the item you wish to purchase.");
    }

    public void displayExitBanner(BigDecimal balance)
    {
        if (balance.compareTo(BigDecimal.ZERO) == 0) {
            io.print("Thank you for purchasing an item from the vending machine.");
        } else {
            io.print("Thank you for purchasing an item from the vending machine. Your change is: £" + balance);
        }

    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String message) {
        io.print("ERROR");
        io.print(message);
    }
    public void purchase(String output)
    {
        io.print(output);
    }

}
