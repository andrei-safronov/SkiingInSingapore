package com.wishmaster.singapore.skiing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andrew
 */
public class App {

    private static final String URL = "http://s3-ap-southeast-1.amazonaws.com/geeks.redmart.com/coding-problems/map.txt";

    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();
        String values = URLDataReaderUtil.getDataAsString(URL);

        time = System.currentTimeMillis() - time;
        System.out.println(("Parsing took " + ((double) time) / 1000) + " sec.");

        Parser parser = new Parser(values);
        parser.parseData();


        int xLength = parser.maxX;
        int yLength = parser.maxY;
        int[][] data = parser.data;

        LinkedList<Coordinate> stack = new LinkedList<>();
        List<Coordinate> longestPath = new ArrayList<>();

        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {
                stack.clear();
                stack.push(new Coordinate(x, y));
                process(xLength, yLength, data, stack, longestPath);
            }
        }

        prettyPrintLongestPath(longestPath, data);
    }

    private static void prettyPrintLongestPath(List<Coordinate> longestPath, int[][] data) {
        System.out.println("Longest path is: ");

        for (int i = longestPath.size() - 1; i >= 0; i--) {
            Coordinate c = longestPath.get(i);
            System.out.print(data[c.x][c.y]);
            if (i != 0) {
                System.out.print("->");
            }
        }

        int length = longestPath.size();
        int dropping = getDropping(data, longestPath);
        System.out.print("; length=" + length);
        System.out.print("; dropping=" + dropping);
    }

    private static void process(int maxX, int maxY, int[][] data, LinkedList<Coordinate> stack, List<Coordinate> longestPath) {
        Coordinate current = stack.peek();
        int x = current.x;
        int y = current.y;

        int currentValue = data[x][y];

        //check left
        if (y - 1 >= 0 && !current.visitedLeft && data[x][y - 1] < currentValue) {
            stack.push(new Coordinate(x, y - 1, Direction.LEFT));
            process(maxX, maxY, data, stack, longestPath);
        }

        //check top
        if (x - 1 >= 0 && !current.visitedTop && data[x - 1][y] < currentValue) {
            stack.push(new Coordinate(x - 1, y, Direction.TOP));
            process(maxX, maxY, data, stack, longestPath);
        }

        //check right
        if (y + 1 < maxY && !current.visitedRight && data[x][y + 1] < currentValue) {
            stack.push(new Coordinate(x, y + 1, Direction.RIGHT));
            process(maxX, maxY, data, stack, longestPath);
        }

        //check bottom
        if (x + 1 < maxX && !current.visitedBottom && data[x + 1][y] < currentValue) {
            stack.push(new Coordinate(x + 1, y, Direction.BOTTOM));
            process(maxX, maxY, data, stack, longestPath);
        }

        //no way from current point, so let's update longest path if needed
        if (longestPath.size() < stack.size()) { //path in stack is greater than saved one
            longestPath.clear();
            longestPath.addAll(stack);
        } else {
            if (longestPath.size() == stack.size()) { //paths have equal size
                int longestPathDropping = getDropping(data, longestPath);
                int stackDropping = getDropping(data, stack);

                if (stackDropping > longestPathDropping) { //new path dropping is bigger that saved path one
                    longestPath.clear();
                    longestPath.addAll(stack);
                }
            }
        }

        //remove current from stack
        stack.pop();
        //mark parent direction as visited
        if (stack.peek() != null)
            stack.peek().setVisited(current.direction);
    }

    private static int getDropping(int[][] data, List<Coordinate> a) {
        int start = data[a.get(0).x][a.get(0).y]; //todo: not optimal for linkedList
        int endIndex = a.size() - 1;
        int end = data[a.get(endIndex).x][a.get(endIndex).y];
        //subtract start from end because it's a stack and first (highest) point is the last!
        return end - start;
    }


}
