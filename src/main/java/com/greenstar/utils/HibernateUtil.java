package com.greenstar.utils;

import com.greenstar.controller.greensales.Codes;
import com.greenstar.entity.qat.QATAreaDetail;
import com.greenstar.entity.qtv.IDMANAGER;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
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

    public static boolean saveOrUpdate(Object obj){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return isSuccessful;
    }

    public static Object getDBObjectsQueryParameter(String queryString, String paramName, List<Long> param){
        Session session = null;
        Object objects = null;

        try {

            session = getSessionFactory()
                    .openSession();
            Query query = session.createQuery(queryString);
            query.setParameterList(paramName,param);
            objects = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
    }

    public static ArrayList<Object> getDBObjectsFromSQLQuery(String query){
        Session session = null;
        ArrayList<Object> objects = new ArrayList<>();
        try {

            session = getSessionFactory()
                    .openSession();
            objects = (ArrayList<Object>) session.createSQLQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return objects;
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

    /*
    This method will return count number.
     */
    public static int getRecordCount(String queryString){
        Session session = null;
        String result = "";
        Integer count = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            count = ((Long)session.createQuery(queryString).uniqueResult()).intValue();

        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return (int) count;
    }

    /*
    This method will return single cell on provided Query.
     */
    public static String getSingleString(String queryString){
        Session session = null;
        String result = "";
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery(queryString);
            Object cell = query.uniqueResult();
            if(cell!=null){
                result = cell.toString();
            }

        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return result;
    }

    public static long getNextBaseID(int appNumber){
        ArrayList<IDMANAGER> idManagers = (ArrayList<IDMANAGER>) getDBObjects("from IDMANAGER");
        IDMANAGER idManager = idManagers.get(0);
        long lastID = 0;
        if(appNumber== Codes.FALCON_APP_CODE){
            lastID = idManager.getLastID()+50000;
            idManager.setLastID(lastID);
        }else if(appNumber==Codes.DTC_APP_CODE){
            lastID = idManager.getDTClastID()+50000;
            idManager.setDTClastID(lastID);
        }else if(appNumber==Codes.MECWHEEL_APP_CODE){
            lastID = idManager.getCRBlastID()+50000;
            idManager.setCRBlastID(lastID);
        }

        idManager.setId(1);
        HibernateUtil.saveOrUpdate(idManager);
        return lastID;
    }

    public static void insertDTCImg(String queryString){
        Session session = null;
        String result = "";
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery(queryString);
            query.executeUpdate();
            int v= 0;

        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }
    }

    public static boolean saveOrUpdateList(List<? extends Object> objs){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            for (Object obj : objs){

                session.saveOrUpdate(obj);
            }

            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return isSuccessful;
    }
}