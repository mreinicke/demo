package com.tech.assignment.controller;

import com.tech.assignment.model.Customer;
import com.tech.assignment.model.CustomerData;
import com.tech.assignment.model.Order;
import com.tech.assignment.model.OrderData;
import com.tech.assignment.model.parse.ParseCustomerData;
import com.tech.assignment.utils.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger LOG = LogManager.getLogger(CustomerController.class);

    @RequestMapping(value = "/orderdata/{id}", method = RequestMethod.GET)
    public @ResponseBody
    OrderData getCustomerData(@PathVariable String id) {

        LOG.info("Call to getCustomerData.");

        LOG.debug("Looking for customer with id: " + id);
        OrderData orderData = null;
        String xmlString = "";
        try {
            xmlString = FileUtil.readFileToString(CustomerController.class, "/customerData.xml");
            orderData = ParseCustomerData.parseOrderDataFromXml(xmlString);
        } catch (Exception e) {
            LOG.error(e);
        }

        LOG.debug("Making customer list filtered by id: " + id);
        List<Order> orderIdList = orderData.getOrderList().stream().filter(ord -> ord.getCustomerId().equals(id)).collect(Collectors.toList());
        orderIdList.stream().forEach(c -> LOG.debug("Adding customer to our return value: " + c));

        orderData.setOrderList(orderIdList);

        LOG.info("Returning getCustomerData.");
        return orderData;
    }
}
