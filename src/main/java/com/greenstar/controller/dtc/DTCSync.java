package com.greenstar.controller.dtc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.DTCSyncDAO;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.entity.dtc.DTCForm;
import com.greenstar.entity.dtc.MeetingData;
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
    List<Long> DTCFormSuccessfulIDs = new ArrayList<Long>();
    List<Long> MeetingDataSuccessfulIDs = new ArrayList<Long>();

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
            List<MeetingData> meetingDataList = new ArrayList<>();

            boolean isValidForm =  false;

            forms = syncObject.getForms();
            meetingDataList = syncObject.getMeetingDataList();

            if(forms!=null){
                for(DTCForm form : forms){
                    form.setStaffCode(code);
                    form.setStatus(1);
                    isSuccesful = HibernateUtil.saveOrUpdate(form);
                    if(isSuccesful){
                        DTCFormSuccessfulIDs.add(form.getId());
                        isSuccesful = false;
                    }else{
                        statusCode = Codes.SOMETHING_WENT_WRONG;
                        message = "Something went wrong";
                    }
                }
            }

            if(meetingDataList!=null){
                for(MeetingData meetingData : meetingDataList){
                    meetingData.setStaffCode(code);
                    meetingData.setStatus(1);
                    isSuccesful = HibernateUtil.saveOrUpdate(meetingData);
                    if(isSuccesful){
                        MeetingDataSuccessfulIDs.add(meetingData.getId());
                        isSuccesful = false;
                    }else{
                        statusCode = Codes.SOMETHING_WENT_WRONG;
                        message = "Something went wrong";
                    }
                }
            }
        }

        DTCSyncDAO sync = new DTCSyncDAO();
        String staffName = "";

        try {
            staffName = sync.getStaffName(code);
            dataSync.setName(staffName);
            dataSync.setDistricts(sync.getDistricts(code));
            dataSync.setDtcForms(sync.getDTCForms(code));
            dataSync.setCode(code);
            response.put("message", message);
            response.put("status", statusCode);
            response.put("staffName",staffName);
            response.put("code",code);
            response.put("data", gson.toJson(dataSync));
            response.put("MeetingDataSuccessfulIDs",MeetingDataSuccessfulIDs);
            response.put("DTCFormSuccessfulIDs",DTCFormSuccessfulIDs);

        }catch(Exception e){
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }
}
