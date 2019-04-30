package com.greenstar.controller.greensales;

import com.greenstar.dao.IStaffVerificationDAO;
import com.greenstar.dao.StaffDAO;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class Login {
    String errorCode="";
    String message="";
    String data="";

    @RequestMapping(value = "/login", method = RequestMethod.GET,params={"code","uniqueId"})
    @ResponseBody
    public String index(String code, String uniqueId){

        JSONObject json = new JSONObject();
        IStaffVerificationDAO staffVerification = new StaffDAO();

        IGSSStaffDatabaseDAO gssStaffDAO = context.getBean(GSSStaffDAO.class);
        String UUID =uniqueId;
        if(staffVerification.isCorrect(code)){
            if(gssStaffDAO.isExist(code)){
                if(gssStaffDAO.isLoggedIn(code)){
                    // Show message that you need to contact admin.
                    errorCode=Codes.ALREADY_LOGGED_IN;
                    message="You are already logged in. Please contact admin";
                }else{
                    // Logging in.
                    String token = Util.generateToken(UUID);
                    gssStaffDAO.updateInformation(code,1, token);
                    verificationLoggedIn(gssStaffDAO,code);
                }
            }else{
                gssStaffDAO.insertRecord(code, 1, Util.generateToken(UUID));
                verificationLoggedIn(gssStaffDAO,code);
            }

        }else{
            errorCode = Codes.NOT_FOUND;
            message = "Code is invalid";
        }

        try{
            json.put("status", errorCode);
            json.put("message", message);
            json.put("data",data);
        }catch(Exception e){

        }
        if(errorCode!=Codes.ALL_OK){
            //Request logout
            gssStaffDAO.logout(code);
        }
        return json.toString();
    }

    private void verificationLoggedIn(IGSSStaffDatabaseDAO gssStaffDAO, String code) {

        if(gssStaffDAO.isLoggedIn(code)){
            errorCode = Codes.ALL_OK;
            message = "You are successfully logged in";
            Customer customer = new Customer();
            Map<String,String> dataResponse = customer.index(code);
            data = dataResponse.get("data");
            errorCode = dataResponse.get("status");
            message = dataResponse.get("message");
        }else{
            errorCode = Codes.SOMETHING_WENT_WRONG;
            message = "Something went wrong";
        }
    }
}
