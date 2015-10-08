package com.tech.assignment.controller;

import com.tech.assignment.model.CustomerData;
import com.tech.assignment.model.OrderData;
import com.tech.assignment.model.parse.ParseCustomerData;
import com.tech.assignment.util.AbstractIntegrationTest;
import static org.junit.Assert.*;

import com.tech.assignment.utils.XmlUtil;
import org.junit.Test;

import java.util.Map;

public class HomeControllerIT_Test extends AbstractIntegrationTest {

    @Test
    public void test_getXmlResource(){
        Map<String,Object> retVal =(Map<String,Object>)this.getEntity("/home/xmlresource", Map.class, new Object());

        assertNotNull(retVal.get("xmlString"));
    }

    @Test
    public void test_getCustomerParse(){
        Map<String,Object> retVal =(Map<String,Object>)this.getEntity("/home/xmlresource", Map.class, new Object());

        assertNotNull(retVal.get("xmlString"));

        CustomerData cd = null;
        try {
            cd = ParseCustomerData.parseCustomerDataFromXml(String.valueOf(retVal.get("xmlString")));
            assertNotNull(cd);
            assertTrue(cd.getCustomerList().size() > 0);
        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void test_getOrderParse(){
        Map<String,Object> retVal =(Map<String,Object>)this.getEntity("/home/xmlresource", Map.class, new Object());

        assertNotNull(retVal.get("xmlString"));

        OrderData od = null;
        try {
            od = ParseCustomerData.parseOrderDataFromXml(String.valueOf(retVal.get("xmlString")));
            assertNotNull(od);
            assertTrue(od.getOrderList().size() > 0);
        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void test_getOrderParseBadXml(){
        Map<String,Object> retVal =(Map<String,Object>)this.getEntity("/home/xmlresource", Map.class, new Object());

        assertNotNull(retVal.get("xmlString"));

        OrderData od = null;
        try {
            od = ParseCustomerData.parseOrderDataFromXml(String.valueOf("<bad>"));
            fail();
        } catch (Exception e){
            assertNotNull(e);
        }
    }

    @Test
    public void test_getOrderParseValidBadXml(){
        Map<String,Object> retVal =(Map<String,Object>)this.getEntity("/home/xmlresource", Map.class, new Object());

        assertNotNull(retVal.get("xmlString"));

        OrderData od = null;
        try {
            od = ParseCustomerData.parseOrderDataFromXml(String.valueOf("<bad></bad>"));
            assertNotNull(od);
            assertFalse(od.getOrderList().size() > 0);
        } catch (Exception e){
            fail();
        }
    }
}
