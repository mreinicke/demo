package com.tech.assignment.controller;

import com.tech.assignment.utils.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {
    private static final Logger LOG = LogManager.getLogger(HomeController.class);

    @RequestMapping("/xmlresource")
    public Map<String, Object> getXmlResource() {
        LOG.info("Call to getXmlResource.");
        String xmlString = "";

        try {
            xmlString = FileUtil.readFileToString(HomeController.class, "/customerData.xml");
        } catch (Exception e){
            LOG.error(e);
        }

        LOG.debug("xmlString value: " + xmlString);

        Map<String,Object> model = new HashMap<String,Object>();
        model.put("xmlString", xmlString);


        LOG.info("Returning getXmlResource.");
        return model;
    }

}
