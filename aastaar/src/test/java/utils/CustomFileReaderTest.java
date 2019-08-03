/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import mj.aastaar.utils.CustomFileReader;

/**
 *
 * @author MJ
 */
public class CustomFileReaderTest {

    private CustomFileReader reader;
    private String filePath;

    @Before
    public void setUp() {
        reader = new CustomFileReader();
        filePath = "testmaps/test.map";
    }

    @Test
    public void readFileReturnsCorrectData() throws Exception {
        reader.readFile(filePath);
        String[] dataArray = reader.getDataArray();
        assertEquals("type octile", dataArray[0]);
        assertEquals("width 4", dataArray[1]);
        assertEquals("height 4", dataArray[2]);
        assertEquals("map", dataArray[3]);
        assertEquals("T@@T", dataArray[4]);
        assertEquals("T..T", dataArray[5]);
        assertEquals("TW.T", dataArray[6]);
        assertEquals("TTTT", dataArray[7]);
        assertEquals(8, dataArray.length);
    }

    @Test(expected = Exception.class)
    public void throwsExceptionIfBadFilePath() throws Exception {
        String noSuchPath = "wrong/no_file.bad";
        reader.readFile(noSuchPath);
    }
}
