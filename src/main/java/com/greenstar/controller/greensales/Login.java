package com.greenstar.controller.greensales;

import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.IGSSStaffDatabaseDAO;
import com.greenstar.dao.IStaffVerificationDAO;
import com.greenstar.dao.StaffDAO;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class Login {

    final static Logger LOG = Logger.getLogger(Login.class);

    String errorCode="";
    String message="";
    String data="";
    String token = "";

    /*
       This method will check the code validity and already logged in and then perform
       Login and generate token to further use for communication.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET,params={"code","uniqueId"})
    @ResponseBody
    public String index(String code, String uniqueId){

        JSONObject json = new JSONObject();
        IStaffVerificationDAO staffVerification = new StaffDAO();

        IGSSStaffDatabaseDAO gssStaffDAO = new GSSStaffDAO();
        String UUID =uniqueId;
        if(staffVerification.isCorrect(code)){
            if(gssStaffDAO.isExist(code)){
                if(gssStaffDAO.isLoggedIn(code)){
                    // Show message that you need to contact admin.
                    errorCode=Codes.ALREADY_LOGGED_IN;
                    message="You are already logged in. Please contact admin";
                }else{
                    performLogin(UUID, gssStaffDAO, code );
                }
            }else{
                String token = HibernateUtil.generateToken(UUID);
                gssStaffDAO.insertRecord(code, 1, token);
                verificationLoggedIn(gssStaffDAO,code,token);
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
                Sync sync = new Sync();
                JSONObject dataResponse = sync.performSync(code,"");
                data = (String) dataResponse.get("data");
                errorCode = (String) dataResponse.get("status");
                message = (String) dataResponse.get("message");
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
