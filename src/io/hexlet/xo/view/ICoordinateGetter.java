package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Point;

public interface ICoordinateGetter {

    Point getMoveCoordinate(final Field field);

}
