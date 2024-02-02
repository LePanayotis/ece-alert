package gr.ntua.ece;

import org.jsoup.nodes.Element;

/**
 * Class with static methods that get desired properties from Elements
 * Useful for the <a href="http://www.ece.ntua.gr">ECE NTUA web site</a>
 * 
 * @author <a href="mailto:el19055@mail.ntua.gr">Panagiotis Papagiannakis</a>
 */
public class ContentRetreiver {


    /**
     * @param contentElement the content element of the announcement retreived from the DOM
     * @return The title of the announcement in the ECE NTUA web site
     */
    public static String getAnnouncementTitle(Element contentElement){
        String title = contentElement.select("h3").first().text();
        return title;

    }


    /**
     * @param targetElement The DOM element of the announcement
     * @return The URL pointing to the full announcement 
     * e.g. https://www.ece.ntua.gr/gr/announcement/1665
     */
    public static String getAnnouncementUrl(Element targetElement) {
        String url = targetElement.select("a").first().attr("href");   
        return url;
    }

    /**
     * @param url The url pointing to an announcement e.g. https://www.ece.ntua.gr/gr/announcement/1665
     * @return The substring of the url after the last '/' character, in String format, in the above case '1665'
     */
    public static String getIdFromUrl(String url){
        String[] urlArray = url.split("/");
        if (urlArray.length < 1 ){
            return null;
        }
        return urlArray[urlArray.length-1];
    }

    /**
     * @param targetElement The DOM element corresponding to the announcement
     * @return The id of the announcement
     */
    public static String getIdFromElement(Element targetElement){
        String url = getAnnouncementUrl(targetElement);
        String id = getIdFromUrl(url);
        return id;
    }
}
