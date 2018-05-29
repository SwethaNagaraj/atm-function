package com.suncorp.org.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.suncorp.org.CashDispenseService;
import com.suncorp.org.DenominationCount;

public class CashDispenseServiceTest {

    CashDispenseService service = new CashDispenseService();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void withdrawValidCashFromDispenser() throws Exception {
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("20", 5);
        
        DenominationCount denominationCount = new DenominationCount();
        denominationCount.setTwentyDollar(20);
        denominationCount.setFiftyDollar(10);
        
        Map<String, Integer> output = service.withdrawCash(100, denominationCount);
        assertEquals(result, output);
    }
    
    @Test(expected = Exception.class)
    public void withdrawInvalidCashFromDispenser() throws Exception {
        
        DenominationCount denominationCount = new DenominationCount();
        denominationCount.setTwentyDollar(20);
        denominationCount.setFiftyDollar(10);
        when(service.withdrawCash(30, denominationCount)).thenThrow(new Exception());
        service.withdrawCash(30, denominationCount);
    }
    
    @Test(expected = Exception.class)
    public void insufficientCashFromDispenser() throws Exception {
        DenominationCount denominationCount = new DenominationCount();
        denominationCount.setTwentyDollar(0);
        denominationCount.setFiftyDollar(0);
        when(service.withdrawCash(30, denominationCount)).thenThrow(new Exception());
        service.withdrawCash(30, denominationCount);
    }

}
