package com.greenstar.dao;

import com.greenstar.entity.GSMBirthday;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.List;

public class GSMBirthdayDAO implements IGSMBirthdayDAO{
    final static Logger LOG = Logger.getLogger(GSMBirthdayDAO.class);
    private Session session = null;
    private List<GSMBirthday> gsmBirthdays = null;

    public List<GSMBirthday> list() {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            gsmBirthdays = session.createQuery("from GSMBirthday").list();
        }catch (Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return gsmBirthdays;
    }
}