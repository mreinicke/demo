package com.tech.assignment.model.parse;

import com.tech.assignment.model.CustomerData;
import com.tech.assignment.utils.FileUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by a543086 on 10/05/2015.
 */
public class ParseCustomerDataTest {
    private String xml;
    @Before
    public void setup() throws Exception{
        this.xml = FileUtil.readFileToString(ParseCustomerDataTest.class, "/customerData.xml");
    }
    @Test
    public void test_parseCustomerDataFromXml() throws Exception{
        CustomerData customerData = ParseCustomerData.parseCustomerDataFromXml(xml);
    }
}
