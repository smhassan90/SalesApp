package com.greenstar.controller.hs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.HSSyncDAO;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.entity.qtv.QTVForm;
import com.greenstar.utils.HibernateUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */

@Controller
public class HSSync {
    Gson gson = new GsonBuilder().setDateFormat("MM/dd/yy").create();
    List<Integer> succesfulIDs = new ArrayList<Integer>();
    List<Integer> rejectedIDs = new ArrayList<Integer>();
    final int closingDay = 4;
    @RequestMapping(value = "/hssync", method = RequestMethod.GET,params={"data","token"})
    @ResponseBody
    public String index(String data, String token){
        GSSStaffDAO gssStaffDAO = new GSSStaffDAO();
        JSONObject response = new JSONObject();
        String staffCode = gssStaffDAO.isTokenValid(token);
        if(!"".equals(staffCode)){
            return performSync(staffCode,data).toString();
        }else{
            response.put("message", "Invalid Token, you might be logged in from another device");
            response.put("status", Codes.INVALID_TOKEN);
            response.put("data","");
            return response.toString();
        }

    }

    private boolean formIsValid(QTVForm form){
        boolean isValid = false;
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        Calendar cal = Calendar.getInstance();
        cal.setTime(form.getVisitDate());
        int visitDateMonth = cal.get(Calendar.MONTH)+1;
        if(month==visitDateMonth || (month==visitDateMonth+1 && day<=closingDay)){
            int count = HibernateUtil.getRecordCount("select count(*) from QTVForm WHERE providerCode='"+form.getProviderCode()+"' AND approvalStatus IN (1,0) AND MONTH(visitDate)="+visitDateMonth);
            if(count==0){
                isValid = true;
            }
        }

        return isValid;

    }

    public JSONObject   performSync(String code, String data){
        String statusCode = Codes.ALL_OK;
        String message = "Successfully synced";
        JSONObject response = new JSONObject();
        HSData dataSync = new HSData();
        boolean isSuccesful = false;
        //data = data.substring(0, data.length() - 1);
       SyncObjectHS syncObjectHS = null;

       if(!"".equals(data)){
           syncObjectHS = gson.fromJson(data, SyncObjectHS.class);

           List<QTVForm> forms = syncObjectHS.getQtvForms();
           boolean isValidForm =  false;
           for(QTVForm form : forms){
               isValidForm = formIsValid(form);
               if(isValidForm){
                   isSuccesful = HibernateUtil.saveOrUpdate(form);
                   if(isSuccesful){
                       succesfulIDs.add(form.getId());
                       isSuccesful = false;
                   }else{
                       statusCode = Codes.SOMETHING_WENT_WRONG;
                       message = "Something went wrong";
                   }
               }else{
                   rejectedIDs.add(form.getId());
               }

           }
       }

        List<Providers> providers = null;

        HSSyncDAO sync = new HSSyncDAO();
        String AMName = "";
        String region = "";
        String staffName = "";
        String AMCode = "";
        List<ApprovalQTVForm> qtvForms = new ArrayList<ApprovalQTVForm>();
        int countTotalProviders = 0;
        int countApprovedQTV = 0;
        int countRejectedQTV = 0;
        int countPendingQTV = 0;

        try {

            providers = sync.getTaggedProviders(code);
            AMName = sync.getAMName(code);
            AMCode = sync.getAMCode(code);
            region = sync.getRegion(code);
            staffName = sync.getStaffName(code);
            qtvForms = sync.getApprovedQTVForms(code);

            for(ApprovalQTVForm form : qtvForms){
                int statusForm = form.getApprovalStatus();
                switch (statusForm){
                    case 0 :
                        countPendingQTV++;
                        break;
                    case 1 :
                        countApprovedQTV++;
                        break;
                    case 2 :
                        countRejectedQTV++;
                }
            }
            HashMap<String,Integer> dashboardParams = new HashMap<String, Integer>();
            dashboardParams.put("countPendingQTV",countPendingQTV);
            dashboardParams.put("countApprovedQTV",countApprovedQTV);
            dashboardParams.put("countRejectedQTV",countRejectedQTV);
            dashboardParams.put("countTotalProviders",providers.size());

            Dashboard dashboard = new Dashboard();
            dashboard = createDashboard(dashboardParams);

            dataSync.setAMName(AMName);
            dataSync.setAMCode(AMCode);
            dataSync.setProviders(providers);
            dataSync.setRegion(region);
            dataSync.setName(staffName);
            dataSync.setQtvForms(qtvForms);
            dataSync.setDashboard(dashboard);
            response.put("message", message);
            response.put("status", statusCode);
            response.put("staffName",staffName);
            response.put("data", gson.toJson(dataSync));
            response.put("successfulIDs",succesfulIDs);
            response.put("rejectedIDs",rejectedIDs);

        }catch(Exception e){
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }

    private Dashboard createDashboard(HashMap<String, Integer> dashboardParams) {

        int countApprovedQTV = dashboardParams.get("countApprovedQTV");
        int countRejectedQTV = dashboardParams.get("countRejectedQTV");
        int countPendingQTV = dashboardParams.get("countPendingQTV");
        int countTotalProviders = dashboardParams.get("countTotalProviders");

        String html = "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "#customers {\n" +
                "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "#customers td, #customers th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "#customers tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "#customers th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #4CAF50;\n" +
                "  color: white;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<table id=\"customers\">\n" +
                "  <tr>\n" +
                "    <th> </th>\n" +
                "    <th>Total</th>\n" +
                "    <th>Percentage</th>\n" +
                "  </tr>";
            html += "<tr>\n" +
                    "    <td>Total Providers</td>\n" +
                    "    <td>"+countTotalProviders+"</td>\n" +
                    "\t<td></td>\n" +
                    "  </tr>";
            int multiply = countPendingQTV*100;
        html+="<tr>\n" +
                "    <td><b>Pending QTV</b></td>\n" +
                "    <td><b>"+countPendingQTV+"</b></td>\n" +
                "    <td><b>"+(float)multiply/countTotalProviders+"%</b></td>\n" +
                "  </tr>";
        multiply = countRejectedQTV*100;
        html+="<tr>\n" +
                "    <td><b>Rejected QTV</b></td>\n" +
                "    <td><b>"+countRejectedQTV+"</b></td>\n" +
                "    <td><b>"+(float)multiply/countTotalProviders+"%</b></td>\n" +
                "  </tr>";
        multiply = countApprovedQTV*100;
        html+="<tr>\n" +
                "    <td><b>Approved QTV</b></td>\n" +
                "    <td><b>"+countApprovedQTV+"</b></td>\n" +
                "    <td><b>"+(float)multiply/countTotalProviders+"%</b></td>\n" +
                "  </tr>";
        html+="</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        Dashboard dashboard = new Dashboard();
        dashboard.setHtml(html);
        dashboard.setId(1);
        return dashboard;
    }
}
