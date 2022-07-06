package com.sg.vendingmachine.service;

public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String message)
    {
        super(message);
    }

    public ItemNotAvailableException(String message, Throwable cause)
    {
        super(message,cause);
    }
}
