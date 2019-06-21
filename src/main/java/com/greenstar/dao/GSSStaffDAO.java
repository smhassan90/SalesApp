package com.greenstar.dao;

import com.greenstar.entity.GSSStaff;
import com.greenstar.entity.SDStaff;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;


public class GSSStaffDAO implements IGSSStaffDatabaseDAO {
    final static Logger LOG = Logger.getLogger(GSSStaffDAO.class);
    public void insertRecord(String code, int status, String token) {
        GSSStaff gssStaff = new GSSStaff();
        SDStaff temp = new SDStaff();

        List<SDStaff> SDStaffs = (List<SDStaff>) HibernateUtil.getDBObjects("from SDStaff where staffCode='"+code+"'");
        //List<SDStaff> SDStaffs = session.createQuery("from SDStaff where staffCode='"+code+"'").list();

        if(SDStaffs!=null && SDStaffs.size()>0){
            temp =  SDStaffs.get(0);
        }
        gssStaff.setStaffCode(temp.getStaffCode());
        gssStaff.setStaffName(temp.getStaffName());
        gssStaff.setStatus(status);
        gssStaff.setToken(token);
        if(gssStaff!=null){
            HibernateUtil.saveOrUpdate(gssStaff);
        }

    }


    public boolean isExist(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"'");

        if( list!=null && list.size()==0)
            return false;
        else
            return true;
    }

    public void updateInformation(String code, int status, String token) {

        List<GSSStaff> gssStaffs = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"'");

        if(gssStaffs!=null && gssStaffs.size()>0){
            GSSStaff gssStaff =gssStaffs.get(0);
            gssStaff.setStatus(status);
            gssStaff.setToken(token);
            HibernateUtil.saveOrUpdate(gssStaff);
        }

    }

    public boolean isLoggedIn(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"' AND status=1");

        if(list !=null && list.size()==0)
            return false;
        else
            return true;
    }

    public String getToken(String code) {
        List<GSSStaff> list = (List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='"+code+"'");

        if(list !=null && list.size()>0){
            return list.get(0).getToken();
        }else{
            return "";
        }
    }

    public boolean logout(String code) {
        List<GSSStaff> gssStaffs= null;

        gssStaffs =(List<GSSStaff>)HibernateUtil.getDBObjects("from GSSStaff where staffCode='" + code + "'");

        if(gssStaffs!=null && gssStaffs.size()>0) {
            GSSStaff gssStaff = gssStaffs.get(0);
            gssStaff.setStatus(0);
            gssStaff.setToken("");

            HibernateUtil.saveOrUpdate(gssStaff);

            return true;
        }else{
            return false;
        }

    }

    public boolean isTokenValid(String code, String token){
        boolean isValid = false;
        List<GSSStaff> gssStaffs = null;


        try{
            gssStaffs = (List<GSSStaff>) HibernateUtil.getDBObjects("FROM GSSStaff WHERE staffCode='"+code+"' AND token ='"+token+"'");
        }catch (Exception e){
            LOG.error(e);
        }
        if(gssStaffs!=null && gssStaffs.size()>0){
            isValid = true;
        }
        return isValid;
    }
}
