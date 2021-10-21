package com.greenstar.controller.eagle;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.HSSyncDAO;
import com.greenstar.entity.eagle.*;
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
import java.util.Calendar;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 13th October, 2021
 */

@Controller
public class EaglePartialSync {
    Gson gson = new GsonBuilder().setDateFormat("MM/dd/yy").create();
    HSSyncDAO hsSyncDAO = new HSSyncDAO();
    final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @RequestMapping(value = "/PSEagleBasicInfo", method = RequestMethod.GET,params={"token","PSType", "data", "version"})
    @ResponseBody
    public String PSBasicInfo(String token,String PSType, String data, String version){

        EagleData dataObj = new EagleData();
        JSONObject response = new JSONObject();
        String message = "";
        String status = "";
        String code = "";
        String staffName = "";
        GSSStaffDAO staffDAO = new GSSStaffDAO();
        boolean isSuccessfulPS = false;
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
                            if(dataObj!=null){
                                isSuccessfulPS=true;
                            }
                        } else if (PSType.equals(Codes.PS_EAGLE_TYPE_Providers)) {
                            dataObj = syncProviders(cho);
                            if(dataObj!=null){
                                isSuccessfulPS=true;
                            }
                        }else{
                            EagleClientToServer eagleClientToServer = gson.fromJson(data,EagleClientToServer.class);
                            isSuccessfulPS = syncClientToServer(cho, eagleClientToServer);
                        }


                        if (isSuccessfulPS) {
                            data = gson.toJson(dataObj);
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

    private boolean syncClientToServer(CHO cho, EagleClientToServer eagleClientToServer) {

        boolean isSuccessful = true;
        String territoryCode = cho.getTerritoryCode();
        String sitaraBajiCode = territoryCode;
        String sitaraBajiName = cho.getName();
        String providerCode = "";
        String providerName = "";
        String providerDistrict = "";

        HSSyncDAO general = new HSSyncDAO();
        List<Providers> providers = general.getTaggedProviders(territoryCode);
        if(providers!=null && providers.size()>0){
            Providers provider = providers.get(0);
            providerCode = provider.getCode();
            providerName = provider.getName();
            providerDistrict = provider.getDistrict();
        }
        String region = general.getRegion(territoryCode);
        String supervisorCode = general.getAMCode(territoryCode);
        String supervisorName = general.getAMName(territoryCode);
        if(eagleClientToServer !=null && eagleClientToServer.getCrForms() != null && eagleClientToServer.getCrForms().size()>0){
            List<CRForm> forms = new ArrayList<>();
            List<CRForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getCrForms();

            if(forms!=null){
                for(CRForm form : forms){
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }

        if(eagleClientToServer !=null && eagleClientToServer.getChildRegistrationForms() != null && eagleClientToServer.getChildRegistrationForms().size()>0){
            List<ChildRegistrationForm> forms = new ArrayList<>();
            List<ChildRegistrationForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getChildRegistrationForms();
            if(forms!=null){
                for(ChildRegistrationForm form : forms){
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }

        if(eagleClientToServer !=null && eagleClientToServer.getFollowupForms() != null && eagleClientToServer.getFollowupForms().size()>0){
            List<FollowupForm> forms = new ArrayList<>();
            List<FollowupForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getFollowupForms();
            if(forms!=null){
                for(FollowupForm form : forms){
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }

        if(eagleClientToServer !=null && eagleClientToServer.getNeighbourForms() != null && eagleClientToServer.getNeighbourForms().size()>0){
            List<NeighbourForm> forms = new ArrayList<>();
            List<NeighbourForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getNeighbourForms();
            if(forms!=null) {
                for (NeighbourForm form : forms) {
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }
            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }
        if(eagleClientToServer !=null && eagleClientToServer.getTokenForms() != null && eagleClientToServer.getTokenForms().size()>0){
            List<TokenForm> forms = new ArrayList<>();
            List<TokenForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getTokenForms();
            if(forms!=null) {
                for (TokenForm form : forms) {
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getReferralDate()));
                    listToInsert.add(form);
                }
            }
            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }


        if(eagleClientToServer !=null && eagleClientToServer.getNeighbourAttendeesForms() != null && eagleClientToServer.getNeighbourAttendeesForms().size()>0){

            List<NeighbourAttendeesForm> listToInsert = new ArrayList<>();

            listToInsert = eagleClientToServer.getNeighbourAttendeesForms();

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }
        return isSuccessful;
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

    public String getReportingMonth(Date visitDate) {
        String reportingMonth = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(visitDate);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        String name = monthNames[month];
        reportingMonth=name+","+String.valueOf(year);
        return reportingMonth;
    }

}
