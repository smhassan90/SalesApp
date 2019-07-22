package com.greenstar.controller.hs;

import com.google.gson.Gson;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.HSData;
import com.greenstar.dal.SyncObjectHS;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.HSSyncDAO;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.entity.qtv.QTVForm;
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
 * 24/06/2019
 */

@Controller
public class HSSync {

    @RequestMapping(value = "/hssync", method = RequestMethod.GET,params={"data","token"})
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

    public JSONObject performSync(String code, String data){
        JSONObject response = new JSONObject();
        HSData dataSync = new HSData();
        boolean isSuccesful = false;
        //data = data.substring(0, data.length() - 1);
       SyncObjectHS syncObjectHS = null;
       List<Integer> succesfulIDs = new ArrayList<Integer>();
       if(!"".equals(data)){
           syncObjectHS = new Gson().fromJson(data, SyncObjectHS.class);

           List<QTVForm> forms = syncObjectHS.getQtvForms();
           for(QTVForm form : forms){
               isSuccesful = HibernateUtil.saveOrUpdate(form);
               if(isSuccesful){
                   succesfulIDs.add(form.getId());
                   isSuccesful = false;
               }
           }
       }

        List<Providers> providers = null;

        HSSyncDAO sync = new HSSyncDAO();
        String AMName = "";
        String region = "";
        String staffName = "";
        try {

            providers = sync.getTaggedProviders(code);
            AMName = sync.getAMName(code);
            region = sync.getRegion(code);
            staffName = sync.getStaffName(code);

            dataSync.setAMName(AMName);
            dataSync.setProviders(providers);
            dataSync.setRegion(region);
            dataSync.setName(staffName);
            response.put("message", "Successfully synced");
            response.put("status", Codes.ALL_OK);
            response.put("staffName",staffName);
            response.put("data", new Gson().toJson(dataSync));
            response.put("successfulIDs",succesfulIDs);

        }catch(Exception e){
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }
}
