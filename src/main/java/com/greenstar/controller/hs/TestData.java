package com.greenstar.controller.hs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Syed Muhammad Hassan
 * 20th August, 2020
 */

@Controller
public class TestData {

    @RequestMapping(value = "/TestData", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        System.out.println("Hello from println");
        return "anyways";
    }
}
