package com.greenstar.controller.mecwheel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.CRBData;
import com.greenstar.dal.SyncObjectCRB;
import com.greenstar.dao.CRBSyncDAO;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.entity.crb.CRBForm;
import com.greenstar.entity.qtv.Providers;
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
 * 29th November, 2019
 */

@Controller
public class CRBSync {

    Gson gson = new GsonBuilder().setDateFormat("MM/dd/yy").create();
    List<Long> CRBFormSuccessfulIDs = new ArrayList<Long>();

    @RequestMapping(value = "/crbsync", method = RequestMethod.GET,params={"data","token"})
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
        CRBData dataSync = new CRBData();
        boolean isSuccesful = false;

        SyncObjectCRB syncObject = null;

        if(!"".equals(data)){
            syncObject = gson.fromJson(data, SyncObjectCRB.class);

            List<CRBForm> forms = new ArrayList<CRBForm>();

            forms = syncObject.getCrbForms();

            if(forms!=null){
                for(CRBForm form : forms){
                    form.setProviderCode(code);
                    form.setStatus(1);
                    isSuccesful = HibernateUtil.saveOrUpdate(form);
                    if(isSuccesful){
                        CRBFormSuccessfulIDs.add(form.getId());
                        isSuccesful = false;
                    }else{
                        statusCode = Codes.SOMETHING_WENT_WRONG;
                        message = "Something went wrong";
                    }
                }
            }
        }

        CRBSyncDAO sync = new CRBSyncDAO();
        String providerName = "";

        try {
            Providers provider = sync.getProvider(code);

            providerName = provider.getName();
            dataSync.setCode(code);
            dataSync.setName(providerName);
            dataSync.setCrbForms(sync.getCRBForms(code));
            dataSync.setDropdownCRBData(sync.getDropdownData());
            response.put("message", message);
            response.put("status", statusCode);
            response.put("providerName",providerName);
            response.put("code",code);
            response.put("staffName",providerName);
            response.put("data", gson.toJson(dataSync));
            response.put("CRBFormSuccessfulIDs",CRBFormSuccessfulIDs);

        }catch(Exception e){
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }
}