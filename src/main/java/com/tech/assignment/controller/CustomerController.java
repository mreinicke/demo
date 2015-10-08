package com.tech.assignment.controller;

import com.tech.assignment.model.Customer;
import com.tech.assignment.model.CustomerData;
import com.tech.assignment.model.parse.ParseCustomerData;
import com.tech.assignment.utils.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private static final Logger LOG = LogManager.getLogger(CustomerController.class);

    @RequestMapping(value = "/customerdata", method = RequestMethod.POST, consumes = {"application/xml", "text/xml"})
    public @ResponseBody CustomerData postCustomerData(@RequestBody String request) {
        LOG.info("Call to getCustomerData.");

        LOG.debug("parsing xml value: " + request);
        CustomerData customerData = null;

        try {
            customerData = ParseCustomerData.parseCustomerDataFromXml(request);
        } catch (Exception e) {
            LOG.error(e);
        }

        LOG.info("Returning getCustomerData.");
        return customerData;
    }

    @RequestMapping(value = "/customerdata/{id}", method = RequestMethod.GET)
    public @ResponseBody CustomerData getCustomerData(@PathVariable String id) {
        LOG.info("Call to getCustomerData.");

        LOG.debug("Looking for customer with id: " + id);
        CustomerData customerData = null;
        String xmlString = "";
        try {
            xmlString = FileUtil.readFileToString(CustomerController.class, "/customerData.xml");
            customerData = ParseCustomerData.parseCustomerDataFromXml(xmlString);
        } catch (Exception e) {
            LOG.error(e);
        }

        LOG.debug("Making customer list filtered by id: " + id);
        List<Customer> customerIdList = customerData.getCustomerList().stream().filter(cust -> cust.getCustomerId().equals(id)).collect(Collectors.toList());
        customerIdList.stream().forEach(c -> LOG.debug("Adding customer to our return value: " + c));

        customerData.setCustomerList(customerIdList);

        LOG.info("Returning getCustomerData.");
        return customerData;
    }
}
