package com.suncorp.org;

/*
 * POJO containing count of available Denominations
 */

public class DenominationCount {
    private int twentyDollar;

    private int fiftyDollar;

    public DenominationCount() {
        twentyDollar = 2;
        fiftyDollar = 10;
    }

    public DenominationCount(int twentyCount, int fiftyCount) {
        twentyDollar = twentyCount;
        fiftyDollar = fiftyCount;
    }

    public int getTwentyDollar() {
        return twentyDollar;
    }

    public void setTwentyDollar(int twentyDollar) {
        this.twentyDollar = twentyDollar;
    }

    public int getFiftyDollar() {
        return fiftyDollar;
    }

    public void setFiftyDollar(int fiftyDollar) {
        this.fiftyDollar = fiftyDollar;
    }
}
