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
    public ArrayList<String> readFile(String filePath) throws Exception {
        ArrayList<String> stringDataList = new ArrayList<>();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        addLines(br, stringDataList);
        is.close();

        return stringDataList;
    }

    private void addLines(BufferedReader br, ArrayList<String> stringDataList) throws IOException {
        String dataLine;
        while ((dataLine = br.readLine()) != null) {
                stringDataList.add(dataLine);
        }
    }
}
