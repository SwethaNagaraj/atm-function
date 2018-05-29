package com.suncorp.org;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * Service with implementation to notify the concerned team or person when threshold 
 * of dispenser is reached
 */

public class NotifyService {
    private static final Logger logger = Logger.getLogger("NotifyService");

    public void sendNotification() {
        logger.entering("Entering Class : NotifyService", "Method : sendNotification");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.port", 465);

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sender@gmail.com", "password");
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("sender@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("reciever@gmail.com"));
            message.setSubject("Threshold reached in Cash Dispenser");
            message.setText("Cash Dispenser reached threshold limit. Please re-supply the dispenser as soon as possible!");

            // send e-mail
            Transport.send(message);
            logger.fine("Notification sent successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Got exception" + e.getMessage());
        }
        logger.exiting("Exiting Class : NotifyService", "Method : sendNotification");
    }
}
