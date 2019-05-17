package pm.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedMap;

import pm.data.Chemical;

/**
 * Reads chemical names from a tab-delimited file also containing SMILES
 * representations.
 * 
 * @author Alex Tyner
 */
public class FileReaderWriter {

    /**
     * Reads chemical names from a tab-delimited file and returns them in an
     * ArrayList.
     * 
     * @param path path to the file
     * @return array list of chemical names
     * @throws FileNotFoundException if the file does not exist
     */
    public static List<Chemical> readFile(String path) throws FileNotFoundException {
        List<Chemical> chemicals = new ArrayList<Chemical>();
        try (Scanner scan = new Scanner(new FileInputStream(path), "UTF8")) {
            while (scan.hasNextLine()) {
                String[] chemicalData = scan.nextLine().split("\t"); // split [name]\t[data] into
                                                                     // [name], [data]
                if (chemicalData.length > 0)
                    chemicals.add(new Chemical(chemicalData[0])); // add a chemical with name
                                                                  // [name] to the list
            }
        }
        return chemicals;
    }

    /**
     * Writes chemical names and their numbers of Wikipedia page views to a file.
     * 
     * @param path      path to the file
     * @param chemicals list of chemicals with names and page view values
     * @throws IOException if no write permissions or UTF-8 not supported
     */
    public static void writeFile(String path, List<Chemical> chemicals) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path);
                Writer w = new OutputStreamWriter(fos, "UTF8")) {
            w.write("\"Chemical Name\", \"Average Monthly Wikipedia Views in 2018\"\n");
            for (Chemical c : chemicals) {
                w.write(c.getName());
                w.write(',');
                w.write(Integer.toString(c.getViews()));
                w.write('\n');
            }
        }
    }
}
