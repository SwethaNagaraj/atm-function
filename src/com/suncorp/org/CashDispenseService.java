package com.suncorp.org;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/*
 *Service containing implementation to withdraw requested cash from dispenser
 */

public class CashDispenseService {
    private static final Logger logger = Logger.getLogger("CashDispenseService");

    public Map<String, Integer> withdrawCash(int money, DenominationCount denominationCount) throws Exception {
        logger.entering("Entering Class : CashDispenseService", "Method : withdrawCash");

        Map<String, Integer> supplyValue = new HashMap<String, Integer>();
        CountDenominationService denominationService = new CountDenominationService();
        Map<String, Integer> denominations = denominationService.getCountOfDenomination(denominationCount);

        int twentyCount = Integer.parseInt(denominations.get("20").toString());
        int fiftyCount = Integer.parseInt(denominations.get("50").toString());

        int twentyNeeded = money / 20;
        int fiftyNeeded = money / 50;

        if (twentyCount > twentyNeeded) {
            int newTwentyCount = twentyCount - twentyNeeded;
            int newMoney = money - (twentyNeeded * 20);
            if (newMoney > 0) {
                if (fiftyCount >= fiftyNeeded) {
                    int newFiftyCount = fiftyCount - fiftyNeeded;
                    int remainingMoney = money - (fiftyNeeded * 50);

                    if (remainingMoney > 0) {
                        twentyNeeded = remainingMoney / 20;
                        int moneyLeftOver = remainingMoney - (twentyNeeded * 20);
                        if (moneyLeftOver > 0) {
                            throw new Exception("Please enter multiples in available denominations");
                        }
                        money = remainingMoney;
                        denominationCount.setFiftyDollar(newFiftyCount);
                        supplyValue.put("50", fiftyNeeded);
                        denominationCount.setTwentyDollar(twentyCount - twentyNeeded);
                        supplyValue.put("20", twentyNeeded);
                        return supplyValue;
                    }
                }
            }
            denominationCount.setTwentyDollar(newTwentyCount);
            money = newMoney;
            supplyValue.put("20", twentyNeeded);
        } else if (twentyCount <= twentyNeeded) {
            if (fiftyCount >= fiftyNeeded) {
                int newFiftyCount = fiftyCount - fiftyNeeded;
                int remainingMoney = money - (fiftyNeeded * 50);

                if (remainingMoney > 0) {
                    twentyNeeded = remainingMoney / 20;
                    int moneyLeftOver = remainingMoney - (twentyNeeded * 20);

                    if (moneyLeftOver > 0) {
                        throw new Exception("Please enter multiples in available denominations");
                    }
                    money = remainingMoney;
                    denominationCount.setFiftyDollar(newFiftyCount);
                    supplyValue.put("50", fiftyNeeded);
                    denominationCount.setTwentyDollar(twentyCount - twentyNeeded);
                    supplyValue.put("20", twentyNeeded);
                    return supplyValue;
                } else {
                    money = remainingMoney;
                    denominationCount.setFiftyDollar(newFiftyCount);
                    supplyValue.put("50", fiftyNeeded);
                }
            } else {
                throw new Exception("Insuffiencient Funds in the dispenser");
            }
        } else {
            throw new Exception("Insuffiencient Funds in the dispenser");
        }
        logger.exiting("Exiting Class : CashDispenseService", "Method : withdrawCash");
        return supplyValue;
    }
}
