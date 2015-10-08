package com.tech.assignment.controller;

import com.tech.assignment.model.OrderData;
import com.tech.assignment.util.AbstractIntegrationTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderControllerIT_Test extends AbstractIntegrationTest {
    private static String CUST_ID = "GREAL";

    @Test
    public void test_getCustomerData(){
        OrderData retVal = this.getEntity("/orders/orderdata/{id}", OrderData.class, CUST_ID);
        assertNotNull(retVal.getOrderList());
    }

    @Test
    public void test_getCustomerDataSize(){
        OrderData retVal = this.getEntity("/orders/orderdata/{id}", OrderData.class, CUST_ID);
        assertEquals(11, retVal.getOrderList().size());
    }

    @Test
    public void test_getCustomerDataSizeBadId(){
        OrderData retVal = this.getEntity("/orders/orderdata/{id}", OrderData.class, "Bad");
        assertEquals(0, retVal.getOrderList().size());
    }
}
