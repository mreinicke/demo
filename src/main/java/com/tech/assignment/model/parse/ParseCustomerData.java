package com.tech.assignment.model.parse;

import com.tech.assignment.model.*;
import com.tech.assignment.utils.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;

public class ParseCustomerData {

    public static CustomerData parseCustomerDataFromXml(String xml) throws Exception{
        CustomerData retVal = new CustomerData();

        Document xmlDoc = XmlUtil.getDocumentFromXml(xml);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        retVal.setCustomerList(parseCustomer(xPath, xmlDoc));

        return retVal;
    }

    public static OrderData parseOrderDataFromXml(String xml) throws Exception{
        OrderData retVal = new OrderData();

        Document xmlDoc = XmlUtil.getDocumentFromXml(xml);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        retVal.setOrderList(parseOrder(xPath, xmlDoc));

        return retVal;
    }

    private static List<Order> parseOrder(XPath xPath, Document xmlDoc) throws Exception{
        List<Order> retVal = new ArrayList<>();

        NodeList ordersNode = (NodeList)xPath.evaluate("/Root/Orders/Order", xmlDoc, XPathConstants.NODESET);
        for (int i = 0; i < ordersNode.getLength(); i++) {
            Node currentNode = ordersNode.item(i);

            if(currentNode != null) {
                Order order = new Order();

                if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) currentNode;
                    order.setCustomerId(element.getElementsByTagName("CustomerID").item(0).getTextContent());
                    order.setEmployeeId(element.getElementsByTagName("EmployeeID").item(0).getTextContent());
                    order.setOrderDate(element.getElementsByTagName("OrderDate").item(0).getTextContent());
                    order.setRequiredDate(element.getElementsByTagName("RequiredDate").item(0).getTextContent());

                    if(element.getElementsByTagName("ShipInfo").item(0).getAttributes().getNamedItem("ShippedDate") != null) {

                        order.setShipDate(element.getElementsByTagName("ShipInfo").item(0).getAttributes().getNamedItem("ShippedDate").getNodeValue());
                    } else {
                        order.setShipDate("");
                    }
                    order.setShipVia(element.getElementsByTagName("ShipVia").item(0).getTextContent());
                    order.setFreight(element.getElementsByTagName("Freight").item(0).getTextContent());
                    order.setShipName(element.getElementsByTagName("ShipName").item(0).getTextContent());
                    order.setShipAddress(element.getElementsByTagName("ShipAddress").item(0).getTextContent());
                    order.setShipCity(element.getElementsByTagName("ShipCity").item(0).getTextContent());
                    order.setShipRegion(element.getElementsByTagName("ShipRegion").item(0).getTextContent());
                    order.setShipPostalCode(element.getElementsByTagName("ShipPostalCode").item(0).getTextContent());
                    order.setShipCoutry(element.getElementsByTagName("ShipCountry").item(0).getTextContent());

                    retVal.add(order);
                }
            }
        }

        return retVal;
    }

    private static List<Customer> parseCustomer(XPath xPath, Document xmlDoc) throws Exception{
        List<Customer> retVal = new ArrayList<>();

        NodeList customersNode = (NodeList)xPath.evaluate("/Root/Customers/Customer", xmlDoc, XPathConstants.NODESET);
        for (int i = 0; i < customersNode.getLength(); i++) {
            Node currentNode = customersNode.item(i);

            if(currentNode != null){
                Customer customer = new Customer();

                if(currentNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element)currentNode;
                    customer.setCustomerId(element.getElementsByTagName("CustomerID").item(0).getTextContent());
                    customer.setCompanyName(element.getElementsByTagName("CompanyName").item(0).getTextContent());
                    customer.setContactName(element.getElementsByTagName("ContactName").item(0).getTextContent());
                    customer.setfName(element.getElementsByTagName("Fname").item(0).getTextContent());
                    customer.setlName(element.getElementsByTagName("Lname").item(0).getTextContent());
                    customer.setAccountNo(element.getElementsByTagName("AccountNo").item(0).getTextContent());
                    customer.setContactTitle(element.getElementsByTagName("ContactTitle").item(0).getTextContent());
                    customer.setPhone(element.getElementsByTagName("Phone").item(0).getTextContent());
                    customer.setAddress(element.getElementsByTagName("Address").item(0).getTextContent());
                    customer.setCity(element.getElementsByTagName("City").item(0).getTextContent());
                    customer.setRegion(element.getElementsByTagName("Region").item(0).getTextContent());
                    customer.setPostalCode(element.getElementsByTagName("PostalCode").item(0).getTextContent());
                    customer.setCountry(element.getElementsByTagName("Country").item(0).getTextContent());

                    retVal.add(customer);
                }
            }
        }

        return retVal;
    }
}
