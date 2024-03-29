package com.greenstar.dao;

import com.greenstar.controller.greensales.Codes;
import com.greenstar.controller.greensales.Sync;
import com.greenstar.controller.hs.HSSync;
import com.greenstar.entity.GSSStaff;
import com.greenstar.entity.qtv.CHO;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */
public class HSStaffDAO implements IGSSStaffDatabaseDAO {
    final static Logger LOG = Logger.getLogger(HSStaffDAO.class);
    public void insertRecord(String code, int status, String token) {
        GSSStaff gssStaff = new GSSStaff();
        CHO temp = new CHO();
        /*
        Type 1 of staffType means : CHO
        Type 2 of staffType means : QTV
         */
        List<CHO> chos = (List<CHO>) HibernateUtil.getDBObjects("from CHO where territoryCode='"+code+"'");
        //List<SDStaff> SDStaffs = session.createQuery("from SDStaff where staffCode='"+code+"'").list();

        if(chos!=null && chos.size()>0){
            temp =  chos.get(0);
        }
        gssStaff.setStaffCode(temp.getTerritoryCode());
        gssStaff.setStaffName(temp.getName());
        gssStaff.setStatus(status);
        gssStaff.setToken(token);
        gssStaff.setStaffType(1);
        if(gssStaff!=null){
            HibernateUtil.saveOrUpdate(gssStaff);
        }
    }

    public boolean isExist(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType=1");

        if( list!=null && list.size()>0)
            return true;
        else
            return false;
    }

    public void updateInformation(String code, int status, String token) {

        List<GSSStaff> gssStaffs = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType=1");

        if(gssStaffs!=null && gssStaffs.size()>0){
            GSSStaff gssStaff =gssStaffs.get(0);
            gssStaff.setStatus(status);
            gssStaff.setToken(token);
            gssStaff.setStaffType(Codes.FALCON_APP_CODE);
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

    public String getToken(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND staffType=1");

        if(list !=null && list.size()>0){
            return list.get(0).getToken();
        }else{
            return "";
        }
    }

    public String isCorrect(String code) {
        Object obj = HibernateUtil.getDBObjects("from CHO where territoryCode='"+code+"'");
        List<CHO> chos = (List<CHO>)obj;

        if(chos!=null && chos.size()>0){
            return chos.get(0).getTerritoryCode();
        }
        return "";
    }

    public JSONObject performSync(String code) {
        HSSync sync = new HSSync();
        return sync.performSync(code,"");
    }

    @Override
    public String getName(String code) {
        HSSyncDAO sync = new HSSyncDAO();
        CHO cho = sync.getStaff(code);
        return cho.getName();
    }

    public boolean logout(String code) {
        List<GSSStaff> gssStaffs= null;

        gssStaffs =(List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='" + code + "' AND staffType=1");

        if(gssStaffs!=null && gssStaffs.size()>0) {
            GSSStaff gssStaff = gssStaffs.get(0);
            gssStaff.setStatus(0);
            gssStaff.setToken("");
            gssStaff.setStaffType(1);
            HibernateUtil.saveOrUpdate(gssStaff);

            return true;
        }else{
            return false;
        }

    }

}
