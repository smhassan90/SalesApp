package com.greenstar.controller;

import com.greenstar.utils.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Syed Muhammad Hassan
 * $Date
 */
@Controller
public class TestIDManager {

    public static Logger LOG = LogManager.getLogger(TestIDManager.class);

        @RequestMapping(value = "/testid", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        //long id = HibernateUtil.getNextBaseID(4);
        return "Done " ;
    }
}
