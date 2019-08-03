package io.hexlet.xo.controllers;


import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Point;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.AlreadyOccupiedException;
import io.hexlet.xo.model.exceptions.InvalidPointException;

public class MoveController {

    public boolean applyFigure(final Field field,
                               final Point point,
                               final Figure figure) throws InvalidPointException, AlreadyOccupiedException {
        if(!checkPoint(point, field)) throw new InvalidPointException(point, field);
        if (field.getFigure(point) != null) throw new AlreadyOccupiedException();
        field.setFigure(point, figure);
        return true;
    }

    private boolean checkPoint(final Point point, Field field){
        if (point.getX() < 0
                || point.getX() > (field.getSize() - 1)
                || point.getY() < 0
                || point.getY() > (field.getSize() - 1)) {
            return false;
        }
        return true;
    }
}
