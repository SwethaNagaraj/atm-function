package com.suncorp.org;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Resource to fetch the available denomination count
 */

public class DisplayDenominationCountResource {
    private static final Logger logger = Logger.getLogger("DisplayDenominationCountResource");

    public Map<String, Integer> displayDenomination(DenominationCount denominationCount) {
        logger.entering("Entering Class : DisplayDenominationCountResource", "Method : displayDenomination");
        
        if (denominationCount == null) {
            logger.log(Level.SEVERE, "Please enter valid input of Denomination");
            throw new IllegalArgumentException("Please enter valid input of Denomination");
        }
        
        Map<String, Integer> countList = new HashMap<String, Integer>();
        CountDenominationService service = getService();

        try {
            countList = service.getCountOfDenomination(denominationCount);

        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Got exception" + e.getMessage());
        }
        logger.exiting("Exiting Class : DisplayDenominationCountResource", "Method : displayDenomination");
        return countList;
    }

    private CountDenominationService getService() {
        return new CountDenominationService();
    }
}
