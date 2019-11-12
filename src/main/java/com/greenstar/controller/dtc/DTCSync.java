package com.greenstar.controller.dtc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.DTCSyncDAO;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.entity.dtc.DTCForm;
import com.greenstar.utils.HibernateUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 18/9/2019
 */
@Controller
public class DTCSync {
    Gson gson = new GsonBuilder().setDateFormat("MM/dd/yy").create();
    List<Integer> succesfulIDs = new ArrayList<Integer>();
    @RequestMapping(value = "/dtcsync", method = RequestMethod.GET,params={"data","token"})
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

    public JSONObject   performSync(String code, String data){
        String statusCode = Codes.ALL_OK;
        String message = "Successfully synced";
        JSONObject response = new JSONObject();
        DTCData dataSync = new DTCData();
        boolean isSuccesful = false;

        SyncObjectDTC syncObject = null;

        if(!"".equals(data)){
            syncObject = gson.fromJson(data, SyncObjectDTC.class);

            List<DTCForm> forms = new ArrayList<DTCForm>();
            boolean isValidForm =  false;

            for(DTCForm form : forms){
                    isSuccesful = HibernateUtil.saveOrUpdate(form);
                    if(isSuccesful){
                        succesfulIDs.add(1);
                        isSuccesful = false;
                    }else{
                        statusCode = Codes.SOMETHING_WENT_WRONG;
                        message = "Something went wrong";
                    }
            }
        }

        DTCSyncDAO sync = new DTCSyncDAO();
        String staffName = "";

        try {
            staffName = sync.getStaffName(code);
            dataSync.setName(staffName);
            dataSync.setDistricts(sync.getDistricts(code));
            dataSync.setCode(code);
            response.put("message", message);
            response.put("status", statusCode);
            response.put("staffName",staffName);
            response.put("code",code);
            response.put("data", gson.toJson(dataSync));
            response.put("successfulIDs",succesfulIDs);

        }catch(Exception e){
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }


}
