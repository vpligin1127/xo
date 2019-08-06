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
        boolean sameSame = false;

        if (object != null && object instanceof Point)
        {
            sameSame = (this.x == ((Point) object).x && this.y == ((Point) object).y);
        }

        return sameSame;
    }
}
