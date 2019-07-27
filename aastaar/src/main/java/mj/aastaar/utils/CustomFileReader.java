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
     * @return the file data in a String ArrayList
     * @throws java.lang.Exception
     */
    private ArrayList<String> dataArray;

    public CustomFileReader() {
        dataArray = new ArrayList<>();
    }

    public ArrayList<String> getDataArrayList() {
        return dataArray;
    }

    public String[] getDataArray() {
        String[] stringArray = new String[dataArray.size()];
        for (int i = 0; i < dataArray.size(); i++) {
            stringArray[i] = dataArray.get(i);
        }
        return stringArray;
    }

    public void readFile(String filePath) throws Exception {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        addLines(br);
        is.close();
    }

    private void addLines(BufferedReader br) throws IOException {
        String dataLine;
        while ((dataLine = br.readLine()) != null) {
            dataArray.add(dataLine);
        }
    }
}
