package com.greenstar.controller.hs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.HSSyncDAO;
import com.greenstar.entity.qat.*;
import com.greenstar.entity.qtv.CHO;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.entity.qtv.QTVForm;
import com.greenstar.utils.HibernateUtil;
import com.greenstar.utils.LogToFile;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */

@Controller
public class HSSync {
    boolean isSuccessfulQAT =false;
    final static Logger LOG = Logger.getLogger(HSSync.class);
    Gson gson = new GsonBuilder().setDateFormat("MM/dd/yy").create();
    List<Integer> succesfulIDs = new ArrayList<Integer>();
    List<Integer> rejectedIDs = new ArrayList<Integer>();

    List<Long> QATIDs = new ArrayList<Long>();
    List<Long> SuccessfullQATIDs = new ArrayList<Long>();
    List<Long> RejectedQATIDs = new ArrayList<Long>();

    List<Long> emptyIDs = new ArrayList<Long>();

    final int closingDay = 9;
    final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    protected static final Logger logger=Logger.getLogger("FalconLog");

    String reason="";

    @RequestMapping(value = "/readFromFile", method = RequestMethod.GET,params={"staffCode"})
    @ResponseBody
    public String readFromFile(String staffCode){
        return performSync(staffCode,getDataFromFile()).toString();
    }

