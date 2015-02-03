package com.wishmaster.singapore.skiing;

import java.io.IOException;
import java.util.List;

/**
 * @author Andrew
 */
public class App {

    private static final String MAP_FILE_RESOURCE = "/map.txt";

    public static void main(String[] args) throws IOException {
        //read data from map file located in classpath
        MapFileLinesReader reader = new MapFileLinesReader(MAP_FILE_RESOURCE);
        List<String> mapFileLines = reader.getMapFileLines();

        //parse read data
        MapDataParser parser = new MapDataParser();
        parser.performParsing(mapFileLines);

        //get parsed values and max coordinate values
        int maxX = parser.getMaxX();
        int maxY = parser.getMaxY();
        int[][] data = parser.getData();

        //get longest path
        List<Point> longestPath = LongestPathAlgorithm.getLongestPath(data, maxX, maxY);

        //display longest path
        prettyPrintLongestPath(longestPath, data);
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
