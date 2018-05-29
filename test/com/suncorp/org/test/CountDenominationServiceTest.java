package com.suncorp.org.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.suncorp.org.CountDenominationService;
import com.suncorp.org.DenominationCount;
import com.suncorp.org.NotifyService;

public class CountDenominationServiceTest {
    @Mock
    CountDenominationService service;
    @Mock
    DenominationCount denominationCount = new DenominationCount();
    @Mock
    NotifyService notifyService = new NotifyService();
    @Mock
    Map<String, Integer> output;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void countDenominationsFromDispenser() throws Exception {
        output = new HashMap<String, Integer>();
        output.put("20", 5);

        denominationCount.setTwentyDollar(10);
        denominationCount.setFiftyDollar(5);
        when(service.getCountOfDenomination(denominationCount)).thenReturn(output);
        assertEquals(output, service.getCountOfDenomination(denominationCount));
    }

    @Test
    public void sendNotificationIncaseThreshold() throws Exception {
        output.clear();
        denominationCount.setTwentyDollar(0);
        denominationCount.setFiftyDollar(1);
        
        stubVoid(notifyService).toReturn().on().sendNotification();
        notifyService.sendNotification();

        when(service.getCountOfDenomination(denominationCount)).thenReturn(output);
        assertEquals(output, service.getCountOfDenomination(denominationCount));
    }
}
