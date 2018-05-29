package com.suncorp.org;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*
 * Service with implementation to fetch the available denomination count
 */
public class CountDenominationService {
    private static final Logger logger = Logger.getLogger("CashDispenseResource");

    public Map<String, Integer> getCountOfDenomination(DenominationCount denominationCount) {
        logger.entering("Entering Class : CountDenominationService", "Method : getCountOfDenomination");

        Map<String, Integer> countList = new HashMap<String, Integer>();
        NotifyService notify = new NotifyService();

        countList.put("20", denominationCount.getTwentyDollar());
        countList.put("50", denominationCount.getFiftyDollar());

        if (denominationCount.getFiftyDollar() < 10)
            // send notification if threshold is reached
            notify.sendNotification();

        logger.exiting("Exiting Class : CountDenominationService", "Method : getCountOfDenomination");
        return countList;
    }
}
