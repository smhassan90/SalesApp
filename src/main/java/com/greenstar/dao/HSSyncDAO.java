package com.greenstar.dao;

import com.greenstar.dal.ApprovalQTVForm;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.entity.qtv.QTVForm;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 26/06/2019
 */
public class HSSyncDAO {
    final static Logger LOG = Logger.getLogger(HSSyncDAO.class);

    /*
        Get tagged providers to CHO
    */
    public List<Providers> getTaggedProviders(String code){
        List<Providers> providers = new ArrayList<Providers>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String queryString="SELECT P.CODE,p.name FROM HS_PROVIDERS P INNER JOIN HS_PROVIDER_CHO PC ON P.CODE = PC.PROVIDER_CODE WHERE PC.CHO_CODE = '"+code+"'";
            SQLQuery query = session.createSQLQuery(queryString)
                    .addEntity(Providers.class);
            providers = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return providers;
    }

    /*
        Get AM name
    */
    public String getAMName(String code){
        String queryString="SELECT A.NAME FROM HS_AM A INNER JOIN HS_AM_CHO AC ON ac.AM = A.CODE WHERE ac.cho = '"+code+"'";
        String name = HibernateUtil.getSingleString(queryString);

        return name;
    }

    /*
        Get AM code
    */
    public String getAMCode(String code){
        String queryString="SELECT A.CODE FROM HS_AM A INNER JOIN HS_AM_CHO AC ON ac.AM = A.CODE WHERE ac.cho = '"+code+"'";
        String AMCode = HibernateUtil.getSingleString(queryString);

        return AMCode;
    }

    /*
        Get Region
     */
    public String getRegion(String code){

        String regionQuery = "SELECT REGION FROM HS_REGION_CHO WHERE CHO_CODE='"+code+"'";
        String region = HibernateUtil.getSingleString(regionQuery);

        return region;
    }
    /*
    GetName
     */
    public String getStaffName(String code){
        return HibernateUtil.getSingleString("SELECT NAME FROM HS_CHO WHERE STAFF_CODE = '"+code+"'");
    }

    public List<ApprovalQTVForm> getApprovedQTVForms(String code){
        List<ApprovalQTVForm> qtvForms = new ArrayList<ApprovalQTVForm>();
        List<QTVForm> qtvForms1 = new ArrayList<QTVForm>();
        qtvForms1 = (List<QTVForm>) HibernateUtil.getDBObjects("FROM QTVForm WHERE choCode = '"+code+"'");
        if(qtvForms1!=null) {
            ApprovalQTVForm approvalQTVForm = new ApprovalQTVForm();
            for (QTVForm qtvForm : qtvForms1) {
                approvalQTVForm = new ApprovalQTVForm();
                approvalQTVForm.setApprovalStatus(qtvForm.getApprovalStatus());
                approvalQTVForm.setId(qtvForm.getId());
                approvalQTVForm.setProviderCode(qtvForm.getProviderCode());
                approvalQTVForm.setProviderName(qtvForm.getProviderName());
                approvalQTVForm.setStatus(qtvForm.getApprovalStatus());
                approvalQTVForm.setVisitDate(qtvForm.getVisitDate());

                qtvForms.add(approvalQTVForm);

            }
        }
        return qtvForms;

    }

    public void getDashboardData(String code){
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String queryString="SELECT (SELECT Count(*) FROM HS_PROVIDERS P INNER JOIN HS_PROVIDER_CHO PC ON P.CODE = PC.PROVIDER_CODE WHERE PC.CHO_CODE = '"+code+"'), ";
            SQLQuery query = session.createSQLQuery(queryString);

        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }
    }
}
