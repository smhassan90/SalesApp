package com.greenstar.controller.eagle;

import com.google.gson.*;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.dao.CRBSyncDAO;
import com.greenstar.dao.GSSStaffDAO;
import com.greenstar.dao.HSSyncDAO;
import com.greenstar.entity.eagle.*;
import com.greenstar.entity.eagle.Areas;
import com.greenstar.entity.qtv.CHO;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.utils.HibernateUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Syed Muhammad Hassan
 * 13th October, 2021
 */

@Controller
public class EaglePartialSync {

    //Gson gson = new GsonBuilder().setDateFormat("MM/dd/yy").create();
    HSSyncDAO hsSyncDAO = new HSSyncDAO();
    final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    private void writeInFile(String data){
        try {
            FileWriter myWriter = new FileWriter("c:\\log\\syncText.txt",true);
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
            File file = new File("c:\\log\\filename.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader("c:\\log\\filename.txt"));
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
    @RequestMapping(value = "/PSEagleBasicInfo", method = RequestMethod.GET,params={"token","PSType", "data", "version"})
    @ResponseBody
    public String PSBasicInfo(String token,String PSType, String data, String version){
        writeInFile("Token : "+token + " version : "+version + " Data : "+data);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            DateFormat df = new SimpleDateFormat("MM/dd/yy");
            @Override
            public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
                try {
                    java.util.Date utilDate = new java.util.Date();
                    utilDate = df.parse(json.getAsString());
                    return  new java.sql.Date(utilDate.getTime());
                } catch (ParseException e) {
                    return null;
                }
            }
        });
        Gson gson = gsonBuilder.create();
        EagleData dataObj = new EagleData();
        JSONObject response = new JSONObject();
        String message = "";
        String status = "";
        String code = "";
        String staffName = "";
        GSSStaffDAO staffDAO = new GSSStaffDAO();
        boolean isSuccessfulPS = false;
        if(version.equals(Codes.VERSIONEAGLE)) {
            code = staffDAO.isTokenValid(token);
            CHO cho = null;
            if (!code.equals("")) {
                try {
                    ArrayList<CHO> chos = new ArrayList<CHO>();
                    chos = (ArrayList<CHO>) HibernateUtil.getDBObjects("FROM CHO WHERE territoryCode='" + code + "'");
                    if (chos != null && chos.size() == 1) {
                        cho = chos.get(0);
                    }

                    if (cho != null) {

                        if (PSType.equals(Codes.PS_EAGLE_TYPE_BASIC_INFO)) {
                            dataObj = syncBasicInfo(cho);
                            dataObj = syncQuestions(cho, dataObj);
                            if(dataObj!=null){
                                isSuccessfulPS=true;
                            }
                        } else if (PSType.equals(Codes.PS_EAGLE_TYPE_Providers)) {
                            dataObj = syncProviders(cho);
                            if(dataObj!=null){
                                isSuccessfulPS=true;
                            }
                        }else if(PSType.equals(Codes.PS_EAGLE_TYPE_PULL_CLIENTS)){
                            dataObj = syncClients(cho,Codes.CLIENTS_FOR_SITARABAJI);
                            if(dataObj!=null){
                                isSuccessfulPS=true;
                            }
                        }else if(PSType.equals(Codes.PS_TYPE_GET_Client)){
                            dataObj = syncClients(cho, Codes.CLIENTS_FOR_PROVIDERS);
                            if(dataObj!=null){
                                isSuccessfulPS=true;
                            }
                        }else{
                            EagleClientToServer eagleClientToServer = gson.fromJson(data,EagleClientToServer.class);
                            isSuccessfulPS = syncClientToServer(cho, eagleClientToServer);
                        }

                        if (isSuccessfulPS) {
                            data = gson.toJson(dataObj);
                            message = "Successfuly synced";
                            status = Codes.ALL_OK;

                        } else {
                            message = "Something went wrong";
                            status = Codes.SOMETHING_WENT_WRONG;
                        }
                    } else {
                        message = "Token is invalid";
                        status = Codes.INVALID_TOKEN;
                    }

                } catch (Exception e) {
                    message = "Something went wrong";
                    status = Codes.ERROR_PS_BASICINFO;
                } finally {
                    response.put("message", message);
                    response.put("status", status);
                    response.put("staffName", staffName);
                    response.put("data", data);
                    response.put("PSType", PSType);
                    return response.toString();
                }
            } else {
                message = "Invalid Token, you might be logged in from another device";
                status = Codes.INVALID_TOKEN;
            }
        }else{
            message = "Please update Falcon Application from playstore to version number "+version;
            status = Codes.INVALID_VERSION;
        }
        response.put("message", message);
        response.put("status", status);
        response.put("staffName", staffName);
        response.put("data", data);
        response.put("PSType",PSType);
        return response.toString();
    }

