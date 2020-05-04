package com.greenstar.dao;

import com.greenstar.dal.ApprovalQATArea;
import com.greenstar.dal.ApprovalQATForm;
import com.greenstar.dal.ApprovalQATFormQuestion;
import com.greenstar.dal.ApprovalQTVForm;
import com.greenstar.entity.qat.*;
import com.greenstar.entity.qtv.CHO;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.entity.qtv.QTVForm;
import com.greenstar.entity.qtv.RegionCHO;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
            String queryString="SELECT P.CODE,p.name, p.status, p.donor FROM HS_PROVIDERS P INNER JOIN HS_PROVIDER_CHO PC ON P.CODE = PC.PROVIDER_CODE WHERE PC.TERRITORY_CODE = '"+code+"' AND P.STATUS=1";
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
        Get Questions
    */
    public List<Question> getQATQuestions(){
        List<Question> questions = new ArrayList<Question>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String queryString="SELECT * FROM QAT_QUESTIONS WHERE STATUS=1";
            SQLQuery query = session.createSQLQuery(queryString)
                    .addEntity(Question.class);
            questions = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return questions;
    }

    /*
        Get Areas
    */
    public List<Area> getQATAreas(){
        List<Area> areas = new ArrayList<Area>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String queryString="SELECT * FROM QAT_AREA WHERE STATUS=1";
            SQLQuery query = session.createSQLQuery(queryString)
                    .addEntity(Area.class);
            areas = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return areas;
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
        String region = "";
        List<RegionCHO> regionCHOS = new ArrayList<RegionCHO>();
        RegionCHO regionCHO = new RegionCHO();
        regionCHOS = (List<RegionCHO>) HibernateUtil.getDBObjects("FROM RegionCHO WHERE choCode = '"+code+"'");
        if(regionCHOS!=null && regionCHOS.size()>0){
            regionCHO = regionCHOS.get(0);
            region = regionCHO.getRegion();
        }
        return region;
    }
    /*
    Get CHO object
     */
    public CHO getStaff(String code){
        List<CHO> chos = new ArrayList<CHO>();
        chos = (List<CHO>)HibernateUtil.getDBObjects("FROM CHO WHERE territoryCode = '"+code+"'");
        return chos.get(0);
    }

    public List<ApprovalQTVForm> getApprovedQTVForms(String code){
        List<ApprovalQTVForm> qtvForms = new ArrayList<ApprovalQTVForm>();
        List<QTVForm> qtvForms1 = new ArrayList<QTVForm>();
        qtvForms1 = (List<QTVForm>) HibernateUtil.getDBObjects("FROM QTVForm WHERE choCode = '"+code+"' AND approvalStatus in (0,1,2)");
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

    public List<ApprovalQATForm> getApprovedQATForms(String code){
        List<ApprovalQATForm> qatForms = new ArrayList<ApprovalQATForm>();
        ArrayList<Object> objs = (ArrayList<Object>) HibernateUtil.getDBObjectsFromSQLQuery("SELECT * FROM qat_formheader WHERE dateofassessment in (" +
                "select max(dateofassessment) " +
                "from qat_formheader where providercode in (select providercode from hs_provider_cho where hs_provider_cho.territory_code='HO')" +
                "group by providercode)");

        for(Object obj : objs){
            ApprovalQATForm approvalQATForm = new ApprovalQATForm();
            Object[] objects = (Object[]) obj;
            approvalQATForm = new ApprovalQATForm();
            try {
                approvalQATForm.setApprovalStatus(Integer.valueOf(String.valueOf(objects[10])));
                approvalQATForm.setId(Long.valueOf(String.valueOf(objects[0])));
                approvalQATForm.setProviderCode(String.valueOf(objects[6]));
                approvalQATForm.setDateOfAssessment(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(objects[5])));
                qatForms.add(approvalQATForm);
            }catch(Exception e ){
                LOG.error(e);
            }
        }
        return qatForms;

    }

    public List<ApprovalQATFormQuestion> getApprovedQATQuestions(List<Long> qatFormIds){
        List<ApprovalQATFormQuestion> approvalQATFormQuestions = new ArrayList<ApprovalQATFormQuestion>();
        List<QATFormQuestion> qatFormQuestionList = new ArrayList<QATFormQuestion>();
        qatFormQuestionList = (List<QATFormQuestion>) HibernateUtil.getDBObjectsQueryParameter("FROM QATFormQuestion WHERE formId in :qatFormIds","qatFormIds",qatFormIds);
        if(qatFormQuestionList!=null) {
            ApprovalQATFormQuestion approvalQATFormQuestion = new ApprovalQATFormQuestion();
            for (QATFormQuestion qatFormQuestion : qatFormQuestionList) {
                approvalQATFormQuestion = new ApprovalQATFormQuestion();
                approvalQATFormQuestion.setAnswer(qatFormQuestion.getAnswer());
                approvalQATFormQuestion.setId(qatFormQuestion.getId());
                approvalQATFormQuestion.setQuestionId(qatFormQuestion.getQuestionId());
                approvalQATFormQuestion.setAreaId(qatFormQuestion.getAreaId());
                approvalQATFormQuestion.setFormId(qatFormQuestion.getFormId());

                approvalQATFormQuestions.add(approvalQATFormQuestion);
            }
        }
        return approvalQATFormQuestions;

    }

    public List<ApprovalQATArea> getApprovalQATAreas(List<Long> qatFormIds){
        List<ApprovalQATArea> approvalQATAreas = new ArrayList<ApprovalQATArea>();
        List<QATAreaDetail> qatAreaDetails = new ArrayList<QATAreaDetail>();
        String queryString = "FROM QATAreaDetail WHERE formId in :qatFormIds";
        qatAreaDetails = (List<QATAreaDetail>) HibernateUtil.getDBObjectsQueryParameter(queryString,"qatFormIds",qatFormIds);
        if(qatAreaDetails!=null) {
            ApprovalQATArea approvalQATArea = new ApprovalQATArea();
            for (QATAreaDetail qatAreaDetail : qatAreaDetails) {
                approvalQATArea = new ApprovalQATArea();
                approvalQATArea.setFormId(qatAreaDetail.getFormId());
                approvalQATArea.setId(qatAreaDetail.getId());
                approvalQATArea.setAreaId(qatAreaDetail.getAreaId());
                approvalQATArea.setComments(qatAreaDetail.getComments());
                approvalQATArea.setTotalCriticalIndicators(qatAreaDetail.getTotalCriticalIndicators());
                approvalQATArea.setTotalCriticalIndicatorsAchieved(qatAreaDetail.getTotalCriticalIndicatorsAchieved());
                approvalQATArea.setTotalIndicators(qatAreaDetail.getTotalIndicators());
                approvalQATArea.setTotalIndicatorsAchieved(qatAreaDetail.getTotalIndicatorsAchieved());
                approvalQATArea.setTotalNonCriticalIndicators(qatAreaDetail.getTotalNonCriticalIndicators());
                approvalQATArea.setTotalNonCriticalIndicatorsAchieved(qatAreaDetail.getTotalNonCriticalIndicatorsAchieved());

                approvalQATAreas.add(approvalQATArea);
            }
        }
        return approvalQATAreas;

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
