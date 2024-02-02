package gr.ntua.ece;

import java.util.Properties;
import javax.mail.*;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

    private Properties props = new Properties();
    private String senderEmail = null;
    private String recipientEmail = null;
    private String senderPassword = null;


    /**
     * Creates new Mailer instance with the following desired configuration properties
     * Used to send email to recipients upon new detected announcements on https://www.ece.ntua.gr/gr/announcements
     * @author <a href="mailto:el19055@mail.ntua.gr">Panagiotis Papagiannakis</a>
     * @param _senderEmail
     * @param _recipientEmail
     * @param _senderPassword
     * @param _smtpServer
     * @param _smtpPort
     */
    public Mailer(@NotNull String _senderEmail,@NotNull String _recipientEmail, @NotNull String _senderPassword, @NotNull String _smtpServer,@NotNull String _smtpPort) {

        senderEmail = _senderEmail;
        recipientEmail = _recipientEmail;
        senderPassword = _senderPassword;

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", _smtpServer);
        props.put("mail.smtp.port", _smtpPort);

    }

    /**
     * Provided the announcement DOM element found on the table on https://www.ece.ntua.gr/gr/announcements
     * and its content DOM element on https://www.ece.ntua.gr/gr/announcements/#id sends alert mail using the set configuration.
     * @param announcement
     * @param content
     */
    public void sendMail(Element announcement, Element content){


        // Create a Session object with the authentication information
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            //Prepare data
            String announcementTitle = ContentRetreiver.getAnnouncementTitle(content);
            String announcementUrl = ContentRetreiver.getAnnouncementUrl(announcement);
            String link = "<br><a href=\"" +announcementUrl+"\">Check here</a>";

            //Subject has form "[ECE ALERT] The title of the alert"
            message.setSubject("[ECE Alert] "+ announcementTitle);
        
            //Email content with full announcement inner html
            message.setContent(content.html()+link, "text/html; charset=utf-8");

            //Sends email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
