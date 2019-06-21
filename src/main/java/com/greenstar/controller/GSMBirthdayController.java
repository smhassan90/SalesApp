package com.greenstar.controller;

import com.greenstar.dao.GSMBirthdayDAO;
import com.greenstar.dao.IGSMBirthdayDAO;
import com.greenstar.entity.GSMBirthday;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

@Controller
public class GSMBirthdayController {
    public static Logger LOG = LogManager.getLogger(GSMBirthdayController.class);

    @RequestMapping(value = "/sendBirthdayWishes", method = RequestMethod.GET)
    @ResponseBody
    public String index(){

        IGSMBirthdayDAO gsmBirthdayList = new GSMBirthdayDAO();
        List<GSMBirthday> listEmployees = gsmBirthdayList.list();

        int todayMonth = 0;
        int todayDay = 0;
        Calendar cal = Calendar.getInstance();
        todayMonth = cal.get(Calendar.MONTH);
        todayDay = cal.get(Calendar.DAY_OF_MONTH);
        String saveEmail="";
        for(GSMBirthday gsmBirthday: listEmployees){
            if(gsmBirthday!=null && gsmBirthday.getDateOfBirth() !=null){
                cal.setTime(gsmBirthday.getDateOfBirth());
                int birthdayMonth = cal.get(Calendar.MONTH);
                int birthdayDay = cal.get(Calendar.DAY_OF_MONTH);
                if(todayMonth == birthdayMonth && todayDay == birthdayDay){
                    try{
                        String toAddress = gsmBirthday.getEmployeeEmail();
                        int ccSize=0;
                        InternetAddress[] ccAddress = null;
                        if(gsmBirthday.getSupervisorEmail()!=null ){
                            ccAddress = new InternetAddress[2];
                            ccAddress[0] = new InternetAddress("uzzmakhan@greenstar.org.pk");

                            // ccAddress[0] = new InternetAddress("asimjawaid@greenstar.org.pk");
                            ccAddress[1] = new InternetAddress(gsmBirthday.getSupervisorEmail());
                        }else{
                            ccAddress = new InternetAddress[1];
                            // ccAddress[0] = new InternetAddress("asimjawaid@greenstar.org.pk");
                            ccAddress[0] = new InternetAddress("uzzmakhan@greenstar.org.pk");
                        }
                        saveEmail = toAddress;
                        sendEmailWithInlineImage(toAddress,gsmBirthday.getEmployeeName(),ccAddress);
                    }catch(Exception e){
                        LOG.error(e);
                    }
                }
            }
        }
        return "Email sent to "+saveEmail;
    }

    public void sendEmailWithInlineImage(String toAddress, String name, InternetAddress[] ccAddresses){

        // Sender's email ID needs to be mentioned
        String from = "support.it@greenstar.org.pk";
        final String username = "support.it@greenstar.org.pk";//change accordingly
        final String password = "Birthday123$";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        final String host = "smtp.office365.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        InternetAddress[] bccAddress = new InternetAddress[3];

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            bccAddress[0] = new InternetAddress("asimjawaid@greenstar.org.pk");
            bccAddress[1] = new InternetAddress("syedhassan@greenstar.org.pk");
            bccAddress[2] = new InternetAddress("imrantirmizi@greenstar.org.pk");
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));

            message.setRecipients(Message.RecipientType.CC, ccAddresses);
            message.setRecipients(Message.RecipientType.BCC, bccAddress);
            // Set Subject: header field
            message.setSubject("Happy Birthday");

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>Dear " + name +",</H1><img src=\"cid:image\" width='2048px' />";
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();

            String file = "D:\\birthday_img.jpg";
            DataSource fds = new FileDataSource(file);

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);

            // put everything together
            message.setContent(multipart);

            // Send message
            // TO-DO: Uncomment this
            Transport.send(message);

        } catch (MessagingException e) {
            LOG.error(e);
        }

    }

}
