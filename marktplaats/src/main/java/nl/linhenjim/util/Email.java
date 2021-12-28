package nl.linhenjim.util;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

public class Email {
    private static String USER_NAME = "BD.Beheerder.Marktplaats";  // GMail user name, just the part before "@gmail.com"
    private static String PASSWORD = ""; // GMail password
    private static String SUBJECT = "Wachtwoord voor inloggen van Marktplaats";

    @Inject
    private Logger logger;

    public boolean sendEmail(String ontvanger, String wachtwoordVoorOntvanger) {
        boolean succesvolVerzonden = false;
        System.out.println(ontvanger);
        System.out.println(wachtwoordVoorOntvanger);
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        properties.put("mail.smtp.starttls.enable", "true"); //ensures TLS is used or connection won't happen

        properties.put("mail.smtp.ssl.trust", host); // The SMTP server to connect to.
        properties.put("mail.smtp.user", USER_NAME); //	Default user name for SMTP.
        properties.put("mail.smtp.password", PASSWORD);
        properties.put("mail.smtp.port", "587"); //The SMTP server port to connect to (TLS)
        properties.put("mail.smtp.auth", "true"); //attempt to authenticate the user using the AUTH command.

        // Get the default Session object.
        // A session is a state consisting of several requests and response between the client and the server
        // A Session holds configuration information.
        Session session = Session.getDefaultInstance(properties);

        // Create a default MimeMessage object.
        // Multipurpose Internet Mail Extensions (MIME) is an Internet standard that extends the format of email messages
        // to support text in character sets other than ASCII, as well as attachments of audio, video, images, and application programs.
        MimeMessage message = new MimeMessage(session);

        succesvolVerzonden = isSuccesvolVerzonden(ontvanger, wachtwoordVoorOntvanger, succesvolVerzonden, host, session, message);
        return succesvolVerzonden;
    }

    private boolean isSuccesvolVerzonden(String ontvanger, String wachtwoordVoorOntvanger,
                                         boolean succesvolVerzonden,
                                         String host, Session session, MimeMessage message) {
        Transport transport = null;
        try {
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(USER_NAME));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(ontvanger));

            // Set Subject: header field
            message.setSubject(SUBJECT);

            // Now set the actual message
            String body = String.format("Goededag,\n\nUw wachtwoord voor de Belastingdienst Marktplaats is %s\n\n" +
                    "Met vriendelijke groeten,\nJane Doe\nBeheerder Marktplaats", wachtwoordVoorOntvanger);
            message.setText(body);

            //A Transport corresponds to a single connection.
            transport = session.getTransport("smtp");
            System.out.println("works untill here");

            transport.connect(host, USER_NAME, PASSWORD);
            System.out.println("connected to the host");
            // Send the Message to the specified list of addresses
            //transport.sendMessage(message, message.getAllRecipients()); //Turn off to prevent sending the mail
            succesvolVerzonden = true;
            System.out.println("Verzenden is gelukt!");

        } catch (SendFailedException | AddressException | NoSuchProviderException sfex) {
            logger.severe("Verzenden van email is mislukt");
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException mex) {
                    logger.severe("Failed to close transport");
                }
            }
        }
        return succesvolVerzonden;
    }
}
