package gr.ntua.ece;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;

public class Parser {

    private String url;

    /**
     * Creates new parser instance that polls from the given url
     * @param _url
     */
    public Parser(@NotNull String _url) {
        this.url = _url;
    }


    /**
     * Gets the latest announcement on the table at the given url,
     * by default https://www.ece.ntua.gr/gr/announcements
     * 
     * @return The DOM element representing the announcement row
     */
    public Element getNewAnnouncementElement() {

        try {
            // Fetch the HTML from the webpage
            Document document = Jsoup.connect(url).get();

            // Access a particular element using its tag or class
            Element targetElement = document.select("tr.clickable-row").first();

            return targetElement;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;  

    }

    /**
     * Provided an announcement element, retreives its full content from the corresponding URL
     * 
     * @param announcementElement
     * @return DOM element representing the content of the announcement
     */
    public Element getAnnouncementContent(Element announcementElement) {
        
        String contentUrl = ContentRetreiver.getAnnouncementUrl(announcementElement);
        
        try {
            // Fetch the HTML from the webpage
            Document document = Jsoup.connect(contentUrl).get();

            // Access a particular element using its tag or class
            Element targetElement = document.select("div#content").first();

            return targetElement;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
