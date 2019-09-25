package com.greenstar.controller.hs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Syed Muhammad Hassan
 * 08/16/2019
 */

@Controller
public class DTCMinutesUploader {

    @RequestMapping(value = "/uploader", method = RequestMethod.GET,params={"file"})
    @ResponseBody
    public String index(String file){
        return "";
    }
}
