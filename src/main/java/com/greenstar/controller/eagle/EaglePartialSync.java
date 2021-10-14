package com.greenstar.controller.eagle;


import com.google.gson.Gson;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.controller.hs.HSSync;
import com.greenstar.dal.*;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.HSSyncDAO;
import com.greenstar.entity.qat.Area;
import com.greenstar.entity.qat.QATTCForm;
import com.greenstar.entity.qat.Question;
import com.greenstar.entity.qtv.CHO;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.utils.HibernateUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 13th October, 2021
 */

@Controller
public class EaglePartialSync {

    HSSyncDAO hsSyncDAO = new HSSyncDAO();
    @RequestMapping(value = "/PSEagleBasicInfo", method = RequestMethod.GET,params={"token","PSType","version"})
    @ResponseBody
    public String PSBasicInfo(String token,String PSType, String version){

        EagleData dataObj = new EagleData();
        JSONObject response = new JSONObject();
        String message = "";
        String status = "";
        String code = "";
        String data = "";
        String staffName = "";
        GSSStaffDAO staffDAO = new GSSStaffDAO();
        if(version.equals(Codes.VERSIONEAGLE)) {
            code = staffDAO.isTokenValid(token);
            CHO cho = null;
            if (!code.equals("")) {
                try {
                    ArrayList<CHO> chos = new ArrayList<CHO>();
                    chos = (ArrayList<CHO>) HibernateUtil.getDBObjects("FROM CHO WHERE territoryCode='" + code + "'");
                    if (chos != null && chos.size() == 1) {
                        cho = chos.get(0);
                    }

                    if (cho != null) {

                        if (PSType.equals(Codes.PS_EAGLE_TYPE_BASIC_INFO)) {
                            dataObj = syncBasicInfo(cho);
                        } else if (PSType.equals(Codes.PS_EAGLE_TYPE_Providers)) {
                            dataObj = syncProviders(cho);
                        } else if (PSType.equals(Codes.PS_EAGLE_TYPE_Client)) {
                            dataObj = syncApprovedQATForms(cho);
                        } else if (PSType.equals(Codes.PS_EAGLE_TYPE_Children)) {
                            dataObj = syncApprovalQTVForm(cho);
                        } else if (PSType.equals(Codes.PS_EAGLE_TYPE_Followup)) {
                            dataObj = syncAreas(cho, type);
                        } else if (PSType.equals(Codes.PS_EAGLE_TYPE_Neighbour)) {
                            dataObj = syncApprovalQATTCForm(cho);
                        } else if (PSType.equals(Codes.PS_EAGLE_TYPE_Token)) {
                            dataObj = syncQuestions(cho, type);
                        }
                        if (dataObj != null) {
                            data = new Gson().toJson(dataObj);
                            message = "Successfuly synced";
                            status = Codes.ALL_OK;

                        } else {
                            message = "Something went wrong";
                            status = Codes.SOMETHING_WENT_WRONG;
                        }
                    } else {
                        message = "Token is invalid";
                        status = Codes.INVALID_TOKEN;
                    }

                } catch (Exception e) {
                    message = "Something went wrong";
                    status = Codes.ERROR_PS_BASICINFO;
                } finally {
                    response.put("message", message);
                    response.put("status", status);
                    response.put("staffName", staffName);
                    response.put("data", data);
                    response.put("PSType", PSType);
                    return response.toString();
                }
            } else {
                message = "Invalid Token, you might be logged in from another device";
                status = Codes.INVALID_TOKEN;
            }
        }else{
            message = "Please update Falcon Application from playstore to version number "+version;
            status = Codes.INVALID_VERSION;
        }
        response.put("message", message);
        response.put("status", status);
        response.put("staffName", staffName);
        response.put("data", data);
        response.put("PSType",PSType);
        return response.toString();
    }

    private EagleData syncClient(CHO cho) {

    }

    private EagleData syncProviders(CHO cho) {
        EagleData data = new EagleData();
        List<Providers> providers = new ArrayList<>();
        providers = hsSyncDAO.getTaggedProviders(cho.getTerritoryCode());
        if(providers!=null && providers.size()>0){
            data.setProviderCode(providers.get(0).getCode());
            data.setProviderName(providers.get(0).getName());
        }

        return data;
    }

    private EagleData syncBasicInfo(CHO cho) {
        EagleData data = new EagleData();

        String amName = hsSyncDAO.getAMName(cho.getTerritoryCode());
        String amCode = hsSyncDAO.getAMCode(cho.getTerritoryCode());
        String region = hsSyncDAO.getRegion(cho.getTerritoryCode());
        if(amName!=null && amName.equals("") && amCode!=null && amCode.equals("")){
            return null;
        }
        data.setSitaraBajiName(cho.getName());
        data.setSitaraBajiCode(cho.getTerritoryCode());
        data.setAMCode(amCode);
        data.setAMName(amName);
        data.setRegion(region);
        return data;
    }

}
