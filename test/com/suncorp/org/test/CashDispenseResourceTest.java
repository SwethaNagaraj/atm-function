package com.suncorp.org.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import com.suncorp.org.CashDispenseService;
import com.suncorp.org.DenominationCount;

public class CashDispenseResourceTest {
    @Mock
    CashDispenseService service;
    @Mock
    DenominationCount denominationCount = new DenominationCount();
    @Mock
    Map<String, Integer> result;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void withdrawValidCashFromDispenser() throws Exception {
        result = new HashMap<String, Integer>();
        result.put("20", 5);

        denominationCount.setTwentyDollar(10);
        denominationCount.setFiftyDollar(5);
        when(service.withdrawCash(100, denominationCount)).thenReturn(result);
        assertEquals(result, service.withdrawCash(100, denominationCount));
    }

    @Test(expected = IllegalArgumentException.class)
    public void raiseExceptionIncaseFailure() throws Exception {
        when(service.withdrawCash(100, denominationCount)).thenThrow(new IllegalArgumentException());
        service.withdrawCash(100, denominationCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawNullMoneyFromDispenser() throws Exception {
        when(service.withdrawCash(0, denominationCount)).thenThrow(new IllegalArgumentException());
        service.withdrawCash(0, denominationCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDenominatioCountInvalidForDispenser() throws Exception {
        when(service.withdrawCash(100, null)).thenThrow(new IllegalArgumentException());
        service.withdrawCash(100, null);
    }
}
