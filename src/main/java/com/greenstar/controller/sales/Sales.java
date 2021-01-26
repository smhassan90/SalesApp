package com.greenstar.controller.sales;

import com.greenstar.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Syed Muhammad Hassan
 * 26th October, 2020
 */

@Controller
public class Sales {

    @RequestMapping(value = "/salesUpdate", method = RequestMethod.GET)
    @ResponseBody
    public String salesUpdate(){

        String maxVID = getMaxVID();
        String minDate = getMinTransactionDate(maxVID);
        String maxDate = getMaxTransactionDate(maxVID);
        ArrayList<Object> rows = new ArrayList<>();
        String strquery = "";
        String strDestID ="";

        rows = getDestinationRecords(minDate,maxDate);

        String deleteQuery = "";
        String copyQuery = "";
        if(rows!=null && rows.size()>0){
            strDestID = getDestinationVID(minDate, maxDate);

            deleteQuery = "delete from SD_MONTHLY_FINAL_DATA where vid='" + strDestID + "'";
        }

        copyQuery += "insert into  SD_MONTHLY_FINAL_DATA(select * from SAL_DAILY_DATA_HIS_COPY where vid='" + maxVID + "')";

        if(strDestID.equals(maxVID)){
        }else{
            if(deleteQuery!=""){
                HibernateUtil.executeQuery(deleteQuery);

            }
            HibernateUtil.executeQuery(copyQuery);
        }

        String transactionDate = HibernateUtil.getSingleString("SELECT MAX(TRANSACTION_DATE) FROM SD_MONTHLY_FINAL_DATA");

        if(transactionDate!=null && transactionDate!="") {
            String body = "Daily Sales Data is now available till " + transactionDate.split(" ")[0] + " in Database.\n It is a System Generated Email.";
            sendEmail(body);
        }
        return "Done";
    }

    private void sendEmail(String body){
        // Sender's email ID needs to be mentioned
        String from = "support.it@greenstar.org.pk";
        final String username = "support.it@greenstar.org.pk";//change accordingly
        final String password = "K#r@3hi32";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        final String host = "smtp.office365.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        InternetAddress[] ccAddress = new InternetAddress[10];

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        PasswordAuthentication pswAuth = new PasswordAuthentication(username, password);
                        return pswAuth;
                    }
                });

        try {
            ccAddress[0] = new InternetAddress("sajidali@greenstar.org.pk");
            ccAddress[1] = new InternetAddress("umeriftikhar@greenstar.org.pk");
            ccAddress[2] = new InternetAddress("masif@greenstar.org.pk");
            ccAddress[3] = new InternetAddress("hasnain.ali@greenstar.org.pk");
            ccAddress[4] = new InternetAddress("saeedulhoda@greenstar.org.pk");
            ccAddress[5] = new InternetAddress("zahidnajmi@greenstar.org.pk");
            ccAddress[6] = new InternetAddress("mtafseer@greenstar.org.pk");
            ccAddress[7] = new InternetAddress("shahzaibsattar@greenstar.org.pk");
            ccAddress[8] = new InternetAddress("saledata@yahoo.com");
            ccAddress[9] = new InternetAddress("syedhassan@greenstar.org.pk");
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.

            String toAddress = "ikonscsm@yahoo.com";
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));

            message.setRecipients(Message.RecipientType.CC, ccAddress);
            // Set Subject: header field
            message.setSubject("Daily Sales Data");

            // put everything together
            message.setText(body);

            // Send message
            // TO-DO: Uncomment this
            Transport.send(message);

        } catch (MessagingException e) {
            int i = 0;
        }catch(Exception ex){
            int i =0;
        }
        int ss = 9;
    }

    private String getDestinationVID(String minDate, String maxDate){

        String maxVID = "";
        String query = "select max(vid) from SD_MONTHLY_FINAL_DATA where NATURE IS NULL AND TRANSACTION_DATE BETWEEN TO_DATE('" + getOnlyDate(minDate) + "','YYYY-MM-DD') AND TO_DATE('" + getOnlyDate(maxDate) + "','YYYY-MM-DD')";

        maxVID = HibernateUtil.getSingleString(query);

        return maxVID;
    }

    private String getOnlyDate(String date){
        if(date!="" && date.contains(" ")) {
            String[] onlyDate;
            onlyDate = date.split(" ");
            return onlyDate[0];
        }else{
            return "";
        }
    }

    private ArrayList<Object> getDestinationRecords(String minDate, String maxDate){
        ArrayList<Object> rows = new ArrayList<>();
        String query = "select distinct vid from SD_MONTHLY_FINAL_DATA where TRANSACTION_DATE BETWEEN TO_DATE('" + getOnlyDate(minDate) + "','YYYY-MM-DD') AND TO_DATE('" + getOnlyDate(maxDate) + "','YYYY-MM-DD')";

        rows = HibernateUtil.getDBObjectsFromSQLQuery(query);

        return rows;
    }

    private String getMaxVID(){
        String vid =  HibernateUtil.getSingleString("SELECT MAX(VID) FROM sal_daily_data_his_copy");

        return vid;
    }

    private String getMinTransactionDate(String maxVID){
        String date = "";
        String query = "select min(transaction_date) as mindate from SAL_DAILY_DATA_HIS_COPY where vid='" + maxVID + "'";

        date = HibernateUtil.getSingleString(query);

        return date;
    }

    private String getMaxTransactionDate(String maxVID){
        String date = "";
        String query = "select max(transaction_date) as mindate from SAL_DAILY_DATA_HIS_COPY where vid='" + maxVID + "'";

        date = HibernateUtil.getSingleString(query);

        return date;
    }
}
