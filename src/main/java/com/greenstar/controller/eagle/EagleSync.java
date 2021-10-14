package com.greenstar.controller.eagle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.CRBSyncDAO;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.HSSyncDAO;
import com.greenstar.entity.qtv.CHO;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.utils.LogToFile;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class EagleSync {
    final static Logger LOG = Logger.getLogger(EagleSync.class);
    Gson gson = new GsonBuilder().setDateFormat("MM/dd/yy").create();

    final int closingDay = 5;
    final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    protected static final Logger logger=Logger.getLogger("EagleLog");

    String reason="";


    @RequestMapping(value = "/syncEagle", method = RequestMethod.GET,params={"data","token","syncType","version"})
    @ResponseBody
    public String syncEagle(String data, String token, String syncType, String version){

        long startCurrentMilis = Calendar.getInstance().getTimeInMillis();
        GSSStaffDAO gssStaffDAO = new GSSStaffDAO();
        JSONObject response = new JSONObject();
        String staffCode  = "";
        String result = "";

        if(version.equals(Codes.VERSIONEAGLE)) {
            staffCode = gssStaffDAO.isTokenValid(token);
            if (!"".equals(staffCode)) {
                result = performSync(data, syncType, staffCode).toString();
            } else {
                response.put("message", "Invalid Token,ddd you might be logged in from another device");
                response.put("status", Codes.INVALID_TOKEN);
                response.put("data", "");
                result = response.toString();
            }
        }else{
            response.put("message", "Please update Falcon Application from playstore to version number "+version);
            response.put("status", Codes.INVALID_VERSION);
            response.put("data", "");
            result = response.toString();
        }
        long endCurrentMilis = Calendar.getInstance().getTimeInMillis();
        long timeTaken = endCurrentMilis - startCurrentMilis;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeTaken);
        LogToFile.log(null,"info", "Time taken to single form sync staffCode : "+ staffCode+" is "+seconds+"Seconds. ");
        return result;
    }

    public JSONObject performSync(String data, String syncType, String staffCode){
        EagleData eagleData = null;
        boolean isSyncProper = false;
        if(syncType.equals(Codes.PullAllEagleData)){
            eagleData = getAllData(staffCode);
        }
        JSONObject response = new JSONObject();
        if(eagleData!=null){
            response.put("message", "Sync Successful");
            response.put("status", Codes.ALL_OK);
            response.put("data",gson.toJson(eagleData));
        }else{
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }
        return response;
    }

    private EagleData getAllData(String staffCode) {
        EagleData eagleData = new EagleData();
        HSSyncDAO sync = new HSSyncDAO();
        CRBSyncDAO syncDD = new CRBSyncDAO();
        List<Providers> providers = new ArrayList<>();
        providers = sync.getTaggedProviders(staffCode);
        CHO cho = new CHO();
        cho = sync.getStaff(staffCode);
        if(providers !=null && providers.size()>0){
            Providers provider = providers.get(0);
            eagleData.setProviderCode(provider.getCode());
            eagleData.setProviderName(provider.getName());
            eagleData.setDistrict(provider.getDistrict());
        }

        eagleData.setAMCode(sync.getAMCode(staffCode));
        eagleData.setAMName(sync.getAMName(staffCode));
        eagleData.setRegion(sync.getRegion(staffCode));
        eagleData.setSitaraBajiCode(cho.getStaffCode());
        eagleData.setSitaraBajiName(cho.getName());
        eagleData.setDropdownCRBData(syncDD.getDropdownData());

        return eagleData;
    }
}