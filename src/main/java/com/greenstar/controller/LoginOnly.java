package com.greenstar.controller;

import com.google.gson.Gson;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.controller.greensales.Login;
import com.greenstar.dal.HSData;
import com.greenstar.dao.*;
import com.greenstar.entity.qtv.CHO;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Syed Muhammad Hassan
 * 31st August, 2020
 */
@Controller
public class LoginOnly {


    final static Logger LOG = Logger.getLogger(Login.class);

    String errorCode="";
    String message="";
    String data="";
    String token = "";
    String staffName = "";
    int isQATAllowed = 0;
    int isQTVAllowed = 0;

    /*
       This method will check the code validity and already logged in and then perform
       Login and generate token to further use for communication.
     */
    @RequestMapping(value = "/loginOnly", method = RequestMethod.GET,params={"code","uniqueId","staffType"})
    @ResponseBody
    public String index(String code, String uniqueId, int staffType){

        JSONObject json = new JSONObject();

        IGSSStaffDatabaseDAO gssStaffDAO = null;
        if(staffType==Codes.FALCON_APP_CODE){
            //request is coming from CHO HS team
            gssStaffDAO = new HSStaffDAO();
        }else if(staffType==Codes.SALES_APP_CODE){
            //request is coming from FMCG Sales team
            gssStaffDAO = new GSSStaffDAO();
        }else if(staffType==Codes.DTC_APP_CODE){
            //request is coming from DTC application
            gssStaffDAO = new DTCStaffDAO();
        }else if(staffType == Codes.MECWHEEL_APP_CODE){
            //request is coming from MEC Wheel application
            gssStaffDAO = new CRBStaffDAO();
        }else if(staffType == Codes.EAGLE_APP_CODE){
            //request is coming from Eagle application
            gssStaffDAO = new EagleStaffDAO();
        }
        String UUID =uniqueId;
        String staffCode = gssStaffDAO.isCorrect(code);

        if(staffCode!=null && !"".equals(staffCode)){
            if(gssStaffDAO.isExist(staffCode)){
                if(staffCode.equals("TEST")){
                    gssStaffDAO.logout(staffCode);
                }
                if(gssStaffDAO.isLoggedIn(staffCode)){
                    // Show message that you need to contact admin.
                    errorCode=Codes.ALREADY_LOGGED_IN;
                    message="You are already logged in. Please contact admin";
                }else{
                    performLogin(UUID, gssStaffDAO, staffCode );
                }
            }else{
                String token = HibernateUtil.generateToken(UUID);
                gssStaffDAO.insertRecord(staffCode, 1, token);
                verificationLoggedIn(gssStaffDAO,staffCode,token);
            }

        }else{
            errorCode = Codes.NOT_FOUND;
            message = "Code is invalid";
        }
        /*
        This code will logout the user from other device and then attempt login again
        For now, this functionality is on hold. The user has will be intimated that
        he is been online in other phone. Kindly contact admin to force logout from other
        phone.

        if(errorCode!=Codes.ALL_OK){
            //Request logout
            gssStaffDAO.logout(code);
            performLogin(UUID,gssStaffDAO,code);
        }
        */
        try{
            json.put("status", errorCode);
            json.put("message", message);
            json.put("data",data);
            json.put("token", token);
            json.put("baseID",HibernateUtil.getNextBaseID(staffType));
            json.put("staffName",staffName);
            json.put("staffCode",staffCode);
        }catch(Exception e){
            LOG.error(e);
        }

        return json.toString();
    }

    private void verificationLoggedIn(IGSSStaffDatabaseDAO gssStaffDAO, String code, String token) {

        if(gssStaffDAO.isLoggedIn(code)){
            errorCode = Codes.ALL_OK;
            message = "You are successfully logged in";
            if(gssStaffDAO.getToken(code).equals(token)){
                /*
                JSONObject dataResponse = gssStaffDAO.performSync(code);
                data = (String) dataResponse.get("data");
                errorCode = (String) dataResponse.get("status");
                message = (String) dataResponse.get("message");
                staffName = (String) dataResponse.get("staffName");
                */
                HSSyncDAO hsSync = new HSSyncDAO();
                CHO cho = hsSync.getStaff(code);
                staffName = gssStaffDAO.getName(code);
                HSData dataObjj = new HSData();
                dataObjj.setIsQATAllowed(cho.getIsQATAllowed());
                dataObjj.setIsQTVAllowed(cho.getIsQTVAllowed());
                dataObjj.setIsQATAMAllowed(cho.getIsQATAMAllowed());
                dataObjj.setName(staffName);
                data = new Gson().toJson(dataObjj);
                errorCode = Codes.ALL_OK;
                message = "Sync Successful";
                staffName = gssStaffDAO.getName(code);
                this.token = token;
            }else{
                data = "";
                errorCode = Codes.FORCED_LOGOUT;
                message = "Forced Logout";
            }
        }else{
            errorCode = Codes.SOMETHING_WENT_WRONG;
            message = "Something went wrong";
        }
    }
    private void performLogin(String UUID, IGSSStaffDatabaseDAO gssStaffDAO, String code){
        // Logging in.
        String token = HibernateUtil.generateToken(UUID);
        gssStaffDAO.updateInformation(code,1, token);
        verificationLoggedIn(gssStaffDAO,code,token);
    }
}