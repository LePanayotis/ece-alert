package gr.ntua.ece;

import org.jsoup.nodes.Element;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger("gr.ntua.ece");

    public static void main(String[] args) {

        // Load the .env file
        Dotenv dotenv = Dotenv.configure().directory(".").load();

        // Get configuration parameters from environment

        String senderEmail = dotenv.get("SENDER_EMAIL");
        String recipientEmail = dotenv.get("RECIPIENT_EMAIL");
        String password = dotenv.get("PASSWORD");
        String smtp_server = dotenv.get("SMTP_SERVER");
        String smtp_port = dotenv.get("SMTP_PORT", "587");
        String ece_url = dotenv.get("ECE_URL", "https://www.ece.ntua.gr/gr/announcements");

        // Initial tag of the last uploaded announcement (user defined)
        String prev_tag = dotenv.get("PREV_TAG", "");

        // Polling interval to the $ece_url
        Long poll_interval = Long.parseLong(dotenv.get("POLL_INTERVAL", "200000"));

        logger.info("Environment parsed");

        // Creates parser and mailer instances from configuration
        Parser parser = new Parser(ece_url);
        Mailer mailer = new Mailer(senderEmail, recipientEmail, password, smtp_server, smtp_port);

        // Tag-id of the retreived last announcement, initially unset.
        String tag;

        // Endless loop
        while (true) {

            // Gets last uploaded announcement element and its tag
            try {
                Element ann = parser.getNewAnnouncementElement();
                tag = ContentRetreiver.getIdFromElement(ann);

                // If tag equals to previous tag then there has been no new announcement
                if (!prev_tag.equals(tag)) {

                    // Gets announcement content and send email alert
                    Element content = parser.getAnnouncementContent(ann);
                    mailer.sendMail(ann, content);

                    logger.info("New tag: " + tag);

                    // Set previous tag to current
                    prev_tag = tag;
                } else {
                    logger.info("Got no new announcement");
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            try {

                // Sleep for $poll_interval milliseconds until next iteration
                Thread.sleep(poll_interval);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
