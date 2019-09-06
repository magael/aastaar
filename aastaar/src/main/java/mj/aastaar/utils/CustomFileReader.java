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

    private ArrayList<String> data;

    /**
     * Initializing an array for the file data.
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
     * @param filePath The file path
     * @throws Exception IO exception
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
     * @param br Java IO BufferedReader object
     * @throws IOException IO exception
     */
    private void addLines(BufferedReader br) throws IOException {
        String dataLine;
        while ((dataLine = br.readLine()) != null) {
            data.add(dataLine);
        }
    }
}
