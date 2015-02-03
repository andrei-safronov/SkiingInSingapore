package com.wishmaster.singapore.skiing;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Andrew
 */
public class MapDataParserTest {

    @Test
    public void testDataParsing() throws Exception {

        String[] stringData = {
                "4 4",
                "4 8 7 3",
                "2 5 9 3",
                "6 3 2 5",
                "4 4 1 6"
        };

        int[][] correctIntData = {
                {4, 8, 7, 3},
                {2, 5, 9, 3},
                {6, 3, 2, 5},
                {4, 4, 1, 6}
        };

        MapDataParser parser = new MapDataParser();
        parser.performParsing(Arrays.asList(stringData));

        assertEquals("Max X parsed incorrectly", 4, parser.getMaxX());
        assertEquals("Max Y parsed incorrectly", 4, parser.getMaxY());

        for (int i = 0; i < correctIntData.length; i++) {
            int[] parsedLine = parser.getData()[i];
            int[] correctLine = correctIntData[i];
            assertTrue("Data parsed incorrectly", Arrays.equals(parsedLine, correctLine));
        }
    }
}
