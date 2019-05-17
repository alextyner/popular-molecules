package pm.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import pm.data.Chemical;
import pm.io.FileReader;
import pm.util.WebQuery;

/**
 * User interface for the Popular Molecules program. Reads chemical names from a
 * file, submits queries for the number of Wikipedia page views for their
 * articles, and writes the sorted results to a file.
 * 
 * @author Alex Tyner
 */
public class PopularMoleculesUI {

    /**
     * Starts the program.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String url = "https://wikimedia.org/api/rest_v1/metrics/pageviews/per-article/en.wikipedia/all-access/all-agents/Uric_acid/monthly/2018010100/2019123100";
        System.out.println(String.format("%-8s", "[Info]") + " Popular Molecules started.\n");
        System.out.println(String.format("%-8s", "[Info]")
                + " Given an input file of chemical names, this program will query Wikimedia for the 2018 monthly \n"
                + String.format("%-8s", "[Info]")
                + " Wikipedia page views for each chemical. Each of the 12 months are averaged to determine the chemical's \n"
                + String.format("%-8s", "[Info]")
                + " popularity score. The chemicals will be sorted by popularity and the results written to an output file (CSV) of your choice.\n");
        System.out.println(String.format("%-8s", "[Info]")
                + " Each line of the input file is expected to be of the following format: ");
        System.out.println(String.format("%-8s", "[Info]") + " \t{Chemical Name}\\t{Tag}\\n");
        System.out.println(String.format("%-8s", "[Info]")
                + " where \\t is a hard tab and {Tag} represents an extra piece of information discarded by the program.\n");
        Scanner get = new Scanner(System.in);
        System.out
                .print(String.format("%-8s", "[Input]") + " Enter the path to your input file: ");

        List<Chemical> input = null;
        while (input == null) {
            String inputPath = get.next();
            System.out.println(String.format("%-8s", "[Status]") + " Working...");
            try {
                input = FileReader.readFile(inputPath);
            } catch (FileNotFoundException e) {
                System.out.println(String.format("%-8s", "[Error]") + " File \"" + inputPath
                        + "\" could not be opened.");
                System.out.print(
                        String.format("%-8s", "[Input]") + " Enter the path to your input file: ");
            }
        }

        System.out.println(String.format("%-8s", "[Info]") + " Discovered " + input.size()
                + " chemical names.");

    }

    /**
     * Populates the page views field of each Chemical in the given list using
     * methods from the WebQuery class.
     * 
     * @param input list of Chemicals without page view counts
     * @return list of Chemicals with page view counts
     */
    private static void populateViews(List<Chemical> input) {
        for (Chemical c : input) {
            try {
                c.setViews(viewsFromJSON(WebQuery.getJSONViewsSummary(c.getName())));
            } catch (JsonIOException|IOException|JsonSyntaxException e) {
                c.setViews(-1);
            }
        }
    }
    
    public static int viewsFromJSON(JsonObject json) {
        
    }

}
