package io.hexlet.xo.model;

public class Point {

    private final int x;

    private final int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object){
           return true;
        }

        if (object instanceof Point)
        {
            return (x == ((Point) object).x && y == ((Point) object).y);
        }

        return false;
    }
}
