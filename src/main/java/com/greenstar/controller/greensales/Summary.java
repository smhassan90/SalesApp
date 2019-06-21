package com.greenstar.controller.greensales;

import com.greenstar.dao.GSSDashboardDAO;
import com.greenstar.dao.SyncDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Syed Muhammad Hassan
 * $Date
 */
@Controller
public class Summary {

    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        GSSDashboardDAO dashboardDAO = new GSSDashboardDAO();
        return dashboardDAO.getDashboardHTML("FSD-01");
    }
}
