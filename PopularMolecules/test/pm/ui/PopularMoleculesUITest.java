package pm.ui;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import pm.util.WebQuery;

/**
 * Tests the {@link PopularMoleculesUI} class.
 * 
 * @author Alex Tyner
 */
public class PopularMoleculesUITest {

    /**
     * Runs before each test.
     * 
     * @throws java.lang.Exception if error
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link pm.ui.PopularMoleculesUI#viewsFromJSON(com.google.gson.JsonObject)}.
     */
    @Test
    public void testViewsFromJSON() throws JsonIOException, JsonSyntaxException, IOException {
        JsonArray months = WebQuery.getJSONSummary("Uric acid").get("items").getAsJsonArray();
        assertEquals(44841, months.get(0).getAsJsonObject().get("views").getAsInt());
    }

}
