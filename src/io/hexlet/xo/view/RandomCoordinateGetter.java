package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Point;
import io.hexlet.xo.model.Figure;
import java.util.Random;

public class RandomCoordinateGetter implements ICoordinateGetter {

    private static final Random RANDOM = new Random();

    @Override
    public Point getMoveCoordinate(final Field field, Figure f) {
        Point randomPoint = new Point(RANDOM.nextInt(field.getSize()), RANDOM.nextInt(field.getSize()));
        while (field.getFigure(randomPoint) != null) {
            randomPoint = new Point(RANDOM.nextInt(field.getSize()), RANDOM.nextInt(field.getSize()));
        }
        return randomPoint;
    }
}
