package com.wishmaster.singapore.skiing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of algorithm to find longest path.
 * Actually, this is a modification of depth-first algorithm.
 * It checks all paths and finds the longest for each point from given data set.
 *
 * @author asafronov
 * @since 03.02.2015
 */
public final class LongestPathAlgorithm {

    private LongestPathAlgorithm() {
    }

    public static List<Point> getLongestPath(int[][] data, int maxX, int maxY) {
        LinkedList<Point> stack = new LinkedList<>();
        List<Point> longestPath = new ArrayList<>();

        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                stack.push(new Point(x, y));
                findLongestPath(maxX, maxY, data, stack, longestPath);
            }
        }

        return longestPath;
    }

    private static void findLongestPath(int maxX, int maxY, int[][] data, LinkedList<Point> stack, List<Point> longestPath) {
        Point current = stack.peek();
        int x = current.getX();
        int y = current.getY();

        int currentValue = data[x][y];

        //check if it's possible to move WEST
        if (y - 1 >= 0 && !current.isDirectionVisited(MoveDirection.WEST) && data[x][y - 1] < currentValue) {
            stack.push(new Point(x, y - 1, MoveDirection.WEST));
            findLongestPath(maxX, maxY, data, stack, longestPath);
        }

        //check if it's possible to move NORTH
        if (x - 1 >= 0 && !current.isDirectionVisited(MoveDirection.NORTH) && data[x - 1][y] < currentValue) {
            stack.push(new Point(x - 1, y, MoveDirection.NORTH));
            findLongestPath(maxX, maxY, data, stack, longestPath);
        }

        //check if it's possible to move EAST
        if (y + 1 < maxY && !current.isDirectionVisited(MoveDirection.EAST) && data[x][y + 1] < currentValue) {
            stack.push(new Point(x, y + 1, MoveDirection.EAST));
            findLongestPath(maxX, maxY, data, stack, longestPath);
        }

        //check if it's possible to move SOUTH
        if (x + 1 < maxX && !current.isDirectionVisited(MoveDirection.SOUTH) && data[x + 1][y] < currentValue) {
            stack.push(new Point(x + 1, y, MoveDirection.SOUTH));
            findLongestPath(maxX, maxY, data, stack, longestPath);
        }

        //actually, we have no direction to move forward
        //check if current path is longest one
        if (longestPath.size() < stack.size()) {
            longestPath.clear();
            longestPath.addAll(stack);
        } else {
            //current path length is equal to the length of longest path
            if (longestPath.size() == stack.size()) {
                int longestPathDropping = getDropping(data, longestPath);
                int stackDropping = getDropping(data, stack);

                //update longest path only if new path dropping is bigger
                if (stackDropping > longestPathDropping) {
                    longestPath.clear();
                    longestPath.addAll(stack);
                }
            }
        }

        //remove current point from stack
        stack.pop();

        //if it's not the start of path, it's needed to take previous point and set this direction as visited
        if (stack.peek() != null)
            stack.peek().markDirectionAsVisited(current.getOffsetFromPreviousCoordinate());
    }

    public static int getDropping(int[][] data, List<Point> a) {
        int start = data[a.get(0).getX()][a.get(0).getY()];
        int endIndex = a.size() - 1;
        int end = data[a.get(endIndex).getX()][a.get(endIndex).getY()];
        //subtract start from end because it's a stack and first (highest) point is the last!
        return end - start;
    }
}
