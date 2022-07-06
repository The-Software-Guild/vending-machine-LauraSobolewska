package com.sg.vendingmachine.dto;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {
    enum Denomination {
        DOLLAR(new BigDecimal("1.00")), QUARTER(new BigDecimal("0.25")), DIME(new BigDecimal("0.10")), NICKEL(new BigDecimal("0.05")), PENNY(new BigDecimal("0.01"));
        private final BigDecimal moneyValue;

        Denomination(BigDecimal moneyValue) {
            this.moneyValue = moneyValue;
        }
    }

    public static String getChange(BigDecimal balance, BigDecimal itemPrice) {
        BigDecimal change = balance.subtract(itemPrice);
        String stringChange = "";
        if (change.compareTo(new BigDecimal("0.00")) == 0) {
            stringChange = "no change";
        } else {
            int[] countMoney = new int[4];

            if (change.compareTo(Denomination.DOLLAR.moneyValue) >= 0) {
                countMoney[Denomination.valueOf("DOLLAR").ordinal()] = change.divide(Denomination.DOLLAR.moneyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.DOLLAR.moneyValue.multiply(new BigDecimal(countMoney[0])));
            }
            if (change.compareTo(Denomination.QUARTER.moneyValue) >= 0) {
                countMoney[Denomination.valueOf("QUARTER").ordinal()] = change.divide(Denomination.QUARTER.moneyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.QUARTER.moneyValue.multiply(new BigDecimal(countMoney[1])));
            }
            if (change.compareTo(Denomination.DIME.moneyValue) >= 0) {
                countMoney[Denomination.valueOf("DIME").ordinal()] = change.divide(Denomination.DIME.moneyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.DIME.moneyValue.multiply(new BigDecimal(countMoney[2])));
            }
            if (change.compareTo(Denomination.NICKEL.moneyValue) >= 0) {
                countMoney[Denomination.valueOf("NICKEL").ordinal()] = change.divide(Denomination.NICKEL.moneyValue, RoundingMode.HALF_UP).intValue();
                change = change.subtract(Denomination.NICKEL.moneyValue.multiply(new BigDecimal(countMoney[3])));
            }
            if (change.compareTo(Denomination.PENNY.moneyValue) >= 0) {
                countMoney[Denomination.valueOf("PENNY").ordinal()] = change.divide(Denomination.PENNY.moneyValue, RoundingMode.HALF_UP).intValue();
            }

            stringChange = "Change:" + countMoney[Denomination.valueOf("DOLLAR").ordinal()] + " " + Denomination.DOLLAR + " " + countMoney[Denomination.valueOf("QUARTER").ordinal()] + " " + Denomination.DIME + " "
                    + countMoney[Denomination.valueOf("QUARTER").ordinal()] + " " + Denomination.QUARTER + " " + countMoney[Denomination.valueOf("PENNY").ordinal()] + " " + Denomination.PENNY;

        }
return stringChange;
    }
}