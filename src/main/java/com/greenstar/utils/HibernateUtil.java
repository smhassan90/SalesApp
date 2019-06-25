package com.greenstar.utils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


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

    public static void saveOrUpdate(Object obj){
        Session session = null;
        Transaction tx =null;
        try {

            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
    }

    public static Object getDBObjects(String query){
        Session session = null;
        Object objects = null;
        try {

            session = getSessionFactory()
                    .openSession();
            objects = session.createQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static boolean saveObject(Object object){
        boolean isSuccessful = true;
        Session session = null;
        Transaction tx = null;
        try{
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(object);

        }catch (Exception e){
            LOG.error(e);
            isSuccessful = false;
        }finally {
            if(session.isOpen()){
                tx.commit();
                session.close();
            }
        }
        return isSuccessful;
    }

    public static String generateToken(String uuid) {
        String random = String.valueOf(System.currentTimeMillis())+uuid;
        return random;
    }


}
