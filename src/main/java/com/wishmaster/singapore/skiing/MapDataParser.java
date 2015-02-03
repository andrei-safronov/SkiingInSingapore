package com.wishmaster.singapore.skiing;

import java.util.List;
import java.util.Scanner;

/**
 * Simple parser of input data.
 *
 * @author Andrew
 */
public class MapDataParser {

    private int maxX;
    private int maxY;

    private int[][] data;

    public void performParsing(List<String> data) {
        parseMaxCoordinates(data.get(0));
        parseData(data.subList(1, data.size()));
    }

    private void parseMaxCoordinates(String firstLine) {
        try (Scanner scanner = new Scanner(firstLine)) {
            if (!scanner.hasNextLine())
                throw new IllegalArgumentException("Wrong input data: " + firstLine);

            if (!scanner.hasNextInt())
                throw new IllegalArgumentException("No maxX presented");
            maxX = scanner.nextInt();

            if (!scanner.hasNextInt())
                throw new IllegalArgumentException("No maxY presented");
            maxY = scanner.nextInt();
        }
    }

    private void parseData(List<String> lines) {
        data = new int[maxX][maxY];
        for (int x = 0; x < maxX; x++) {
            try (Scanner scanner = new Scanner(lines.get(x))) {
                for (int y = 0; y < maxY; y++) {
                    if (!scanner.hasNextInt())
                        throw new IllegalArgumentException("Wrong amount of data. x = " + x + ";y=" + y);

                    data[x][y] = scanner.nextInt();
                }
            }
        }
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int[][] getData() {
        return data;
    }
}
