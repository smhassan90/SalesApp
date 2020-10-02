package com.greenstar.controller.hs;

import com.google.gson.Gson;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.HSSyncDAO;
import com.greenstar.entity.qat.Area;
import com.greenstar.entity.qat.QATTCForm;
import com.greenstar.entity.qat.Question;
import com.greenstar.entity.qtv.*;
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
 * 1st September, 2020
 */

@Controller
public class HSPartialSync {

    HSSyncDAO hsSyncDAO = new HSSyncDAO();
    @RequestMapping(value = "/PSBasicInfo", method = RequestMethod.GET,params={"token","PSType"})
    @ResponseBody
    public String PSBasicInfo(String token,String PSType){
        HSData dataObj = new HSData();
        JSONObject response = new JSONObject();
        String message = "";
        String status = "";
        String code="";
        String data = "";
        String staffName= "";
        GSSStaffDAO staffDAO  = new GSSStaffDAO();
        code = staffDAO.isTokenValid(token);
        CHO cho = null;
        if(!code.equals("")){
            try{
                ArrayList<CHO> chos = new ArrayList<CHO>();
                chos = (ArrayList<CHO>) HibernateUtil.getDBObjects("FROM CHO WHERE territoryCode='"+code+"'");
                if(chos!=null && chos.size()==1){
                    cho = chos.get(0);
                }

                if(cho!=null) {

                    if(PSType.equals(Codes.PS_TYPE_BASIC_INFO)){
                        dataObj = syncBasicInfo(cho);
                    }else if (PSType.equals(Codes.PS_TYPE_Providers)){
                        dataObj = syncProviders(cho);
                    }else if (PSType.equals(Codes.PS_TYPE_ApprovalQATForm)){
                        dataObj = syncApprovedQATForms(cho);
                    }else if (PSType.equals(Codes.PS_TYPE_ApprovalQTVForm)){
                        dataObj = syncApprovalQTVForm(cho);
                    }else if (PSType.equals(Codes.PS_TYPE_Area)){
                        dataObj = syncAreas(cho);
                    }else if (PSType.equals(Codes.PS_TYPE_QATTCForm)){
                        dataObj = syncApprovalQATTCForm(cho);
                    }else if (PSType.equals(Codes.PS_TYPE_Question)){
                        dataObj = syncQuestions(cho);
                    }
                    if(dataObj!=null) {
                        data = new Gson().toJson(dataObj);
                        message = "Successfuly synced";
                        status = Codes.ALL_OK;

                    }else{
                        message = "Something went wrong";
                        status = Codes.SOMETHING_WENT_WRONG;
                    }
                }else{
                    message = "Token is invalid";
                    status = Codes.INVALID_TOKEN;
                }

            }catch(Exception e){
                message  = "Something went wrong";
                status = Codes.ERROR_PS_BASICINFO;
            }finally {
                response.put("message", message);
                response.put("status", status);
                response.put("staffName", staffName);
                response.put("data", data);
                response.put("PSType",PSType);
                return response.toString();
            }
        }else{
            message = "Invalid Token, you might be logged in from another device";
            status = Codes.INVALID_TOKEN;
        }
        response.put("message", message);
        response.put("status", status);
        response.put("staffName", staffName);
        response.put("data", data);
        response.put("PSType",PSType);
        return response.toString();
    }

    private HSData syncProviders(CHO cho) {
        HSData data = new HSData();
        List<Providers> providers = new ArrayList<>();
        providers = hsSyncDAO.getTaggedProviders(cho.getTerritoryCode());
        data.setCode(cho.getTerritoryCode());
        data.setProviders(providers);
        return data;
    }

    private HSData syncQuestions(CHO cho) {
        HSData data = new HSData();
        List<Question> questions = new ArrayList<>();
        questions = hsSyncDAO.getQATQuestions();
        data.setCode(cho.getTerritoryCode());
        data.setQuestions(questions);
        return data;
    }

    private HSData syncApprovalQTVForm(CHO cho) {

        HSData data = new HSData();
        List<ApprovalQTVForm> qtvForms = new ArrayList<>();
        String reportingMonth = new HSSync().getReportingMonth(new Date(System.currentTimeMillis()));
        qtvForms = hsSyncDAO.getApprovedQTVForms(cho.getTerritoryCode(), reportingMonth);
        data.setCode(cho.getTerritoryCode());
        data.setQtvForms(qtvForms);
        return data;
    }

    private HSData syncApprovalQATTCForm(CHO cho) {

        HSData data = new HSData();
        List<QATTCForm> forms = new ArrayList<>();
        forms = hsSyncDAO.getApprovedQATTCForms(cho.getTerritoryCode());
        data.setCode(cho.getTerritoryCode());
        data.setQattcForms(forms);
        return data;
    }

    private HSData syncAreas(CHO cho) {
        HSData data = new HSData();
        List<Area> areas = new ArrayList<>();
        areas = hsSyncDAO.getQATAreas();
        data.setCode(cho.getTerritoryCode());
        data.setAreas(areas);
        return data;
    }

    private HSData syncApprovedQATForms(CHO cho) {
        List<ApprovalQATForm> approvalQATForms = new ArrayList<ApprovalQATForm>();
        List<ApprovalQATFormQuestion> approvalQATFormQuestions = new ArrayList<ApprovalQATFormQuestion>();
        List<ApprovalQATArea> approvalQATAreas = new ArrayList<ApprovalQATArea>();
        approvalQATForms = hsSyncDAO.getApprovedQATForms(cho.getTerritoryCode());
        List<Long> QATIds = new ArrayList<>();
        if(approvalQATForms!=null && approvalQATForms.size()>0 ) {
            for (ApprovalQATForm form : approvalQATForms)
                QATIds.add(form.getId());
        }
        if(QATIds.size()>0){
            approvalQATFormQuestions = hsSyncDAO.getApprovedQATQuestions(QATIds);
            approvalQATAreas = hsSyncDAO.getApprovalQATAreas(QATIds);
        }

        HSData data = new HSData();
        data.setCode(cho.getTerritoryCode());
        data.setApprovalQATAreas(approvalQATAreas);
        data.setApprovalQATFormQuestions(approvalQATFormQuestions);
        data.setApprovalQATForms(approvalQATForms);
        return data;
    }

    private HSData syncBasicInfo(CHO cho) {
        HSData data = new HSData();

        String amName = hsSyncDAO.getAMName(cho.getTerritoryCode());
        String amCode = hsSyncDAO.getAMCode(cho.getTerritoryCode());
        String region = hsSyncDAO.getRegion(cho.getTerritoryCode());
        if(amName!=null && amName.equals("") && amCode!=null && amCode.equals("")){
            return null;
        }
        data.setName(cho.getName());
        data.setIsQATAllowed(cho.getIsQATAllowed());
        data.setIsQTVAllowed(cho.getIsQTVAllowed());
        data.setCode(cho.getTerritoryCode());
        data.setAMCode(amCode);
        data.setAMName(amName);
        data.setRegion(region);
        data.setName(cho.getName());
        data.setCode(cho.getTerritoryCode());
        return data;
    }

}
