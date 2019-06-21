package com.greenstar.controller.greensales;

import com.google.gson.Gson;
import com.greenstar.dal.Dashboard;
import com.greenstar.dal.Data;
import com.greenstar.dal.SyncObject;
import com.greenstar.dao.GSSDashboardDAO;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.SyncDAO;
import com.greenstar.entity.*;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Sync {

    @RequestMapping(value = "/sync", method = RequestMethod.GET,params={"code","data","token"})
    @ResponseBody
    public String index(String code, String data, String token){
        GSSStaffDAO gssStaffDAO = new GSSStaffDAO();
        JSONObject response = new JSONObject();
        if(gssStaffDAO.isTokenValid(code,token)){
            return performSync(code,data).toString();
        }else{
            response.put("message", "Invalid Token, you might be logged in from another device");
            response.put("status", Codes.INVALID_TOKEN);
            response.put("data","");
            return response.toString();
        }

    }

    public JSONObject performSync(String code, String data){
        JSONObject response = new JSONObject();
        Data dataSync = new Data();
        List<SDDepot> depots = null;
        List<SDTown> towns = null;
        List<SDCustomer> customers = null;
        List<SDTownDepot> depotRegion = null;
        List<SDTownCustomer> regionCustomer = null;
        List<SDStatus> statuses = null;
        List<SDSKUGroup> skuGroup = null;
        List<GSSWorkWith> workWiths = null;

        String insertCode = "";
        SyncObject syncObject =  new Gson().fromJson(data, SyncObject.class);
        SyncDAO sync = new SyncDAO();
        try {
            if(syncObject==null){
                insertCode = Codes.ALL_OK;
            }else{
                insertCode = sync.insertData(syncObject,code);
            }

            Dashboard dashboard = new Dashboard();
            GSSDashboardDAO dashboardDAO = new GSSDashboardDAO();

            String html = dashboardDAO.getDashboardHTML(code);
            dashboard.setId(1);
            dashboard.setHtml(html);

            depots = sync.getDepots(code);
            towns = sync.getTowns(code);
            customers = sync.getCustomers(code);
            depotRegion = sync.getDepotRegionMapping(code);
            regionCustomer = sync.getRegionCustomerMapping(code);
            statuses = sync.getStatuses();
            skuGroup = sync.getSKUGroup();
            workWiths = sync.getWorkWiths();

            dataSync.setDepots(depots);
            dataSync.setTowns(towns);
            dataSync.setCustomers(customers);
            dataSync.setTownDepots(depotRegion);
            dataSync.setTownCustomers(regionCustomer);
            dataSync.setStatuses(statuses);
            dataSync.setSkuGroup(skuGroup);
            dataSync.setWorkWiths(workWiths);
            dataSync.setDashboard(dashboard);

            if(insertCode.equals("200")){
                response.put("message", "Successfully synced");
                response.put("status", Codes.ALL_OK);
                response.put("data", new Gson().toJson(dataSync));
            }else{
                response.put("message", "Something went wrong while inserting data");
                response.put("status", Codes.SOMETHING_WENT_WRONG);
                response.put("data", new Gson().toJson(dataSync));
            }

        }catch(Exception e){
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }
}