    private EagleData syncQuestions(CHO cho, EagleData data) {
        String queryQuestion  = "";
        String queryAreas  = "";

        queryQuestion  = "from Questions WHERE TYPE="+cho.getType();
        queryAreas = "from Areas";
        List<Questions> questions = new ArrayList<>();
        List<Areas> areas = new ArrayList<>();
        questions = (List<Questions>) HibernateUtil.getDBObjects(queryQuestion);
        areas = (List<Areas>) HibernateUtil.getDBObjects(queryAreas);
        if(questions!=null && questions.size()>0 && areas!=null && areas.size()>0){
            if(data==null){
                data = new EagleData();
            }
            data.setQuestions(questions);
            data.setAreas(areas);
        }

        return data;
    }

    private Date getSQLDate(Date dd){
        try {
            return new Date(Codes.DATE_FORMAT_DB.parse(Codes.DATE_FORMAT_DB.format(dd)).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            return null;
        }
    }

    private boolean syncClientToServer(CHO cho, EagleClientToServer eagleClientToServer) {

        boolean isSuccessful = true;
        String territoryCode = cho.getTerritoryCode();
        String sitaraBajiCode = territoryCode;
        String sitaraBajiName = cho.getName();
        String providerCode = "";
        String providerName = "";
        String providerDistrict = "";

        HSSyncDAO general = new HSSyncDAO();
        List<Providers> providers = general.getTaggedProviders(territoryCode);
        if(providers!=null && providers.size()>0){
            Providers provider = providers.get(0);
            providerCode = provider.getCode();
            providerName = provider.getName();
            providerDistrict = provider.getDistrict();
        }
        String region = general.getRegion(territoryCode);
        String supervisorCode = general.getAMCode(territoryCode);
        String supervisorName = general.getAMName(territoryCode);
        if(eagleClientToServer !=null && eagleClientToServer.getCrForms() != null && eagleClientToServer.getCrForms().size()>0){
            List<CRForm> forms = new ArrayList<>();
            List<CRForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getCrForms();

            if(forms!=null){
                for(CRForm form : forms){
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setIsSynced(1);
                    form.setReportingMonth(this.getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }

        if(eagleClientToServer !=null && eagleClientToServer.getChildRegistrationForms() != null && eagleClientToServer.getChildRegistrationForms().size()>0){
            List<ChildRegistrationForm> forms = new ArrayList<>();
            List<ChildRegistrationForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getChildRegistrationForms();
            if(forms!=null){
                for(ChildRegistrationForm form : forms){
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }

        if(eagleClientToServer !=null && eagleClientToServer.getFollowupForms() != null && eagleClientToServer.getFollowupForms().size()>0){
            List<FollowupForm> forms = new ArrayList<>();
            List<FollowupForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getFollowupForms();
            if(forms!=null){
                for(FollowupForm form : forms){
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }
        if(eagleClientToServer !=null && eagleClientToServer.getProductServices() != null && eagleClientToServer.getProductServices().size()>0){
            List<ProductService> productServices = new ArrayList<>();
            List<ProductService> listToInsert = new ArrayList<>();

            productServices = eagleClientToServer.getProductServices();


            isSuccessful = HibernateUtil.saveOrUpdateList(productServices);
        }

        if(eagleClientToServer !=null && eagleClientToServer.getScreeningFormHeaders() != null && eagleClientToServer.getScreeningFormHeaders().size()>0){
            List<ScreeningFormHeader> forms = new ArrayList<>();
            List<ScreeningFormHeader> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getScreeningFormHeaders();
            if(forms!=null){
                for(ScreeningFormHeader form : forms){
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }

        if(eagleClientToServer !=null && eagleClientToServer.getScreeningAreaDetails() != null && eagleClientToServer.getScreeningAreaDetails().size()>0){
            List<ScreeningAreaDetail> forms = new ArrayList<>();

            forms = eagleClientToServer.getScreeningAreaDetails();

            isSuccessful = HibernateUtil.saveOrUpdateList(forms);
        }
        if(eagleClientToServer !=null && eagleClientToServer.getScreeningTests() != null && eagleClientToServer.getScreeningTests().size()>0){
            List<ScreeningTest> forms = new ArrayList<>();

            forms = eagleClientToServer.getScreeningTests();

            isSuccessful = HibernateUtil.saveOrUpdateList(forms);
        }

        if(eagleClientToServer !=null && eagleClientToServer.getNeighbourForms() != null && eagleClientToServer.getNeighbourForms().size()>0){
            List<NeighbourForm> forms = new ArrayList<>();
            List<NeighbourForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getNeighbourForms();
            if(forms!=null) {
                for (NeighbourForm form : forms) {
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setSitarabajiName(sitaraBajiName);
                    form.setSupervisorName(supervisorName);
                    form.setSupervisorCode(supervisorCode);
                    form.setRegion(region);
                    form.setDistrict(providerDistrict);
                    form.setProviderName(providerName);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getVisitDate()));
                    listToInsert.add(form);
                }
            }
            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }
        if(eagleClientToServer !=null && eagleClientToServer.getTokenForms() != null && eagleClientToServer.getTokenForms().size()>0){
            List<TokenForm> forms = new ArrayList<>();
            List<TokenForm> listToInsert = new ArrayList<>();

            forms = eagleClientToServer.getTokenForms();
            if(forms!=null) {
                for (TokenForm form : forms) {
                    form.setSitarabajiCode(sitaraBajiCode);
                    form.setProviderCode(providerCode);
                    form.setReportingMonth(getReportingMonth(form.getReferralDate()));
                    listToInsert.add(form);
                }
            }
            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }


        if(eagleClientToServer !=null && eagleClientToServer.getNeighbourAttendeesForms() != null && eagleClientToServer.getNeighbourAttendeesForms().size()>0){

            List<NeighbourAttendeesForm> listToInsert = new ArrayList<>();

            listToInsert = eagleClientToServer.getNeighbourAttendeesForms();

            isSuccessful = HibernateUtil.saveOrUpdateList(listToInsert);
        }
        return isSuccessful;
    }

    private EagleData syncProviders(CHO cho) {
        EagleData data = new EagleData();
        List<Providers> providers = new ArrayList<>();
        providers = hsSyncDAO.getTaggedProviders(cho.getTerritoryCode());
        if(providers!=null && providers.size()>0){
            data.setProviderCode(providers.get(0).getCode());
            data.setProviderName(providers.get(0).getName());
        }

        return data;
    }

    private EagleData syncClients(CHO cho, int type) {
        String query = "";

        if(type==Codes.CLIENTS_FOR_SITARABAJI){
            query = "from CRForm where sitarabajiCode = '"+cho.getTerritoryCode()+"' order by id desc";
        }else if(type==Codes.CLIENTS_FOR_PROVIDERS){
            query = "from CRForm where sitarabajiCode = '"+cho.getTerritoryCode()+"' OR providerCode = '"+cho.getTerritoryCode()+"' order by id desc";
        }

        EagleData data = new EagleData();
        List<CRForm> crForms = new ArrayList<>();
        crForms = (List<CRForm>) HibernateUtil.getDBObjects(query);
        if(crForms!=null && crForms.size()>0){
            data.setCrForms(crForms);
        }

        return data;
    }

    private EagleData syncBasicInfo(CHO cho) {
        EagleData data = new EagleData();

        String amName = hsSyncDAO.getAMName(cho.getTerritoryCode());
        String amCode = hsSyncDAO.getAMCode(cho.getTerritoryCode());
        String region = hsSyncDAO.getRegion(cho.getTerritoryCode());

        if(amName!=null && amName.equals("") && amCode!=null && amCode.equals("")){
            return null;
        }
        data.setSitaraBajiName(cho.getName());
        data.setType(cho.getType());
        data.setSitaraBajiCode(cho.getTerritoryCode());
        data.setAMCode(amCode);
        data.setAMName(amName);
        data.setRegion(region);

        HSSyncDAO sync = new HSSyncDAO();
        List<Providers> provider = sync.getTaggedProviders(cho.getTerritoryCode());
        if(provider!=null && provider.size()>0){
            data.setProviderName(provider.get(0).getName());
            data.setProviderCode(provider.get(0).getCode());
            data.setDistrict(provider.get(0).getDistrict());
        }
        CRBSyncDAO syncDD = new CRBSyncDAO();
        data.setDropdownCRBData(syncDD.getDropdownData());

        long countCRForm, countFollowup, countToken, countNeighbour, countChild;

        countChild = HibernateUtil.getRecordCount("SELECT COUNT(*) FROM ChildRegistrationForm where sitarabajiCode='"+cho.getTerritoryCode()+"'");
        countCRForm = HibernateUtil.getRecordCount("SELECT COUNT(*) FROM CRForm where sitarabajiCode='"+cho.getTerritoryCode()+"'");
        countFollowup = HibernateUtil.getRecordCount("SELECT COUNT(*) FROM FollowupForm where sitarabajiCode='"+cho.getTerritoryCode()+"'");
        countToken = HibernateUtil.getRecordCount("SELECT COUNT(*) FROM TokenForm  where sitarabajiCode='"+cho.getTerritoryCode()+"'");
        countNeighbour = HibernateUtil.getRecordCount("SELECT COUNT(*) FROM NeighbourForm where sitarabajiCode='"+cho.getTerritoryCode()+"'");

        HashMap<String,Long> dashboardParams = new HashMap<String, Long>();
        dashboardParams.put("countCRForm",countCRForm);
        dashboardParams.put("countFollowup",countFollowup);
        dashboardParams.put("countToken",countToken);
        dashboardParams.put("countNeighbour",countNeighbour);
        dashboardParams.put("countChild",countChild);

        Dashboard dashboard = new Dashboard();
        dashboard = createDashboardForEagle(dashboardParams);
        data.setDashboard(dashboard);
        return data;
    }

    private Dashboard createDashboardForEagle(HashMap<String, Long> dashboardParams) {
        long countCRForm, countFollowup, countToken, countNeighbour, countChild;


        countCRForm = dashboardParams.get("countCRForm");
        countFollowup = dashboardParams.get("countFollowup");
        countToken = dashboardParams.get("countToken");
        countNeighbour = dashboardParams.get("countNeighbour");
        countChild = dashboardParams.get("countChild");

        String html = "<html>" +
                "<body>" +
                "" +
                "<table id=\"customers\">" +
                "  <tr>" +
                "    <th> </th>" +
                "    <th>Total</th>" +
                "  </tr>";
        html += "<tr>" +
                "    <td>MWRA</td>" +
                "    <td>"+countCRForm+"</td>" +
                "  </tr>";
        html+="<tr>" +
                "    <td><b>Children</b></td>" +
                "    <td><b>"+countChild+"</b></td>" +
                "  </tr>";

        html+="<tr>" +
                "    <td><b>Followup</b></td>" +
                "    <td><b>"+countFollowup+"</b></td>" +
                "  </tr>";
        html+="<tr>" +
                "    <td><b>Neighbour</b></td>" +
                "    <td><b>"+countNeighbour+"</b></td>" +
                "  </tr>";
        html+="<tr>" +
                "    <td><b>Token</b></td>" +
                "    <td><b>"+countToken+"</b></td>" +
                "  </tr>";
        html+="</table>" +
                "</body>" +
                "</html>";

        Dashboard dashboard = new Dashboard();
        dashboard.setHtml(html);
        dashboard.setId(1);
        return dashboard;
    }

    public String getReportingMonth(Date visitDate) {
        String reportingMonth = "";
        if(visitDate!=null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(visitDate);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            String name = monthNames[month];
            reportingMonth = name + "," + String.valueOf(year);

        }
        return reportingMonth;
    }
    @RequestMapping(value = "/getNotification", method = RequestMethod.GET,params={"token", "followupDate"})
    @ResponseBody
    private String JSONObject(String token, String followupDate) {
        GSSStaffDAO staffDAO = new GSSStaffDAO();
        String code = staffDAO.isTokenValid(token);
        Notification notification = new Notification();
        List<Notification> notifications = new ArrayList<>();

        String queryFollowupForm = "SELECT cr.CLIENTNAME, cr.CLIENTAGE,cr.remarks, ff.followupdate FROM eagle_followup_form ff INNER JOIN eagle_client_registration cr on cr.id = ff.clientid where ff.sitarabajiCode='"+code+"' and lower(ff.followupdate) = '"+followupDate.toLowerCase()+"' order by ff.syncdate desc";
        String queryClientRegistration = "SELECT cr.CLIENTNAME, cr.CLIENTAGE,cr.remarks, cr.followupvisitdate FROM eagle_client_registration cr where cr.sitarabajiCode='"+code+"' and lower(cr.visitdate) = '"+followupDate.toLowerCase()+"' order by cr.syncdate desc";
        String querySummary = "SELECT * FROM (SELECT  to_char(visitdate,'DD-MON-YY'),count(*) FROM eagle_client_registration where visitdate is not null and sitarabajicode ='"+code+"'  group by visitdate  order by visitdate desc )  where rownum<6";

        ArrayList<Object> objFollowup = HibernateUtil.getDBObjectsFromSQLQuery(queryFollowupForm);
        ArrayList<Object> objClient = HibernateUtil.getDBObjectsFromSQLQuery(queryClientRegistration);
        ArrayList<Object> objSummary = HibernateUtil.getDBObjectsFromSQLQuery(querySummary);

        notifications.addAll(getNotifications(objClient));
        notifications.addAll(getNotifications(objFollowup));

        String html = getSummaryHTML(getEagleSummaries(objSummary)) + getHTML(getNotifications(objClient),"Clients Registration") + getHTML(getNotifications(objFollowup),"Clients Followup");
        JSONObject response = new JSONObject();
        response.put("message", "");
        response.put("status", Codes.ALL_OK);
        response.put("data", html);
        return response.toString();
    }

    private String getSummaryHTML(List<EagleSummary> eagleSummaries) {
        String html = "<html><head> <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\"></head>" +
                "<h3>Summary</h3>" +
                "<table class=\"table table-striped\">\n" +
                "  <thead>\n" +
                "    <tr class=\"bg-primary\">\n" +
                "      <th scope=\"col\">Visit Date</th>\n" +
                "      <th scope=\"col\">Count</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n";

        for(EagleSummary eagleSummary : eagleSummaries){
            html += "    <tr>\n" +
                    "      <th scope=\"row\">"+eagleSummary.getVisitDate()+"</th>\n" +
                    "      <td>"+eagleSummary.getCount()+"</td>\n" +
                    "    </tr>\n";
        }
        html += "  </tbody>\n" +
                "</table></html>";
        return html;
    }

    private String getHTML(List<Notification> notifications, String title) {
        String html = "";
        if(notifications!= null && notifications.size()>0) {
             html = "<html><head> <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\"></head>" +
                    "<h3>" + title + "</h3>" +
                    "<table class=\"table table-striped\">\n" +
                    "  <thead>\n" +
                    "    <tr class=\"bg-primary\">\n" +
                    "      <th scope=\"col\">Name</th>\n" +
                    "      <th scope=\"col\">AGE</th>\n" +
                    "      <th scope=\"col\">Reason</th>\n" +
                    "      <th scope=\"col\">Followup Date</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n";

            for (Notification notification : notifications) {
                html += "    <tr>\n" +
                        "      <th scope=\"row\">" + notification.getClientName() + "</th>\n" +
                        "      <td>" + notification.getClientAge() + "</td>\n" +
                        "      <td>" + notification.getReasons() + "</td>\n" +
                        "      <td>" + notification.getFollowupDate() + "</td>\n" +
                        "    </tr>\n";
            }
            html += "  </tbody>\n" +
                    "</table></html>";
        }
        return html;
    }

    private List<Notification> getNotifications(ArrayList<Object> objects){
        List<Notification> notifications = new ArrayList<>();
        Notification not = new Notification() ;
        if(objects!=null){
            for(Object obj : objects){
                if(obj!=null){
                    Object[] objArr = (Object[]) obj;
                    if(objArr!=null && objArr.length>0){
                        not = new Notification();
                        not.setClientName(objArr[0].toString());
                        not.setClientAge(objArr[1].toString());
                        if(objArr[2]!=null){
                            not.setReasons(objArr[2].toString());
                        }

                        try{
                            java.util.Date date = new SimpleDateFormat("yyyy-MM-DD").parse(objArr[3].toString());
                            not.setFollowupDate(new SimpleDateFormat("dd-MMM-YY").format(date));
                        }catch (Exception e){

                        }
                        notifications.add(not);
                    }
                }
            }
        }
        return notifications;
    }

    private List<EagleSummary> getEagleSummaries(ArrayList<Object> objects){
        List<EagleSummary> eagleSummaries = new ArrayList<>();
        EagleSummary eagleSummary = new EagleSummary() ;
        if(objects!=null){
            for(Object obj : objects){
                if(obj!=null){
                    Object[] objArr = (Object[]) obj;
                    if(objArr!=null && objArr.length>0){
                        eagleSummary = new EagleSummary();
                        eagleSummary.setVisitDate(objArr[0].toString());
                        eagleSummary.setCount(Integer.valueOf(objArr[1].toString()));
                        eagleSummaries.add(eagleSummary);
                    }
                }
            }
        }
        return eagleSummaries;
    }
}
