/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.awt.Component;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.JOptionPane;

public class EmailNotification {

    private static final EmailNotification instance = new EmailNotification();

    public static EmailNotification getInstance() {
        return instance;
    }

    private String host = "smtp.gmail.com";
    private final String user = "proyectoprogramado2dnjab@gmail.com";
    private final String password = "dropbox123";
    private  String to = "";

    private Properties getProperties() {

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        return props;
    }

    private Session getSession() {
        Properties props = getProperties();
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        return session;
    }

    public void sendEmail(String to, String nombre)  {
        this.to=to;
        try {
            Session session = getSession();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Solicitud recibida");
            message.setText("Su solicitud está siendo procesada");
            
            Multipart emailContent=new MimeMultipart();
            
            MimeBodyPart textBodyPart=new MimeBodyPart();
            textBodyPart.setText("multi");
            
            MimeBodyPart pdfAttachment =new MimeBodyPart();
            pdfAttachment.attachFile("C:\\Users\\jabre\\OneDrive\\Documentos\\NetBeansProjects\\PRIMERPROYECTOPOO\\"+nombre+".pdf");
            
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);
            
            message.setContent(emailContent);

            Transport.send(message);
            System.out.print("jose");

        } catch (MessagingException e) {
           System.out.print(e);
        }
        catch(IOException E){
        System.out.print(E);
        }
    }

}
