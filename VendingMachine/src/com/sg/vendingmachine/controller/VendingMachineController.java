package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.ItemNotAvailableException;
import com.sg.vendingmachine.service.VendingMachineNSFException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;

public class VendingMachineController {

    private final VendingMachineView view;
    private final VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int userChoice;

        try {
            displayItems();
            while (keepGoing) {
                userChoice = getUserChoice();
                switch (userChoice) {
                    case 1 -> insertMoney();
                    case 2 -> displayItems();
                    case 3 -> buyItem();
                    case 4 -> viewBalance();
                    case 5 -> keepGoing = false;
                    default -> unknownCommand();
                }
            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }






    private void exitMessage() {
        view.displayExitBanner(service.getBalance());
    }


    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }


    private void insertMoney() throws VendingMachinePersistenceException {
        String money = view.getUserMoney();
        try {
            BigDecimal balance = service.addToBalance(new BigDecimal(money));
        } catch (NumberFormatException e) {
            view.displayErrorMessage("Please try again and enter a valid item number.");
        }
    }

    private void buyItem() //throws VendingMachinePersistenceException, VendingMachineNSFException, ItemNotAvailableException
    {
        try {
            String itemChoice = view.getItemChoice();
            if (itemChoice == null) {
                view.displayErrorMessage("Please enter a valid item number.");
            } else if (itemChoice.equals("0")) {
                this.run();
            } else {
                String response = service.buyItem(itemChoice);
                view.purchase(response);
            }

        } catch (VendingMachineNSFException | VendingMachinePersistenceException | ItemNotAvailableException e) {
            view.displayErrorMessage(e.getMessage());
        }}

    private void viewBalance() {
        BigDecimal balance = service.getBalance();
        view.printBalance(balance);
    }
    private void displayItems() throws VendingMachinePersistenceException {
        view.displayItems(service.getItems());
    }

    private int getUserChoice()
    {
        return view.getUserSelection();
    }

    }


