package com.wishmaster.singapore.skiing;

/**
 * @author Andrew
 */
public class Coordinate {

    int x;
    int y;
    Direction direction;

    boolean visitedLeft;
    boolean visitedTop;
    boolean visitedRight;
    boolean visitedBottom;

    public Coordinate(int x, int y) {
        this(x, y, null);
    }

    public Coordinate(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void setVisited(Direction direction) {
        switch (direction) {
            case LEFT:
                visitedLeft = true;
                break;
            case TOP:
                visitedTop = true;
                break;
            case RIGHT:
                visitedRight = true;
                break;
            case BOTTOM:
                visitedBottom = true;
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + ']';
    }
}
