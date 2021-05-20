package com.greenstar.abstracts;

import com.greenstar.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author Syed Muhammad Hassan
 * 15th March, 2021
 */
@Controller
public class ProviderVerification {

    @RequestMapping(value = "/activeinactive", method = RequestMethod.GET)
    @ResponseBody
    public String getActiveInActiveStatus(){
        String html = "<html>";
        String query = "SELECT DECODE(STATUS,1,'Active',0,'In-active') AS STATUS, COUNT(*) AS Count FROM HS_PROVIDERS \n" +
                "WHERE CODE IN (SELECT PROVIDER_CODE FROM HS_MAPPING_TEMP)GROUP BY STATUS";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("STATUS");
        headers.add("COUNT");
        html += HibernateUtil.getHTMLForQuery(query,headers, "Aewain");
        html+="</html>";
        return html;
    }
}
