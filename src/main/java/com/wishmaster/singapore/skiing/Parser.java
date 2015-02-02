package com.wishmaster.singapore.skiing;

import java.util.Scanner;

/**
 * @author Andrew
 */
public class Parser {

    final String value;
    int maxX;
    int maxY;

    int[][] data;

    public Parser(String value) {
        this.value = value;
    }

    void parseData() {
        Scanner scanner = new Scanner(value);
        parseMaxCoordinates(scanner);
        parseData(scanner);
    }

    private void parseMaxCoordinates(Scanner scanner) {
        if (!scanner.hasNextLine())
            throw new IllegalArgumentException("Wrong input data: " + value);

        if (!scanner.hasNextInt())
            throw new IllegalArgumentException("No maxX presented");
        maxX = scanner.nextInt();

        if (!scanner.hasNextInt())
            throw new IllegalArgumentException("No maxY presented");
        maxY = scanner.nextInt();

    }

    private void parseData(Scanner scanner) {
        data = new int[maxX][maxY];
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if (!scanner.hasNextInt())
                    throw new IllegalArgumentException("Wrong amount of data. x = " + x + ";y=" + y);

                data[x][y] = scanner.nextInt();
            }
        }
    }
}
