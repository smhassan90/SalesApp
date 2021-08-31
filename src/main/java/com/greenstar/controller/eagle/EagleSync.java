package com.greenstar.controller.eagle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.utils.LogToFile;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Controller
public class EagleSync {
    final static Logger LOG = Logger.getLogger(EagleSync.class);
    Gson gson = new GsonBuilder().setDateFormat("MM/dd/yy").create();

    final int closingDay = 5;
    final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    protected static final Logger logger=Logger.getLogger("EagleLog");

    String reason="";

    @RequestMapping(value = "/singleFormSyncEagle", method = RequestMethod.GET,params={"data","token","syncType","version"})
    @ResponseBody
    public String singleFormSync(String data, String token, String syncType, String version){

        long startCurrentMilis = Calendar.getInstance().getTimeInMillis();
        GSSStaffDAO gssStaffDAO = new GSSStaffDAO();
        JSONObject response = new JSONObject();
        String staffCode  = gssStaffDAO.isTokenValid(token);

        String result = "";
        if(version.equals(Codes.VERSIONFALCON)) {
            if (!"".equals(staffCode)) {
                result = performSingleFormSync(data, syncType).toString();
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

    public JSONObject performSingleFormSync(String data, String syncType){
        SyncObjectHS syncObjectHS = null;
        boolean isSyncProper = false;
        if(!"".equals(data)) {
            syncObjectHS = gson.fromJson(data, SyncObjectHS.class);
            if(syncType.equals(Codes.SINGLE_QTV_FORM)){
            //    isSyncProper=  syncQTVForm(syncObjectHS.getQtvForms());
            }
        }
        JSONObject response = new JSONObject();
        if(isSyncProper){


        }else{
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }

}
