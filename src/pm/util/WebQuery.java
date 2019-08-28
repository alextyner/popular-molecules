package pm.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import pm.ui.PopularMoleculesUI;

/**
 * Performs HTTP requests to retrieve page view counts for Wikipedia articles.
 * Turns the JSON received into Java objects.
 * 
 * @author Alex Tyner
 */
public class WebQuery {

    /**
     * Gets a JSON object describing the monthly views for a Wikipedia article.
     * 
     * @param articleName name of the article
     * @return views data as JSON object
     * @throws IOException         if IO error
     * @throws JsonSyntaxException
     * @throws JsonIOException     if IO error
     */
    public static JsonObject getJSONSummary(String articleName)
            throws JsonIOException, JsonSyntaxException, IOException {
        String url = formURL(articleName);

        JsonParser parser = new JsonParser();

        return parser.parse(new InputStreamReader(getWebData(url))).getAsJsonObject();
    }

    /**
     * Queries a URL, returning the response as an {@link InputStream}.
     * 
     * @param url URL to query as a String
     * @return the data received from the URL as an InputStream
     * @throws IOException on malformed URL; on error opening HTTP connection
     */
    private static InputStream getWebData(String surl) throws IOException {
        URL url = new URL(surl);
        URLConnection query = url.openConnection();
        query.connect();

        return (InputStream) query.getContent();
    }

    /**
     * Forms a Wikimedia URL to query based on an article name.
     * 
     * @param articleName article
     * @return URL string to get a JSON result from
     */
    private static String formURL(String articleName) {
        return new StringBuilder(
                "https://wikimedia.org/api/rest_v1/metrics/pageviews/per-article/en.wikipedia/all-access/all-agents/")
                        .append(articleName.replace(' ', '_')).append("/monthly/")
                        .append(PopularMoleculesUI.SAMPLE_YEAR).append("010100/")
                        .append(PopularMoleculesUI.SAMPLE_YEAR).append("123100").toString();
    }
}
