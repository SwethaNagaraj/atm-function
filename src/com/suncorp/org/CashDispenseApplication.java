package com.suncorp.org;

import java.util.Map;

public class CashDispenseApplication {

    public static void main(String[] args) {
        DenominationCount denominationCount = new DenominationCount(5, 5);
        DisplayDenominationCountResource countResource = new DisplayDenominationCountResource();

        System.out.print("Displaying Available Denomination Count : ");
        System.out.println(countResource.displayDenomination(denominationCount));

        CashDispenseResource resource = new CashDispenseResource();

        try {
            Map<String, Integer> result = resource.withdrawCash(100, denominationCount);
            System.out.println("Cash Dispensing : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
