package com.wishmaster.singapore.skiing;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author asafronov
 * @since 03.02.2015
 */
public class LongestPathAlgorithmTest {

    /**
     * Test algorithm on given data in task
     */
    @Test
    public void testLongestPath() throws Exception {
        int maxX = 4;
        int maxY = 4;

        int[][] data = {
                {4, 8, 7, 3},
                {2, 5, 9, 3},
                {6, 3, 2, 5},
                {4, 4, 1, 6}
        };

        List<Point> longestPath = LongestPathAlgorithm.getLongestPath(data, maxX, maxY);

        prettyPrintLongestPath(longestPath, data);

        assertEquals("Longest path length is wrong", 5, longestPath.size());
        assertEquals("Point of path is wrong", 1, data[longestPath.get(0).getX()][longestPath.get(0).getY()]);
        assertEquals("Point of path is wrong", 2, data[longestPath.get(1).getX()][longestPath.get(1).getY()]);
        assertEquals("Point of path is wrong", 3, data[longestPath.get(2).getX()][longestPath.get(2).getY()]);
        assertEquals("Point of path is wrong", 5, data[longestPath.get(3).getX()][longestPath.get(3).getY()]);
        assertEquals("Point of path is wrong", 9, data[longestPath.get(4).getX()][longestPath.get(4).getY()]);
    }

    private static void prettyPrintLongestPath(List<Point> longestPath, int[][] data) {
        System.out.println("Longest path is: ");

        for (int i = longestPath.size() - 1; i >= 0; i--) {
            Point c = longestPath.get(i);
            System.out.print(data[c.getX()][c.getY()]);
            if (i != 0) {
                System.out.print("->");
            }
        }

        System.out.println();

        int length = longestPath.size();
        int dropping = LongestPathAlgorithm.getDropping(data, longestPath);
        System.out.println("length=" + length);
        System.out.println("dropping=" + dropping);
    }
}
