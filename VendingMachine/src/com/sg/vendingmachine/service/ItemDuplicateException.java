package com.sg.vendingmachine.service;

public class ItemDuplicateException extends Exception {
    public ItemDuplicateException(String message)
    {
        super(message);
    }

    public ItemDuplicateException(String message, Throwable cause)
    {
        super(message, cause);
    }
}