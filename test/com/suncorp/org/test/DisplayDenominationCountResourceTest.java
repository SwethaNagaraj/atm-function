package com.suncorp.org.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.suncorp.org.CountDenominationService;
import com.suncorp.org.DenominationCount;

import static org.mockito.Mockito.when;

public class DisplayDenominationCountResourceTest {
    @Mock
    CountDenominationService service;
    @Mock
    DenominationCount denominationCount = new DenominationCount();
    @Mock
    Map<String, Integer> result;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void displayDenomination() throws Exception {
        result = new HashMap<String, Integer>();
        result.put("20", 5);

        denominationCount.setTwentyDollar(10);
        denominationCount.setFiftyDollar(5);
        when(service.getCountOfDenomination(denominationCount)).thenReturn(result);
        assertEquals(result, service.getCountOfDenomination(denominationCount));
    }

    @Test(expected = IllegalArgumentException.class)
    public void raiseExceptionIncaseFailure() throws Exception {
        when(service.getCountOfDenomination(denominationCount)).thenThrow(new IllegalArgumentException());
        service.getCountOfDenomination(denominationCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDenominatioCountInvalidForDispenser() throws Exception {
        when(service.getCountOfDenomination(null)).thenThrow(new IllegalArgumentException());
        service.getCountOfDenomination(null);
    }
}
