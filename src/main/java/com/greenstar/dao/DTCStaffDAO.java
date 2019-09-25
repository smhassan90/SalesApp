package com.greenstar.dao;

import com.greenstar.controller.dtc.DTCSync;
import com.greenstar.controller.hs.HSSync;
import com.greenstar.entity.GSSStaff;
import com.greenstar.entity.dtc.Staff;
import com.greenstar.entity.qtv.CHO;
import com.greenstar.utils.HibernateUtil;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 18/9/2019
 */
public class DTCStaffDAO implements IGSSStaffDatabaseDAO {
    public void insertRecord(String code, int status, String token) {
        GSSStaff gssStaff = new GSSStaff();
        Staff temp = new Staff();
        /*
        Type 1 of staffType means : CHO
        Type 2 of staffType means : QTV
        type 3 of staffType means : DTC
         */
        List<Staff> staffs = (List<Staff>) HibernateUtil.getDBObjects("from Staff where staffCode='"+code+"'");

        if(staffs!=null && staffs.size()>0){
            temp =  staffs.get(0);
        }
        gssStaff.setStaffCode(temp.getStaffCode());
        gssStaff.setStaffName(temp.getStaffName());
        gssStaff.setStatus(status);
        gssStaff.setToken(token);
        gssStaff.setStaffType(3);
        if(gssStaff!=null){
            HibernateUtil.saveOrUpdate(gssStaff);
        }
    }

    public boolean isExist(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType=3");

        if( list!=null && list.size()>0)
            return true;
        else
            return false;
    }

    public void updateInformation(String code, int status, String token) {
        List<GSSStaff> gssStaffs = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType=3");

        if(gssStaffs!=null && gssStaffs.size()>0){
            GSSStaff gssStaff =gssStaffs.get(0);
            gssStaff.setStatus(status);
            gssStaff.setToken(token);
            gssStaff.setStaffType(3);
            HibernateUtil.saveOrUpdate(gssStaff);
        }
    }

    public boolean isLoggedIn(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND status=1");

        if(list !=null && list.size()>0)
            return true;
        else
            return false;
    }

    public boolean logout(String code) {
        return false;
    }

    public String getToken(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType=3");

        if(list !=null && list.size()>0){
            return list.get(0).getToken();
        }else{
            return "";
        }
    }

    public String isCorrect(String code) {
        Object obj = HibernateUtil.getDBObjects("from Staff where staffCode='"+code+"'");
        List<Staff> staffs = (List<Staff>)obj;

        if(staffs!=null && staffs.size()>0){
            return staffs.get(0).getStaffCode();
        }
        return "";
    }

    public JSONObject performSync(String code) {
        DTCSync sync = new DTCSync();
        return sync.performSync(code,"");
    }
}