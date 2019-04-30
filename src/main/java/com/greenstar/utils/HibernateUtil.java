package com.greenstar.utils;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    final static Logger LOG = Logger.getLogger(HibernateUtil.class);

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }catch(Exception e){
                LOG.error(e);
            }

        }
        return sessionFactory;
    }


}
