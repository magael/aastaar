package mj.aastaar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The FileReader class.
 *
 * @author MJ
 */
public class CustomFileReader {

    /**
     * Reads a file from the given path and puts it's contents in a String array.
     *
     * @param filePath
     * @return The file data in a String ArrayList
     * @throws java.lang.Exception
     */
    private ArrayList<String> data;

    /**
     *
     */
    public CustomFileReader() {
        data = new ArrayList<>();
    }

    /**
     *
     * @return The data file rows as an array of Strings
     */
    public String[] getDataArray() {
        String[] dataArray = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }
        return dataArray;
    }

    /**
     * Reading the file at the provided path.
     * 
     * @param filePath
     * @throws Exception
     */
    public void readFile(String filePath) throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        addLines(br);
        is.close();
    }

    /**
     * Adding the data rows in the file into an ArrayList.
     * 
     * @param br
     * @throws IOException 
     */
    private void addLines(BufferedReader br) throws IOException {
        String dataLine;
        while ((dataLine = br.readLine()) != null) {
            data.add(dataLine);
        }
    }
}
