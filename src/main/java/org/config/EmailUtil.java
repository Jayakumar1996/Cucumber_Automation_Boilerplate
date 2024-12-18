package org.config;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.File;

public class EmailUtil {

    public static void sendEmail(String reportPath) {
        System.out.println("Email Entered");
        
        // SMTP server settings
        ConfigReader config = new ConfigReader();

        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;
        final String senderEmail = config.getSMTPSenderEmail();
        final String senderPassword = config.getSMTPPassword();
        
        // Recipient email address
        String recipientEmail = config.getSMTPRecipientEmail();
        
        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create MimeMessage object
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Test Report");

            // Create MimeBodyPart for the email body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached test report.");

            // Create MimeBodyPart for the test report attachment
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

            // Validate the report path
            File file = new File(reportPath);
            if (!file.exists() || !file.isFile()) {
                throw new MessagingException("Attachment file does not exist or is not a valid file: " + reportPath);
            }

            attachmentBodyPart.attachFile(file);

            // Create Multipart object to combine body and attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Set content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            System.out.println("Email Not sent successfully!");
            e.printStackTrace();
        }
    }
}
