package com.greenstar.dao;

import com.greenstar.entity.SDStaff;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class StaffDAO implements IStaffVerificationDAO {
    final static Logger LOG = Logger.getLogger(StaffDAO.class);
    private SessionFactory sessionFactory;
    private Session session = null;
    List<SDStaff> staffs =null;

    /*
    This method is to check if this user code exist in Database
     */
    public boolean isCorrect(String code) {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            staffs = session.createQuery("from SDStaff where staffCode='"+code+"'").list();
        }catch (Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }

        if(staffs!=null && staffs.size()>0){
            return true;
        }
        return false;
    }


}