    @RequestMapping(value = "/singleFormSync", method = RequestMethod.GET,params={"data","token","syncType"})
    @ResponseBody
    public String singleFormSync(String data, String token, String syncType){

        long startCurrentMilis = Calendar.getInstance().getTimeInMillis();
        GSSStaffDAO gssStaffDAO = new GSSStaffDAO();
        JSONObject response = new JSONObject();
        String staffCode  = gssStaffDAO.isTokenValid(token);

        String result = "";
        if(syncType.equals(Codes.SINGLE_QAT_FORM)){
            if(isQATAM(staffCode)){
                syncType = Codes.SINGLE_QAT_FORM_AM;
            }else{
                syncType = Codes.SINGLE_QAT_FORM;
            }

        }
        if(!"".equals(staffCode)){
            result = performSingleFormSync(data,syncType).toString();
        }else{
            response.put("message", "Invalid Token, you might be logged in from another device");
            response.put("status", Codes.INVALID_TOKEN);
            response.put("data","");
            result = response.toString();
        }

        long endCurrentMilis = Calendar.getInstance().getTimeInMillis();
        long timeTaken = endCurrentMilis - startCurrentMilis;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeTaken);
        LogToFile.log(null,"info", "Time taken to single form sync staffCode : "+ staffCode+" is "+seconds+"Seconds. ");
        return result;
    }

    private boolean isQATAM(String staffCode) {
        boolean isAM = false;
        ArrayList<CHO> chos = new ArrayList<CHO>();
        chos = (ArrayList<CHO>) HibernateUtil.getDBObjects("FROM CHO WHERE territoryCode='"+staffCode+"'");
        CHO cho = chos!=null&& chos.size()>0?chos.get(0):new CHO();
        if(cho.getIsQATAMAllowed()==1){
            isAM = true;
        }
        return isAM;
    }

    @RequestMapping(value = "/hssync", method = RequestMethod.GET,params={"data","token"})
    @ResponseBody
    public String index(String data, String token){
        long startCurrentMilis = Calendar.getInstance().getTimeInMillis();
        GSSStaffDAO gssStaffDAO = new GSSStaffDAO();
        JSONObject response = new JSONObject();
        String staffCode  = gssStaffDAO.isTokenValid(token);

        String result = "";
        if(!"".equals(staffCode)){
            result = performSync(staffCode,data).toString();
        }else{
            response.put("message", "Invalid Token, you might be logged in from another device");
            response.put("status", Codes.INVALID_TOKEN);
            response.put("data","");
            result = response.toString();
        }
        long endCurrentMilis = Calendar.getInstance().getTimeInMillis();
        long timeTaken = endCurrentMilis - startCurrentMilis;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeTaken);
        LogToFile.log(null,"info", "Time taken to sync staffCode : "+ staffCode+" is "+seconds+"Seconds");
        return result;

    }

    private boolean isQATFormValid(QATFormHeader form){
        boolean isValid = false;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int type = form.getType();

        Calendar cal = Calendar.getInstance();
        cal.setTime(form.getDateOfAssessment());
        int visitDateMonth = cal.get(Calendar.MONTH)+1;
        if(month==13){
            month=1;
        }
        if(month==visitDateMonth || (month==visitDateMonth+1 && day<=closingDay)){
            int count = HibernateUtil.getRecordCountNew("select count(*) from QATFormHeader WHERE type="+type+" and providerCode='"+form.getProviderCode()+"' AND approvalStatus IN (1,0) AND  YEAR(dateOfAssessment)="+year+" AND MONTH(dateOfAssessment)="+visitDateMonth);
            if(count==0){
                isValid = true;
            }
        }

        return isValid;
    }

    private boolean isActiveProvider(String providerCode){
        boolean isActiveProvider = false;

        ArrayList<Providers> providers = new ArrayList<>();

        if(providerCode!=null && !"".equals(providerCode) ){
            providers = (ArrayList<Providers>) HibernateUtil.getDBObjects("FROM Providers WHERE code ='"+providerCode+"' and status='1'");
        }

        if(providers!=null &&  providers.size()>0){
            isActiveProvider = true;
        }

        return isActiveProvider;
    }

    private boolean formIsValid(QTVForm form){
        boolean isValid = false;
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String reportingMonth = monthNames[month-1] + ","+ Calendar.getInstance().get(Calendar.YEAR);
        Calendar cal = Calendar.getInstance();
        cal.setTime(form.getVisitDate());
        int visitDateMonth = cal.get(Calendar.MONTH)+1;
        String visitDateReportingMonth = monthNames[visitDateMonth-1] + ","+cal.get(Calendar.YEAR);
        boolean isActiveProvider = isActiveProvider(form.getProviderCode());
        String monthToInsert = "";
        reason = "";
        if((month==visitDateMonth || (month==visitDateMonth+1 && day<=closingDay)) && isActiveProvider){
            String query = "select count(*) from QTVForm WHERE providerCode='"+form.getProviderCode()+"' AND approvalStatus IN (1,0) AND reportingMonth='"+visitDateReportingMonth+"'";
            int count = HibernateUtil.getRecordCount(query);
            if(count==0){
                isValid = true;
                reason = Codes.REASON_ACCEPTED;
            }else{
                reason = Codes.REASON_DUPLICATE_PROVIDERS ;
            }
        }else if(month>visitDateMonth){
            reason=Codes.REASON_AFTER_DUE;
        }else if(month < visitDateMonth ) {
            reason=Codes.REASON_FWD_MONTH;
        }else if(month < visitDateMonth ) {
            reason=Codes.REASON_FWD_MONTH;
        }else if(!isActiveProvider ) {
            reason=Codes.REASON_CLOSED_PROVIDERS;
        }else{
            reason="month:"+month+",visitDateMonth:"+visitDateMonth+",isActiveProvider:"+isActiveProvider+",providercode:"+form.getProviderCode();
        }

        return isValid;
    }
    public JSONObject performSingleFormSync(String data, String syncType){
        SyncObjectHS syncObjectHS = null;
        boolean isSyncProper = false;
        if(!"".equals(data)) {
            syncObjectHS = gson.fromJson(data, SyncObjectHS.class);
            if (syncType.equals(Codes.SINGLE_QAT_FORM) ||
                    syncType.equals(Codes.SINGLE_QAT_FORM_AM)) {
                int numberOfAreas = 0;
                int numberOfQuestions = 0;
                int type = 0;

                if(syncType.equals(Codes.SINGLE_QAT_FORM_AM)){
                    type = Codes.QAT_FOR_AM;
                }else{
                    type = Codes.QAT_FOR_QAM;
                }
                numberOfQuestions= HibernateUtil.getRecordCount("select count(*) from Question WHERE type = "+Codes.QAT_FOR_BOTH+" OR type="+type);
                numberOfAreas = HibernateUtil.getRecordCount("select count(*) from Area WHERE type = "+Codes.QAT_FOR_BOTH+" OR type="+type);

                isSyncProper=  syncQAT(syncObjectHS, numberOfAreas, numberOfQuestions, type);
            }else if(syncType.equals(Codes.SINGLE_QTV_FORM)){
                isSyncProper=  syncQTVForm(syncObjectHS.getQtvForms());
            }
        }
        JSONObject response = new JSONObject();
        if(isSyncProper){
            response.put("message", "Synced successful");
            response.put("status", Codes.ALL_OK);
            if(Codes.SINGLE_QAT_FORM.equals(syncType) || Codes.SINGLE_QAT_FORM_AM.equals(syncType)){
                if(SuccessfullQATIDs.size()>0)
                    response.put("qatSuccessfulId",SuccessfullQATIDs.get(0));

                if(RejectedQATIDs.size()>0)
                    response.put("qatRejectedId",RejectedQATIDs.get(0));



            }else if(Codes.SINGLE_QTV_FORM.equals(syncType)){
                if(succesfulIDs.size()>0)
                    response.put("SuccessfulQTVID",succesfulIDs.get(0));
                else{
                    response.put("SuccessfulQTVID",0);
                }
                if(rejectedIDs.size()>0){
                    response.put("RejectedQTVID",rejectedIDs.get(0));
                }else{
                    response.put("RejectedQTVID",0);
                }
            }

        }else{
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }

    public JSONObject performSync(String code, String data){
        printObject(data);
        String statusCode = Codes.ALL_OK;
        String message = "Successfully synced";
        JSONObject response = new JSONObject();
        HSData dataSync = new HSData();
        boolean isSuccessful = false;

       SyncObjectHS syncObjectHS = null;

       if(!"".equals(data)){
           syncObjectHS = gson.fromJson(data, SyncObjectHS.class);

           List<QTVForm> forms = syncObjectHS.getQtvForms();

           syncQTVForm(forms);
       }

        List<Providers> providers = null;
        List<Question> questions = new ArrayList<>();
        List<Area> areas = new ArrayList<>();

        HSSyncDAO sync = new HSSyncDAO();
        String AMName = "";
        String region = "";
        String staffName = "";
        String AMCode = "";
        List<ApprovalQTVForm> qtvForms = new ArrayList<ApprovalQTVForm>();

        List<ApprovalQATForm> approvalQATForms = new ArrayList<ApprovalQATForm>();
        List<ApprovalQATFormQuestion> approvalQATFormQuestions = new ArrayList<ApprovalQATFormQuestion>();
        List<ApprovalQATArea> approvalQATAreas = new ArrayList<ApprovalQATArea>();
        List<QATTCForm> qattcFormsApproved = new ArrayList<QATTCForm>();

        int countTotalProviders = 0;
        int countApprovedQTV = 0;
        int countRejectedQTV = 0;
        int countPendingQTV = 0;
        int isQTVAllowed = 0;
        int isQATAllowed = 0;
        int isQATAMAllowed = 0;
        CHO cho = new CHO();

        try {

            providers = sync.getTaggedProviders(code);
            AMName = sync.getAMName(code);
            AMCode = sync.getAMCode(code);
            region = sync.getRegion(code);
            cho = sync.getStaff(code);
            isQTVAllowed = cho.getIsQTVAllowed();
            isQATAllowed = cho.getIsQATAllowed();
            isQATAMAllowed = cho.getIsQATAMAllowed();
            staffName = cho.getName();
            String reportingMonth = getReportingMonth(new Date(System.currentTimeMillis()));
            qtvForms = sync.getApprovedQTVForms(code, reportingMonth);
            int type = 0;

            if(cho.getIsQATAMAllowed()==1){
                type = Codes.QAT_FOR_AM;
            }else{
                type = Codes.QAT_FOR_QAM;
            }

            //QAT Related stuff
            if(isQATAllowed==1) {

                if(syncObjectHS!=null){
                    int numberOfAreas = 0;
                    int numberOfQuestions = 0;

                    numberOfQuestions= HibernateUtil.getRecordCount("select count(*) from Question WHERE type = "+Codes.QAT_FOR_BOTH+" OR type="+type);
                    numberOfAreas = HibernateUtil.getRecordCount("select count(*) from Area WHERE type = "+Codes.QAT_FOR_BOTH+" OR type="+type);

                    isSuccessfulQAT = syncQAT(syncObjectHS, numberOfAreas, numberOfQuestions, type);
                }

                approvalQATForms = sync.getApprovedQATForms(code);
                List<Long> QATIds = new ArrayList<>();
                if(approvalQATForms!=null && approvalQATForms.size()>0 ) {
                    for (ApprovalQATForm form : approvalQATForms)
                        QATIds.add(form.getId());
                }
                if(QATIds.size()>0){
                    approvalQATFormQuestions = sync.getApprovedQATQuestions(QATIds);
                    approvalQATAreas = sync.getApprovalQATAreas(QATIds);
                }

                qattcFormsApproved = sync.getApprovedQATTCForms(code);

                questions = sync.getQATQuestions(type);
                areas = sync.getQATAreas(type);
            }
            //END

            for(ApprovalQTVForm form : qtvForms){
                int statusForm = form.getApprovalStatus();
                switch (statusForm){
                    case 0 :
                        countPendingQTV++;
                        break;
                    case 1 :
                        countApprovedQTV++;
                        break;
                    case 2 :
                        countRejectedQTV++;
                }
            }
            HashMap<String,Integer> dashboardParams = new HashMap<String, Integer>();
            dashboardParams.put("countPendingQTV",countPendingQTV);
            dashboardParams.put("countApprovedQTV",countApprovedQTV);
            dashboardParams.put("countRejectedQTV",countRejectedQTV);
            dashboardParams.put("countTotalProviders",providers.size());

            Dashboard dashboard = new Dashboard();
            dashboard = createDashboard(dashboardParams);

            dataSync.setAMName(AMName);
            dataSync.setAMCode(AMCode);
            dataSync.setProviders(providers);
            dataSync.setRegion(region);
            dataSync.setName(staffName);
            dataSync.setQtvForms(qtvForms);
            dataSync.setQattcForms(qattcFormsApproved);
            dataSync.setIsQATAMAllowed(isQATAMAllowed);
            dataSync.setApprovalQATAreas(approvalQATAreas);
            dataSync.setApprovalQATFormQuestions(approvalQATFormQuestions);
            dataSync.setApprovalQATForms(approvalQATForms);
            dataSync.setDashboard(dashboard);
            dataSync.setQuestions(questions);
            dataSync.setAreas(areas);
            dataSync.setIsQATAllowed(isQATAllowed);
            dataSync.setIsQTVAllowed(isQTVAllowed);

            response.put("message", message);
            response.put("status", statusCode);
            response.put("staffName",staffName);
            response.put("data", gson.toJson(dataSync));
            response.put("successfulIDs",succesfulIDs);
            response.put("rejectedIDs",rejectedIDs);
            if(isSuccessfulQAT){
                response.put("successfulQATIDs",SuccessfullQATIDs);
                response.put("rejectedQATIDs",emptyIDs);
            }else{
                response.put("successfulQATIDs",emptyIDs);
                response.put("rejectedQATIDs",QATIDs);
            }
        }catch(Exception e){
            response.put("message", "Something went wrong");
            response.put("status", Codes.SOMETHING_WENT_WRONG);
            response.put("data","");
        }

        return response;
    }

    private String getProviderDonor(String providerCode) {
        String providerDonor="";
        ArrayList<Providers> providers = new ArrayList<>();

        if(providerCode!=null && !"".equals(providerCode) ){
            providers = (ArrayList<Providers>) HibernateUtil.getDBObjects("FROM Providers WHERE code ='"+providerCode+"'");
        }

        if(providers!=null &&  providers.size()>0){
            providerDonor = providers.get(0).getDonor();
        }
        return providerDonor;
    }

    private boolean isSameFormExist(int id) {
        boolean isSameFormExist = false;

        int count = HibernateUtil.getRecordCount("select count(*) from QTVForm WHERE ID="+id );
        if(count!=0){
            isSameFormExist = true;
        }

        return isSameFormExist;
    }

    public String getReportingMonth(Date visitDate) {
        String reportingMonth = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(visitDate);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        String name = monthNames[month];
        reportingMonth=name+","+String.valueOf(year);
        return reportingMonth;
    }

    private Dashboard createDashboard(HashMap<String, Integer> dashboardParams) {

        int countApprovedQTV = dashboardParams.get("countApprovedQTV");
        int countRejectedQTV = dashboardParams.get("countRejectedQTV");
        int countPendingQTV = dashboardParams.get("countPendingQTV");
        int countTotalProviders = dashboardParams.get("countTotalProviders");

        String html = "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "#customers {\n" +
                "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "#customers td, #customers th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "#customers tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "#customers th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #4CAF50;\n" +
                "  color: white;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<table id=\"customers\">\n" +
                "  <tr>\n" +
                "    <th> </th>\n" +
                "    <th>Total</th>\n" +
                "    <th>Percentage</th>\n" +
                "  </tr>";
            html += "<tr>\n" +
                    "    <td>Total Providers</td>\n" +
                    "    <td>"+countTotalProviders+"</td>\n" +
                    "\t<td></td>\n" +
                    "  </tr>";
            int multiply = countPendingQTV*100;
        html+="<tr>\n" +
                "    <td><b>Pending QTV</b></td>\n" +
                "    <td><b>"+countPendingQTV+"</b></td>\n" +
                "    <td><b>"+(float)multiply/countTotalProviders+"%</b></td>\n" +
                "  </tr>";
        multiply = countRejectedQTV*100;
        html+="<tr>\n" +
                "    <td><b>Rejected QTV</b></td>\n" +
                "    <td><b>"+countRejectedQTV+"</b></td>\n" +
                "    <td><b>"+(float)multiply/countTotalProviders+"%</b></td>\n" +
                "  </tr>";
        multiply = countApprovedQTV*100;
        html+="<tr>\n" +
                "    <td><b>Approved QTV</b></td>\n" +
                "    <td><b>"+countApprovedQTV+"</b></td>\n" +
                "    <td><b>"+(float)multiply/countTotalProviders+"%</b></td>\n" +
                "  </tr>";
        html+="</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        Dashboard dashboard = new Dashboard();
        dashboard.setHtml(html);
        dashboard.setId(1);
        return dashboard;
    }

    private boolean syncQTVForm(List<QTVForm> forms){
        boolean isSuccessful = true;
        succesfulIDs.clear();
        rejectedIDs.clear();
        boolean isValidForm =  false;
        List<QTVForm> qtvFormsToSave = new ArrayList<>();

        try {

            if (forms != null && forms.size() > 0) {
                for (QTVForm form : forms) {
                    isValidForm = formIsValid(form);
                    form.setProviderDonor(getProviderDonor(form.getProviderCode()));
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    form.setReason(reason);
                    if (isSameFormExist(form.getId())) {
                        succesfulIDs.add(form.getId());
                    } else {
                        if (!isValidForm) {
                            form.setApprovalStatus(Codes.REJECTEDFORMSBYSYSTEM);
                            rejectedIDs.add(form.getId());
                        }
                        isSuccessful = HibernateUtil.saveOrUpdate(form);
                        if(!isSuccessful){
                            break;
                        }
                    }
                }
                if (qtvFormsToSave != null && qtvFormsToSave.size() > 0) {
                    isSuccessful = HibernateUtil.saveOrUpdateList(qtvFormsToSave);
                }
            }
        }catch(Exception e){
            isSuccessful = false;
        }
        return isSuccessful;
    }

    private boolean syncQAT(SyncObjectHS syncObjectHS, int numberOfAreas, int numberOfQuestions, int type){

        SuccessfullQATIDs.clear();
        RejectedQATIDs.clear();
        SuccessfullQATIDs = new ArrayList<>();
        boolean isSuccessfulQAT = false;
        List<QATAreaDetail> qatAreaDetails = syncObjectHS.getQatAreaDetails();
        List<QATFormHeader> qatFormHeaders = syncObjectHS.getQatFormHeaders();
        List<QATFormQuestion> qatFormQuestions = syncObjectHS.getQatFormQuestions();
        List<QATTCForm> qattcForms = syncObjectHS.getQattcForms();

        if(qattcForms!=null && qattcForms.size()>0){
            for(QATTCForm qattcForm: qattcForms){
                qattcForm.setApprovalStatus(1);
            }
        }

        if(qattcForms!=null && qattcForms.size()>0)
            HibernateUtil.saveOrUpdateListNew(qattcForms);

        boolean isNewQAT = false;
        boolean isInsertSuccessful;
        boolean isValidQATForm = false;
        // QAT qatFormHeaders
        if(qatFormHeaders!=null && qatFormHeaders.size()>0){
            for(QATFormHeader obj : qatFormHeaders) {
                isInsertSuccessful = false;
                obj.setReportingMonth(getReportingMonth(obj.getDateOfAssessment()));
                obj.setProviderDonor(getProviderDonor(obj.getProviderCode()));
                obj.setType(type);
                isValidQATForm = isQATFormValid(obj);
                if(!isValidQATForm)
                    obj.setApprovalStatus(20);
                else
                    obj.setApprovalStatus(0);


                ArrayList<Object> objs =  HibernateUtil.getDBObjectsFromSQLQueryNew("SELECT * FROM qat_formheader where id='"+obj.getId()+"'");
                if(objs.size()>0){
                    isNewQAT=false;
                    isInsertSuccessful = true;
                }else{
                    isNewQAT = true;
                    isInsertSuccessful = HibernateUtil.saveObjectNew(obj);
                }
                if(isInsertSuccessful){
                    if(isValidQATForm)
                        SuccessfullQATIDs.add(obj.getId());
                    else{
                        RejectedQATIDs.add(obj.getId());
                    }
                    isSuccessfulQAT = true;
                }

                if(isNewQAT){
                    boolean isInsertQATSuccessful = false;
                    QATIDs.add(obj.getId());
                    isInsertQATSuccessful = insertQATDetails(qatAreaDetails,obj.getId(), numberOfAreas);
                    if(isInsertQATSuccessful){
                        isInsertQATSuccessful = false;
                        isInsertQATSuccessful = insertQATQuestions(qatFormQuestions, obj.getId(),numberOfQuestions);
                    }

                    if(!isInsertQATSuccessful){
                        rollBackQATForm(obj.getId());
                        isSuccessfulQAT = false;
                        break;
                    }
                }
                isNewQAT = false;
            }
        }

        //END

        printObject(new Gson().toJson(syncObjectHS));

        return isSuccessfulQAT;
    }

    private void rollBackQATForm(long formId) {
        deleteFormHeader(formId);
        deleteAreas(formId);
        deleteQuestions(formId);
    }

    private boolean insertQATDetails(List<QATAreaDetail> qatAreaDetails, long formId,
                                     int numberOfAreas) {
        boolean isSuccessfulQATAreaDetailInsert = false;
        List<QATAreaDetail> toSaveQATAreaDetail = new ArrayList<>();
        // QAT Area Detail
        boolean shouldInsert = false;
        if(formId >0 ){
            if(qatAreaDetails!=null && qatAreaDetails.size()>0){
                shouldInsert = shouldInsertArea(qatAreaDetails,formId,numberOfAreas);
                if(shouldInsert) {
                    for (QATAreaDetail temp : qatAreaDetails) {
                        if (formId == temp.getFormId()) {
                            temp.setId(0);
                            toSaveQATAreaDetail.add(temp);
                        }
                    }


                    isSuccessfulQATAreaDetailInsert = HibernateUtil.saveOrUpdateListNew(toSaveQATAreaDetail);
                }else{
                    isSuccessfulQATAreaDetailInsert = true;
                }
            }
        }
        return isSuccessfulQATAreaDetailInsert;
    }

    private boolean insertQATQuestions(List<QATFormQuestion> qatFormQuestions, long formId,
                                     int numberOfQuestions) {
        boolean isSuccessfulQATQuestionsInsert = false;

        // QAT qatFormQuestions
        boolean shouldInsert = false;
        if(formId!=0){
            List<QATFormQuestion> toSaveQATFormQuestion = new ArrayList<>();

            if( qatFormQuestions!=null && qatFormQuestions.size()==numberOfQuestions){
                shouldInsert = shouldInsertQuestion(qatFormQuestions, formId, numberOfQuestions);
                if(shouldInsert){
                    for(QATFormQuestion temp : qatFormQuestions){
                        if(formId == temp.getFormId()){
                            temp.setId(0);
                            toSaveQATFormQuestion.add(temp);
                        }
                    }

                    isSuccessfulQATQuestionsInsert = HibernateUtil.saveOrUpdateListNew(toSaveQATFormQuestion);
                }
            }
        }

        //END

        return isSuccessfulQATQuestionsInsert;
    }

    private boolean shouldInsertArea(List<QATAreaDetail> qatAreaDetails, long formId, int areaSize ){
        boolean shouldInsert = false;

        String query = "FROM QATAreaDetail where formId = "+ formId;

        List<QATAreaDetail> qatAreaDetailsObjs = (List<QATAreaDetail>) HibernateUtil.getDBObjects(query);

        if (areaSize!=qatAreaDetailsObjs.size()){
            shouldInsert=true;
            if(qatAreaDetailsObjs.size()>0){
                deleteAreas(formId);
            }
        }

        return shouldInsert;
    }

    private boolean deleteAreas(long formId) {
        return HibernateUtil.executeQueryNew("DELETE FROM QAT_AreaDetail where formId = "+formId);
    }

    private boolean deleteQuestions(long formId) {
        return HibernateUtil.executeQueryNew("DELETE FROM QAT_FormQuestion where formId = "+formId);
    }

    private boolean deleteFormHeader(long formId) {
        return HibernateUtil.executeQueryNew("DELETE FROM QAT_FormHeader where id = "+formId);
    }

    private boolean shouldInsertQuestion(List<QATFormQuestion> qatFormQuestions, long formId, int questionSize ){
        boolean shouldInsert = false;

        String query = "FROM QATFormQuestion where formId = "+ formId;

        List<QATFormQuestion> qatFormQuestionList = (List<QATFormQuestion>) HibernateUtil.getDBObjects(query);

        if (questionSize!=qatFormQuestionList.size()){
            shouldInsert=true;
            if(qatFormQuestionList.size()>0){
                deleteQuestions(formId);
            }

        }

        return shouldInsert;
    }

    private void printObject(String data){
        try {
            FileWriter myWriter = new FileWriter("syncText.txt",true);
            myWriter.append(data);
            myWriter.append("\n \n \n SyncDate : "+ Calendar.getInstance().getTimeInMillis());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private String getDataFromFile(){
        BufferedReader br = null;
        String everything = "";
        try {
            File file = new File("C:\\file.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader("C:\\file.txt"));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
             everything = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return everything;
    }
}
