package com.wishmaster.singapore.skiing;

import java.util.EnumSet;
import java.util.Set;

/**
 * Represents map point.
 * Contain coordinates and visited directions and directional offset from previous point.
 *
 * @author Andrew
 */
public class Point {

    private final int x;
    private final int y;
    private final MoveDirection offsetFromPreviousCoordinate;

    private final Set<MoveDirection> visitedDirections;

    public Point(int x, int y) {
        this(x, y, null);
    }

    public Point(int x, int y, MoveDirection offsetFromPreviousCoordinate) {
        this.x = x;
        this.y = y;
        this.offsetFromPreviousCoordinate = offsetFromPreviousCoordinate;
        visitedDirections = EnumSet.noneOf(MoveDirection.class);
    }

    public void markDirectionAsVisited(MoveDirection direction) {
        visitedDirections.add(direction);
    }

    public boolean isDirectionVisited(MoveDirection direction) {
        return visitedDirections.contains(direction);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MoveDirection getOffsetFromPreviousCoordinate() {
        return offsetFromPreviousCoordinate;
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + ']';
    }
}
