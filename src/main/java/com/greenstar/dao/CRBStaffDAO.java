package com.greenstar.dao;

import com.greenstar.controller.greensales.Codes;
import com.greenstar.controller.mecwheel.CRBSync;
import com.greenstar.entity.GSSStaff;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.utils.HibernateUtil;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 18th December, 2019
 */
public class CRBStaffDAO  implements IGSSStaffDatabaseDAO {
    public void insertRecord(String code, int status, String token) {
        GSSStaff gssStaff = new GSSStaff();
        Providers provider = new Providers();
        /*
        Type 1 of staffType means : CHO
        Type 2 of staffType means : QTV
        type 3 of staffType means : DTC
        type 4 of staffType means : MEC Wheel
         */
        List<Providers> providers = (List<Providers>) HibernateUtil.getDBObjects("from Providers where code='"+code+"'");

        if(providers!=null && providers.size()>0){
            provider =  providers.get(0);
        }
        gssStaff.setStaffCode(provider.getCode());
        gssStaff.setStaffName(provider.getName());
        gssStaff.setStatus(status);
        gssStaff.setToken(token);
        gssStaff.setStaffType(Codes.MECWHEEL_APP_CODE);
        if(gssStaff!=null){
            HibernateUtil.saveOrUpdate(gssStaff);
        }
    }

    public boolean isExist(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType="+Codes.MECWHEEL_APP_CODE);

        if( list!=null && list.size()>0)
            return true;
        else
            return false;
    }

    public void updateInformation(String code, int status, String token) {
        List<GSSStaff> gssStaffs = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType=" + Codes.MECWHEEL_APP_CODE);

        if(gssStaffs!=null && gssStaffs.size()>0){
            GSSStaff gssStaff =gssStaffs.get(0);
            gssStaff.setStatus(status);
            gssStaff.setToken(token);
            gssStaff.setStaffType(Codes.MECWHEEL_APP_CODE);
            HibernateUtil.saveOrUpdate(gssStaff);
        }
    }

    public boolean isLoggedIn(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND status=1 AND staffType="+Codes.MECWHEEL_APP_CODE);

        if(list !=null && list.size()>0)
            return true;
        else
            return false;
    }

    public boolean logout(String code) {
        return false;
    }

    public String getToken(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType="  + Codes.MECWHEEL_APP_CODE);

        if(list !=null && list.size()>0){
            return list.get(0).getToken();
        }else{
            return "";
        }
    }

    public String isCorrect(String code) {
        Object obj = HibernateUtil.getDBObjects("from Providers where code='"+code+"'");
        List<Providers> providers = (List<Providers>)obj;

        if(providers!=null && providers.size()>0){
            return providers.get(0).getCode();
        }
        return "";
    }

    public JSONObject performSync(String code) {
        CRBSync sync = new CRBSync();
        return sync.performSync(code,"");
    }

    public void insertMeetingImg(String query){
        HibernateUtil.insertDTCImg(query);
    }
}