package com.suncorp.org;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.suncorp.org.CashDispenseService;

public class CashDispenseResource {

    private static final Logger logger = Logger.getLogger("CashDispenseResource");

    public Map<String, Integer> withdrawCash(int money, DenominationCount denominationCount) throws Exception {
        logger.entering("Entering Class : CashDispenseResource", "Method : withdrawCash");

        if (money == 0 || denominationCount == null) {
            logger.log(Level.SEVERE, "Please enter valid input to withdraw");
            throw new IllegalArgumentException("Please enter valid input to withdraw");
        }
        
        Map<String, Integer> countList = new HashMap<String, Integer>();
        CashDispenseService service = getService();

        try {
            countList = service.withdrawCash(money, denominationCount);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Got exception" + e.getMessage());
        }
        logger.exiting("Exiting Class : CashDispenseResource", "Method : withdrawCash");
        return countList;
    }

    private CashDispenseService getService() {
        return new CashDispenseService();
    }
}
